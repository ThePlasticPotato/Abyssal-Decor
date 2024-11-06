package net.starrysock.abyssaldecor.forge;

import net.starrysock.abyssaldecor.AbyssalDecorExpectPlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class AbyssalDecorExpectPlatformImpl {
    /**
     * This is our actual method to {@link AbyssalDecorExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
