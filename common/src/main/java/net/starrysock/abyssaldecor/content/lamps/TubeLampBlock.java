package net.starrysock.abyssaldecor.content.lamps;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.starrysock.abyssaldecor.content.abstraction.IAdditionalDirection;
import net.starrysock.abyssaldecor.content.abstraction.lamps.WallMountedInteractibleLampBlock;
import org.jetbrains.annotations.Nullable;

public class TubeLampBlock extends WallMountedInteractibleLampBlock implements IAdditionalDirection {
    public TubeLampBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(UP);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        boolean shouldBeTop = blockPlaceContext.getClickedFace() == Direction.DOWN || (blockPlaceContext.getClickedFace().getAxis().isHorizontal() && (blockPlaceContext.getClickLocation().y - blockPlaceContext.getClickedPos().getY()) >= 0.5);
        return super.getStateForPlacement(blockPlaceContext).setValue(UP, shouldBeTop);
    }
}
