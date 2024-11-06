package net.starrysock.abyssaldecor.content;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.starrysock.abyssaldecor.registry.AbyssalBlockSetType;
import org.jetbrains.annotations.Nullable;

public class InteractibleRedstoneLampBlock extends RedstoneLampBlock {
    public static final BooleanProperty FORCED = BooleanProperty.create("forced");

    public InteractibleRedstoneLampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState) this.defaultBlockState().setValue(LIT, false).setValue(FORCED, false));
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player.getItemInHand(interactionHand).isEmpty()) {
            level.setBlockAndUpdate(blockPos, blockState.cycle(LIT).cycle(FORCED));
            level.playLocalSound(blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 0.5F, false);
            return InteractionResult.SUCCESS;
        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LIT);
        builder.add(FORCED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return super.getStateForPlacement(blockPlaceContext).setValue(FORCED, false);
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(FORCED)) {
            return;
        }
        super.tick(blockState, serverLevel, blockPos, randomSource);
    }
}
