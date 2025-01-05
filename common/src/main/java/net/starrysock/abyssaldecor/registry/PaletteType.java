package net.starrysock.abyssaldecor.registry;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.starrysock.abyssaldecor.AbyssalDecor;

import java.util.ArrayList;
import java.util.List;

public enum PaletteType {
    METAL,
    WOOD,
    PEARL,
    GLASSLIKE,
    FRAMED_GLASSLIKE,
    CORAL,
    SIMPLE,
    ;

    public String getPath() {
        return AbyssalDecor.MOD_ID + ":" + this.name().toLowerCase() + "/";
    }

    public List<String> getVariants() {
        ArrayList<String> variants = new ArrayList<>();
        switch (this) {
            case METAL -> {
                variants.add("_block");
                variants.add("_riveted");
                variants.add("_pillar");
                variants.add("_large_pipe");
                variants.add("_pipe");
                variants.add("_small_pipes");
                variants.add("_tiles");
                variants.add("_plating");
                variants.add("_slab");
                variants.add("_riveted_slab");
                variants.add("_stairs");
                variants.add("_riveted_stairs");
                variants.add("_wall");
                variants.add("_riveted_wall");
                variants.add("_door");
                variants.add("_sconce");
                variants.add("_bars");
                variants.add("_ornate_bars");
                variants.add("_chain");
                variants.add("_button");
                variants.add("_trapdoor");
                variants.add("_pressure_plate");
                variants.add("_small_bars");
                variants.add("_small_bars_corner");
                variants.add("_lamp");
                variants.add("_beam");
            }
            case WOOD -> {
                variants.add("_log");
                variants.add("_stripped_log");
                variants.add("_wood");
                variants.add("_stripped_wood");
                variants.add("_planks");
                variants.add("_trim");
                variants.add("_planter");
                variants.add("_slab");
                variants.add("_shingled");
                variants.add("_shingled_slab");
                variants.add("_stairs");
                variants.add("_shingled_stairs");
                variants.add("_fence");
                variants.add("_fence_gate");
                variants.add("_door");
                variants.add("_post");
                variants.add("_button");
                variants.add("_trapdoor");
                variants.add("_pressure_plate");
            }
            case PEARL -> {
                variants.add("_block");
                variants.add("_cut");
                variants.add("_chiseled");
                variants.add("_smooth");
                variants.add("_pillar");
                variants.add("_tiles");
                variants.add("_cracked_tiles");
                variants.add("_mixed_tiles");
                variants.add("_starry_tiles");
                variants.add("_bricks");
                variants.add("_brick_slab");
                variants.add("_brick_stairs");
                variants.add("_brick_wall");
                variants.add("_slab");
                variants.add("_smooth_slab");
                variants.add("_stairs");
                variants.add("_smooth_stairs");
                variants.add("_wall");
                variants.add("_smooth_wall");
                variants.add("_door");
                variants.add("_bars");
                variants.add("_trapdoor");
                variants.add("_small_bars");
                variants.add("_small_bars_corner");
            }
            case GLASSLIKE -> {
                variants.add("_block");
                variants.add("_pane");
            }
            case FRAMED_GLASSLIKE -> {
                variants.add("_block");
                variants.add("_pane");
                variants.add("_framed_block");
                variants.add("_framed_pane");
            }
            case CORAL -> {
                variants.add("_bud");
                variants.add("_smooth");
                variants.add("_smooth_slab");
                variants.add("_smooth_stairs");
                variants.add("_smooth_wall");
                variants.add("_polished");
                variants.add("_polished_slab");
                variants.add("_polished_stairs");
                variants.add("_polished_wall");
                variants.add("_bricks");
                variants.add("_brick_slab");
                variants.add("_brick_stairs");
                variants.add("_brick_wall");
                variants.add("_pillar");
                variants.add("_gilded_pillar");
                variants.add("_rough");
                variants.add("_door");
                variants.add("_trapdoor");
                variants.add("_bars");
                variants.add("_lantern");
                variants.add("_sconce");
            }
        }
        return variants;
    }

    public BlockBehaviour.Properties getProperties() {
        return switch (this) {
            case METAL -> BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK);
            case WOOD -> BlockBehaviour.Properties.copy(Blocks.OAK_WOOD);
            case PEARL -> BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK);
            case GLASSLIKE, FRAMED_GLASSLIKE -> BlockBehaviour.Properties.copy(Blocks.GLASS);
            case CORAL -> BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_BLOCK);
            case SIMPLE -> BlockBehaviour.Properties.copy(Blocks.STONE);
        };
    }

    public BlockSetType getSetType() {
        return switch (this) {
            case METAL, FRAMED_GLASSLIKE -> BlockSetType.IRON;
            case WOOD -> BlockSetType.OAK;
            case PEARL, GLASSLIKE, SIMPLE -> BlockSetType.STONE;
            case CORAL -> BlockSetType.CRIMSON;
        };
    }
}
