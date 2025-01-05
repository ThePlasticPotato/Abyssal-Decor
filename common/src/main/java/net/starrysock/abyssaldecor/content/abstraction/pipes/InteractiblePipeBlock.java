package net.starrysock.abyssaldecor.content.abstraction.pipes;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Vector2f;

import java.util.HashMap;
import java.util.Map;

public class InteractiblePipeBlock extends Block implements SimpleWaterloggedBlock {

    //TEMPORARY, UNTIL POTATO STOPS BEING LAZY AND MAKES A LIBRARY

    //Pipe implementation ""inspired"" by neepmeat

    private final HashMap<BlockState, VoxelShape> shapes = new HashMap<>();

    public Map<Direction, EnumProperty<PipeConnectionType>> DIR_TO_CONNECTION =
            new ImmutableMap.Builder<Direction, EnumProperty<PipeConnectionType>>()
            .put(Direction.NORTH, NORTH_CONNECTION)
            .put(Direction.EAST, EAST_CONNECTION)
            .put(Direction.SOUTH, SOUTH_CONNECTION)
            .put(Direction.WEST, WEST_CONNECTION)
            .put(Direction.DOWN, DOWN_CONNECTION)
            .put(Direction.UP, UP_CONNECTION).build();

    public Map<Direction, VoxelShape> DIR_SHAPES = new ImmutableMap.Builder<Direction, VoxelShape>()
            .put(Direction.NORTH, box(5.0, 5.0, 0.0, 11.0, 11.0, 5.0))
            .put(Direction.EAST, box(11.0, 5.0, 5.0, 16.0, 11.0, 11.0))
            .put(Direction.SOUTH, box(5.0, 5.0, 11.0, 11.0, 11.0, 16.0))
            .put(Direction.WEST, box(0.0, 5.0, 5.0, 5.0, 11.0, 11.0))
            .put(Direction.UP, box(5.0, 11.0, 5.0, 11.0, 16.0, 11.0))
            .put(Direction.DOWN, box(5.0, 0.0, 5.0, 11.0, 5.0, 11.0)).build();

    public static final EnumProperty<PipeConnectionType> NORTH_CONNECTION = EnumProperty.create("north", PipeConnectionType.class);
    public static final EnumProperty<PipeConnectionType> EAST_CONNECTION = EnumProperty.create("east", PipeConnectionType.class);
    public static final EnumProperty<PipeConnectionType> SOUTH_CONNECTION = EnumProperty.create("south", PipeConnectionType.class);
    public static final EnumProperty<PipeConnectionType> WEST_CONNECTION = EnumProperty.create("west", PipeConnectionType.class);
    public static final EnumProperty<PipeConnectionType> UP_CONNECTION = EnumProperty.create("up", PipeConnectionType.class);
    public static final EnumProperty<PipeConnectionType> DOWN_CONNECTION = EnumProperty.create("down", PipeConnectionType.class);


    public InteractiblePipeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(ductConnectionsDefault(defaultBlockState()).setValue(BlockStateProperties.WATERLOGGED, false));

