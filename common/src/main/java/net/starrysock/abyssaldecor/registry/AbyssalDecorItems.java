package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.Item;
import net.starrysock.abyssaldecor.AbyssalDecor;

public class AbyssalDecorItems {

    public static final RegistrySupplier<Item> TEST_ITEM = AbyssalDecor.ITEMS.register("test_item", () -> new Item(new Item.Properties()));

    public static void register() {
        DeferredRegister<Item> itemRegistry = AbyssalDecor.ITEMS;

        itemRegistry.register();
    }
}
