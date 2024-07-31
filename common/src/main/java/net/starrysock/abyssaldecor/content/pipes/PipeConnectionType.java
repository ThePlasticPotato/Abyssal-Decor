package net.starrysock.abyssaldecor.content.pipes;

import net.minecraft.util.StringRepresentable;

public enum PipeConnectionType implements StringRepresentable {
    SIDE("true"),
    FORCED("forced"),
    NONE("false")
    ;

    private final String value;

    PipeConnectionType(String value) {
        this.value = value;
    }

    public Boolean isConnected() {
        return this == SIDE;
    }

    public Boolean canBeChanged() {
        return this == SIDE || this == NONE;
    }

    @Override
    public String getSerializedName() {
        return value;
    }
}
