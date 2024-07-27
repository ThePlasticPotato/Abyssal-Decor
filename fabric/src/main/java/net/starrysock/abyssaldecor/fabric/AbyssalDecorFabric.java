package net.starrysock.abyssaldecor.fabric;

import net.starrysock.abyssaldecor.AbyssalDecor;
import net.fabricmc.api.ModInitializer;

public class AbyssalDecorFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        AbyssalDecor.init();
    }
}
