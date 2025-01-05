package net.starrysock.abyssaldecor.content.abstraction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SmallBarsBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

    public static final DirectionProperty VERTICAL_FACING = DirectionProperty.create("vertical_facing", Direction.Plane.VERTICAL);

    public SmallBarsBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState) this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(VERTICAL_FACING, Direction.UP).setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(VERTICAL_FACING);
        builder.add(BlockStateProperties.WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        Direction horizontalDirection = blockPlaceContext.getHorizontalDirection();
        Direction verticalDirection = blockPlaceContext.getNearestLookingVerticalDirection();
        return this.defaultBlockState().setValue(FACING, horizontalDirection.getOpposite()).setValue(VERTICAL_FACING, verticalDirection.getOpposite()).setValue(BlockStateProperties.WATERLOGGED, blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player.getItemInHand(interactionHand).isEmpty()) {
            level.setBlockAndUpdate(blockPos, blockState.cycle(VERTICAL_FACING));
            level.playLocalSound(blockPos, SoundEvents.LANTERN_PLACE, SoundSource.BLOCKS, 1.0F, 0.5F, false);
            return InteractionResult.SUCCESS;
        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }
}
