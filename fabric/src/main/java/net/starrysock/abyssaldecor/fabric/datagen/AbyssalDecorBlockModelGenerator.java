package net.starrysock.abyssaldecor.fabric.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class AbyssalDecorBlockModelGenerator extends FabricModelProvider {
    public AbyssalDecorBlockModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }

    public static class SimpleBlockStateGenerator implements BlockStateGenerator
    {
        private final Block block;
        private final ResourceLocation model;

        public SimpleBlockStateGenerator(Block block, ResourceLocation model)
        {
            this.block = block;
            this.model = model;
        }

        @Override
        public Block getBlock()
        {
            return block;
        }

        @Override
        public JsonElement get()
        {
            JsonObject root = new JsonObject();
            JsonObject variant = new JsonObject();
            JsonObject model = new JsonObject();

            model.addProperty("model", this.model.toString());
            variant.add("", model);
            root.add("variants", variant);
            return root;
        }

    }
}
