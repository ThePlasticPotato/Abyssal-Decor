package net.starrysock.abyssaldecor.content.lamps;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.starrysock.abyssaldecor.content.abstraction.lamps.WallMountedInteractibleLampBlock;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class BulkheadLampBlock extends WallMountedInteractibleLampBlock {
    public static EnumProperty<PlacementType> PLACEMENT_TYPE = EnumProperty.create("placement_type", PlacementType.class);

    public BulkheadLampBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PLACEMENT_TYPE);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        PlacementType placementType = blockPlaceContext.getClickedFace().getAxis().isVertical() ? PlacementType.WALL : blockPlaceContext.getClickedFace() == Direction.DOWN ? PlacementType.CEILING : PlacementType.FLOOR;
        return super.getStateForPlacement(blockPlaceContext).setValue(PLACEMENT_TYPE, placementType);
    }

    public enum PlacementType implements StringRepresentable {
        WALL,
        FLOOR,
        CEILING;

        @Override
        public String getSerializedName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
