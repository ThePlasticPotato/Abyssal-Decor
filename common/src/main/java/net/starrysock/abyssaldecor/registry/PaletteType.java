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
    PEARL
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
                variants.add("_small_pipe");
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
        }
        return variants;
    }

    public BlockBehaviour.Properties getProperties() {
        return switch (this) {
            case METAL -> BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK);
            case WOOD -> BlockBehaviour.Properties.copy(Blocks.OAK_WOOD);
            case PEARL -> BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK);
        };
    }

    public BlockSetType getSetType() {
        return switch (this) {
            case METAL -> BlockSetType.IRON;
            case WOOD -> BlockSetType.OAK;
            case PEARL -> BlockSetType.STONE;
        };
    }
}
