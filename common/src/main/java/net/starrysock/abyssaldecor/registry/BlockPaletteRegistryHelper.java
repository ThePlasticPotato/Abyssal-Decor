package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.starrysock.abyssaldecor.AbyssalDecor;

import java.util.HashMap;
import java.util.Map;

public class BlockPaletteRegistryHelper {

    public static HashMap<String, RegistrySupplier<Block>> paletteBlocks = new HashMap<>();

    public static HashMap<String, RegistrySupplier<Item>> paletteItems = new HashMap<>();

    public static void init(Map<String, PaletteType> toRegister) {
        for (String key : toRegister.keySet()) {
            PaletteType type = toRegister.get(key);
            for (String variant : type.getVariants()) {
                String name = key + variant;
                var block = switch (variant) {
                    case "_button" -> {
                        AbyssalDecor.BLOCKS.register(name, () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON), AbyssalBlockSetType.byName(key).toBlockSetType();
                    }
                    default -> {
                        block = AbyssalDecor.BLOCKS.register(name, () -> new Block(type.getProperties()));

                    }
                }
                RegistrySupplier<Item> item = AbyssalDecor.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().arch$tab(AbyssalDecor.PALETTE_TAB)));
                paletteBlocks.put(name, block);
                paletteItems.put(name, item);
            }
        }
    }
}
