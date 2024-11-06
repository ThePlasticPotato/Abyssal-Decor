package net.starrysock.abyssaldecor.fabric;

import net.starrysock.abyssaldecor.AbyssalDecorExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class AbyssalDecorExpectPlatformImpl {
    /**
     * This is our actual method to {@link AbyssalDecorExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
