package net.starrysock.abyssaldecor;

import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.starrysock.abyssaldecor.registry.*;

import java.util.HashMap;
import java.util.function.Supplier;

public class AbyssalDecor {
    public static final String MOD_ID = "abyssaldecor";

    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    // Registering a new creative tab
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static final RegistrySupplier<CreativeModeTab> EXAMPLE_TAB = TABS.register("example_tab", () ->
            CreativeTabRegistry.create(Component.translatable("itemGroup." + MOD_ID + ".example_tab"),
                    () -> new ItemStack(AbyssalDecor.EXAMPLE_ITEM.get())));

    public static final RegistrySupplier<CreativeModeTab> PALETTE_TAB = TABS.register("palette_tab", () ->
            CreativeTabRegistry.create(Component.translatable("itemGroup." + MOD_ID + ".palette_tab"),
                    () -> new ItemStack(AbyssalDecor.EXAMPLE_ITEM.get())));

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final RegistrySupplier<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () ->
            new Item(new Item.Properties().arch$tab(AbyssalDecor.EXAMPLE_TAB)));

    public static final DeferredRegister<DamageType> DAMAGE_TYPES = DeferredRegister.create(MOD_ID, Registries.DAMAGE_TYPE);
    
    public static void init() {
        HashMap<String, PaletteType> paletteMap = new HashMap<>();

        // add palettes here
        paletteMap.put("seabrass", PaletteType.METAL);
        paletteMap.put("deepbronze", PaletteType.METAL);
        paletteMap.put("whitewood", PaletteType.WOOD);
        paletteMap.put("blackwood", PaletteType.WOOD);
        paletteMap.put("cinnamon", PaletteType.WOOD);
        paletteMap.put("pearl", PaletteType.PEARL);
        paletteMap.put("blackpearl", PaletteType.PEARL);
        paletteMap.put("fresnel", PaletteType.GLASSLIKE);
        paletteMap.put("vermillion", PaletteType.FRAMED_GLASSLIKE);
        paletteMap.put("prismarine_crystal", PaletteType.GLASSLIKE);
        paletteMap.put("crystalized_glowstone", PaletteType.FRAMED_GLASSLIKE);
        paletteMap.put("starglass", PaletteType.GLASSLIKE);
        paletteMap.put("pitchglass", PaletteType.FRAMED_GLASSLIKE);
        paletteMap.put("blood_coral", PaletteType.CORAL);

        //

        BlockPaletteRegistryHelper.init(paletteMap);
        TABS.register();
        AbyssalDecorBlocks.register();
        AbyssalDecorItems.register();

        System.out.println(AbyssalDecorExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
