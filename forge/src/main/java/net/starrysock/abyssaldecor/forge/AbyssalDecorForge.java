package net.starrysock.abyssaldecor.forge;

import dev.architectury.platform.forge.EventBuses;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AbyssalDecor.MOD_ID)
public class AbyssalDecorForge {
    public AbyssalDecorForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(AbyssalDecor.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        AbyssalDecor.init();
    }
}
