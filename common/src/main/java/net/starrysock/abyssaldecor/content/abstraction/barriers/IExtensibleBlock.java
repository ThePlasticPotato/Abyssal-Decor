package net.starrysock.abyssaldecor.content.abstraction.barriers;

import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Locale;

public interface IExtensibleBlock {
    public static final EnumProperty<ExtensionType> EXTENSION_TYPE = EnumProperty.create("extension_type", ExtensionType.class);

    public default ExtensionType getExtensionType(Level level, BlockPos pos) {
        if (level.getBlockState(pos).getBlock() instanceof IExtensibleBlock) {
            return level.getBlockState(pos).getValue(EXTENSION_TYPE);
        }
        return null;
    }

    public static enum ExtensionType implements StringRepresentable {
        BASE,
        EXTENSION,
        TIP;

        @Override
        public String getSerializedName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
