package net.starrysock.abyssaldecor.content.abstraction.barriers;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.AbyssalVoxelShapes;

import java.util.HashMap;
import java.util.List;

public class TiedBarrierBlock extends Block implements IExtensibleBlock, SimpleWaterloggedBlock {
    public TiedBarrierBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(EXTENSION_TYPE);
        builder.add(BlockStateProperties.WATERLOGGED);
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        ExtensionType type = ExtensionType.BASE;
        if (level.getBlockState(blockPos.below()).is(this)) {
            type = ExtensionType.TIP;
            if (getExtensionType(level, blockPos.below()) == ExtensionType.TIP) {
                level.setBlock(blockPos.below(), level.getBlockState(blockPos.below()).setValue(EXTENSION_TYPE, ExtensionType.EXTENSION), 3);
            }
        }
        if (level.getBlockState(blockPos.above()).is(this))  {
            if (type != ExtensionType.BASE) {
                type = ExtensionType.EXTENSION;
            }
        } else if (level.getBlockState(blockPos.above()).isAir() || level.getBlockState(blockPos.above()).canBeReplaced()) {
            boolean wasWaterlogged = (level.getBlockState(blockPos.above()).hasProperty(BlockStateProperties.WATERLOGGED) && level.getBlockState(blockPos.above()).getValue(BlockStateProperties.WATERLOGGED)) || level.getFluidState(blockPos.above()).is(Fluids.WATER);
            level.setBlock(blockPos.above(), this.defaultBlockState().setValue(EXTENSION_TYPE, ExtensionType.TIP).setValue(BlockStateProperties.WATERLOGGED, wasWaterlogged), 3);
        }
        super.onPlace(blockState.setValue(EXTENSION_TYPE, type), level, blockPos, blockState2, bl);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        super.onRemove(blockState, level, blockPos, blockState2, bl);
    }

    @Override
    public void destroy(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState) {
        if (blockState.hasProperty(EXTENSION_TYPE)) {
            if (blockState.getValue(EXTENSION_TYPE) == ExtensionType.TIP) {
                if (levelAccessor.getBlockState(blockPos.below()).getBlock() instanceof TiedBarrierBlock) {
                    if (levelAccessor.getBlockState(blockPos.below()).getValue(EXTENSION_TYPE) == ExtensionType.EXTENSION) {
                        levelAccessor.setBlock(blockPos.below(), levelAccessor.getBlockState(blockPos.below()).setValue(EXTENSION_TYPE, ExtensionType.TIP), 3);
                    } else {
                        levelAccessor.destroyBlock(blockPos.below(), true);
                    }
                }
            }
        }
        super.destroy(levelAccessor, blockPos, blockState);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.below()).getBlock() instanceof TiedBarrierBlock || levelReader.getBlockState(blockPos.below()).isFaceSturdy(levelReader, blockPos.below(), Direction.UP, SupportType.CENTER);
    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos2, boolean bl) {
        if (!(level.getBlockState(blockPos.above()).getBlock() instanceof TiedBarrierBlock) && level.getBlockState(blockPos).getValue(EXTENSION_TYPE) == ExtensionType.EXTENSION) {
            level.setBlock(blockPos, level.getBlockState(blockPos).setValue(EXTENSION_TYPE, ExtensionType.TIP), 3);
        }
        super.neighborChanged(blockState, level, blockPos, block, blockPos2, bl);
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        if (blockState.getValue(EXTENSION_TYPE) == ExtensionType.TIP) {
            return List.of();
        }
        return super.getDrops(blockState, builder);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (blockState.hasProperty(EXTENSION_TYPE)) {
            return switch (blockState.getValue(EXTENSION_TYPE)) {
                case BASE, EXTENSION -> AbyssalVoxelShapes.BARRIER_BASE;
                case TIP -> AbyssalVoxelShapes.BARRIER_TIP;
            };
        }
        return super.getShape(blockState, blockGetter, blockPos, collisionContext);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        Direction interactedFace = blockHitResult.getDirection();
        if (interactedFace.getAxis() == Direction.Axis.Y) {
            return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
        }
        BarrierType type = BarrierType.fromBlockState(blockState);
        if (blockState.getValue(EXTENSION_TYPE) == ExtensionType.BASE) {
            return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
        } else {
            BlockPos neighborPos = findNeighborPost(blockPos, level, interactedFace);
            BarrierType neighborType = BarrierType.fromBlockState(level.getBlockState(neighborPos));
            if (level.getBlockState(neighborPos).getValue(EXTENSION_TYPE) != ExtensionType.BASE && neighborType != null) {
                BlockState betweenState = level.getBlockState(blockPos.relative(interactedFace));
                RegistrySupplier<Block> tieType = switch (neighborType) {
                    case VELVET -> AbyssalDecorBlocks.VELVET_BARRIER_TIE;
                    case IRON -> AbyssalDecorBlocks.IRON_BARRIER_TIE;
                    case ROPE -> AbyssalDecorBlocks.ROPE_BARRIER_TIE;
                    case BARBED -> AbyssalDecorBlocks.BARBED_WIRE_BARRIER_TIE;
                };
                if (neighborType == type && (betweenState.isAir() || betweenState.canBeReplaced()) && !(betweenState.getBlock() instanceof BarrierTieBlock)) {
                    boolean wasWaterlogged = (betweenState.hasProperty(BlockStateProperties.WATERLOGGED) && betweenState.getValue(BlockStateProperties.WATERLOGGED)) || level.getFluidState(blockPos.above()).is(Fluids.WATER);
                    level.setBlock(blockPos.relative(interactedFace), tieType.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, interactedFace.getClockWise()).setValue(BlockStateProperties.WATERLOGGED, wasWaterlogged), 3);
                    level.playSound(null, blockPos, tieType.get().defaultBlockState().getSoundType().getPlaceSound(), SoundSource.BLOCKS, 1.0F, 0.5F);
                }
            }


        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    private BlockPos findNeighborPost(BlockPos pos, Level level, Direction direction) {
        BlockPos neighborPos = pos.relative(direction, 2);
        if (level.getBlockState(neighborPos).getBlock() instanceof TiedBarrierBlock) {
            return neighborPos;
        }
        return null;
    }

    private BlockPos findBasePost(BlockPos pos, Level level) {
        BlockPos basePos = pos;
        while (level.getBlockState(basePos.below()).getBlock() instanceof TiedBarrierBlock) {
            basePos = basePos.below();
        }
        return basePos;
    }

    private HashMap<BlockPos, ExtensionType> getFullPost(BlockPos pos, Level level, boolean startingFromBase) {
        BlockPos startPos = pos;
        if (!startingFromBase) {
            while (level.getBlockState(startPos.below()).getBlock() instanceof TiedBarrierBlock) {
                startPos = startPos.below();
            }
        }
        HashMap<BlockPos, ExtensionType> post = new HashMap<>();
        BlockPos currentPos = startPos;
        ExtensionType currentType = ExtensionType.BASE;
        BlockPos topPos = startPos;
        while (level.getBlockState(currentPos).getBlock() instanceof TiedBarrierBlock) {
            post.put(currentPos, currentType);
            currentPos = currentPos.above();
            currentType = ExtensionType.EXTENSION;
            if (currentPos.getY() > topPos.getY()) {
                topPos = currentPos;
            }
        }
        post.put(topPos, ExtensionType.TIP);
        return post;
    }
}