        for (BlockState state : this.stateDefinition.getPossibleStates())
        {
            shapes.put(state, getShapeForState(state));
        }
    }
    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(
                NORTH_CONNECTION,
                EAST_CONNECTION,
                SOUTH_CONNECTION,
                WEST_CONNECTION,
                UP_CONNECTION,
                DOWN_CONNECTION
        );

        builder.add(BlockStateProperties.WATERLOGGED);

        super.createBlockStateDefinition(builder);
    }

    private BlockState ductConnectionsDefault(BlockState defaultBlockState) {
        return defaultBlockState
                .setValue(NORTH_CONNECTION, PipeConnectionType.NONE)
                .setValue(EAST_CONNECTION, PipeConnectionType.NONE)
                .setValue(SOUTH_CONNECTION, PipeConnectionType.NONE)
                .setValue(WEST_CONNECTION, PipeConnectionType.NONE)
                .setValue(UP_CONNECTION, PipeConnectionType.NONE)
                .setValue(DOWN_CONNECTION, PipeConnectionType.NONE)
                ;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide()) {
            Direction direction = blockHitResult.getDirection();
            Vec3 hitPos = blockHitResult.getLocation();
            Direction changeDirection = getUseDirection(direction, blockHitResult.getBlockPos(), hitPos);
            boolean connected = blockState.getValue(DIR_TO_CONNECTION.get(changeDirection)) == PipeConnectionType.SIDE;
            BlockState newState = blockState.setValue(DIR_TO_CONNECTION.get(changeDirection), connected ? PipeConnectionType.FORCED : PipeConnectionType.SIDE);
            level.setBlockAndUpdate(blockHitResult.getBlockPos(), newState);

            return InteractionResult.SUCCESS;
        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public boolean canBeReplaced(BlockState state, Fluid fluid) {
        return false;
    }

    public static Vector2f removeAxis(Direction.Axis axis, Vec3 vec) {
        Vector2f out;
        switch (axis) {
            case X:
                out = new Vector2f((float) vec.y, (float) vec.z);
                break;
            case Y:
                out = new Vector2f((float) vec.x, (float) vec.z);
                break;
            case Z:
                out = new Vector2f((float) vec.x, (float) vec.y);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + axis);
        }
        return out;
    }

    public static boolean isWithin(Vector2f vector, float x1, float y1, float difference) {
        return Math.abs(x1 - vector.x) < difference && Math.abs(y1 - vector.y) < difference;
    }

    public Direction getUseDirection(Direction direction, BlockPos pos, Vec3 hitPos) {
        Vector2f relative = removeAxis(direction.getAxis(), hitPos.subtract(pos.getX(), pos.getY(), pos.getZ()));
        Direction changeDirection = direction;
        if (!isWithin(relative, 0.5f, 0.5f, 0.25f)) {
            // X axis case
            if (relative.y > 0.75) changeDirection = Direction.SOUTH;
            if (relative.y < 0.25) changeDirection = Direction.NORTH;
            if (relative.x < 0.25) changeDirection = Direction.DOWN;
            if (relative.x > 0.75) changeDirection = Direction.UP;
            switch (direction.getAxis()) {
                case Y -> changeDirection = changeDirection.getClockWise(Direction.Axis.Z);
                case Z -> {
                    changeDirection = switch (changeDirection.getClockWise(Direction.Axis.Y)) {
                        case UP -> Direction.EAST;
                        case DOWN -> Direction.WEST;
                        case EAST -> Direction.DOWN;
                        case WEST -> Direction.UP;
                        default -> Direction.NORTH;
                    };
                }
                default -> {
                }
            }
        }
        return changeDirection;
    }

    protected VoxelShape getCenterShape() {
        return box(5.0, 5.0, 5.0, 11.0, 11.0, 11.0);
    }

    public VoxelShape getShapeForState(BlockState state) {
        VoxelShape shape = getCenterShape();
        for (Direction direction : Direction.values()) {
            if (state.getValue(DIR_TO_CONNECTION.get(direction)) == PipeConnectionType.SIDE) {
                shape = Shapes.or(shape, DIR_SHAPES.get(direction));
            }
        }
        return shape;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shapes.get(state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shapes.get(state);
    }

    public boolean connectInDirection(BlockGetter world, BlockPos pos, BlockState state, Direction direction) {
        return state.getValue(DIR_TO_CONNECTION.get(direction)).canBeChanged();
    }

    public boolean canConnectTo(BlockPos self, BlockPos other, Direction direction, BlockGetter level) {
        if (self.distSqr(other) > 1.0) return false;
        BlockState selfState = level.getBlockState(self);
        BlockState otherState = level.getBlockState(other);

        if (!(otherState.getBlock() instanceof InteractiblePipeBlock)) return false;

        return connectInDirection(level, other, otherState, direction);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        return this.getConnectedState(level, this.defaultBlockState(), pos);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        PipeConnectionType type = state.getValue(DIR_TO_CONNECTION.get(direction));
        boolean forced = type == PipeConnectionType.FORCED;
        boolean otherConnected = false;

        boolean canConnect = canConnectTo(currentPos, neighborPos, direction.getOpposite(), (Level) level);

        if (neighborState.getBlock() instanceof InteractiblePipeBlock) {
            forced = forced || neighborState.getValue(DIR_TO_CONNECTION.get(direction.getOpposite())) == PipeConnectionType.FORCED;
            otherConnected = neighborState.getValue(DIR_TO_CONNECTION.get(direction.getOpposite())) == PipeConnectionType.SIDE;
        }
        PipeConnectionType finalConnection = otherConnected ? PipeConnectionType.SIDE : (forced ? PipeConnectionType.FORCED : (canConnect ? PipeConnectionType.SIDE : PipeConnectionType.NONE));

        return state.setValue(DIR_TO_CONNECTION.get(direction), finalConnection);
    }

    protected BlockState getConnectedState(BlockGetter level, BlockState state, BlockPos pos) {
        BlockState newState = state;
        for (Direction direction : Direction.values()) {
            PipeConnectionType property = newState.getValue(DIR_TO_CONNECTION.get(direction));
            if (property == PipeConnectionType.SIDE) continue;
            BlockPos adjPos = pos.relative(direction);
            newState = newState.setValue(DIR_TO_CONNECTION.get(direction), canConnectTo(pos, adjPos, direction.getOpposite(), (Level) level) ? PipeConnectionType.SIDE : PipeConnectionType.NONE);
        }
        return newState.setValue(BlockStateProperties.WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);
    }
}
