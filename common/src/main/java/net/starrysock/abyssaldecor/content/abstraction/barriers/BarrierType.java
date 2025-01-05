package net.starrysock.abyssaldecor.content.abstraction.barriers;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.BlockState;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

import java.util.Locale;

public enum BarrierType implements StringRepresentable {
    VELVET,
    IRON,
    ROPE,
    BARBED,
    ;

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static BarrierType fromBlockState(BlockState state) {
        if (state.is(AbyssalDecorBlocks.VELVET_BARRIER.get())) {
            return VELVET;
        } else if (state.is(AbyssalDecorBlocks.IRON_BARRIER.get())) {
            return IRON;
        } else if (state.is(AbyssalDecorBlocks.ROPE_BARRIER.get())) {
            return ROPE;
        } else if (state.is(AbyssalDecorBlocks.BARBED_WIRE_BARRIER.get())) {
            return BARBED;
        } else {
            return null;
        }
    }
}
