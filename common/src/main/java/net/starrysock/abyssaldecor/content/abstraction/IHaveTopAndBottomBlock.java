package net.starrysock.abyssaldecor.content.abstraction;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public interface IHaveTopAndBottomBlock {
    public static BooleanProperty TOP = BooleanProperty.create("top");

    private void placeTop(BlockGetter level, BlockPos pos) {

    }
}
