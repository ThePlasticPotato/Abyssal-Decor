package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.content.abstraction.barriers.BarrierTieBlock;
import net.starrysock.abyssaldecor.content.abstraction.barriers.TiedBarrierBlock;
import net.starrysock.abyssaldecor.content.abstraction.lamps.DirectionalInteractibleLampBlock;
import net.starrysock.abyssaldecor.content.abstraction.lamps.InteractibleRedstoneLampBlock;
import net.starrysock.abyssaldecor.content.abstraction.lamps.WallMountedInteractibleLampBlock;
import net.starrysock.abyssaldecor.content.lamps.BulkheadLampBlock;
import net.starrysock.abyssaldecor.content.lamps.TubeLampBlock;

public class AbyssalDecorBlocks {

    public static RegistrySupplier<Block> TEST_BLOCK = AbyssalDecor.BLOCKS.register("test_block", () -> new Block(Block.Properties.copy(Blocks.STONE)));

    public static RegistrySupplier<Block> SEABRASS_ORE = AbyssalDecor.BLOCKS.register("seabrass_ore", () -> new FallingBlock(Block.Properties.copy(Blocks.GRAVEL)));

    //section: Lamps
    public static RegistrySupplier<Block> FRESNEL_LAMP = AbyssalDecor.BLOCKS.register("fresnel_lamp", () -> new InteractibleRedstoneLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));

    // Lightbulb [directional], Wall Bulb Lamp [wall mounted], Tube Lamp [tube], Iron Lamp [directional], Flower Lamp [wall mounted], Frosted Lamp [directional], Quartz Lamp [directional], Jade Lamp [directional], Seaglass Lamp [directional], Blaze Lamp [wall mounted], Rainbow Lamp [directional]
    public static RegistrySupplier<Block> LIGHTBULB = AbyssalDecor.BLOCKS.register("lightbulb", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> WALL_BULB_LAMP = AbyssalDecor.BLOCKS.register("wall_bulb_lamp", () -> new WallMountedInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> TUBE_LAMP = AbyssalDecor.BLOCKS.register("tube_lamp", () -> new TubeLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> IRON_LAMP = AbyssalDecor.BLOCKS.register("iron_lamp", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static RegistrySupplier<Block> FLOWER_LAMP = AbyssalDecor.BLOCKS.register("flower_lamp", () -> new WallMountedInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> FROSTED_LAMP = AbyssalDecor.BLOCKS.register("frosted_lamp", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> QUARTZ_LAMP = AbyssalDecor.BLOCKS.register("quartz_lamp", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)));
    public static RegistrySupplier<Block> JADE_LAMP = AbyssalDecor.BLOCKS.register("jade_lamp", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK)));
    public static RegistrySupplier<Block> SEAGLASS_LAMP = AbyssalDecor.BLOCKS.register("seaglass_lamp", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> BLAZE_LAMP = AbyssalDecor.BLOCKS.register("blaze_lamp", () -> new WallMountedInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS)));
    public static RegistrySupplier<Block> RAINBOW_LAMP = AbyssalDecor.BLOCKS.register("rainbow_lamp", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> BULKHEAD_LAMP = AbyssalDecor.BLOCKS.register("bulkhead_lamp", () -> new BulkheadLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static RegistrySupplier<Block> JADE_LANTERN = AbyssalDecor.BLOCKS.register("jade_lantern", () -> new InteractibleRedstoneLampBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK)));
    public static RegistrySupplier<Block> ABYSSAL_LANTERN = AbyssalDecor.BLOCKS.register("abyssal_lantern", () -> new InteractibleRedstoneLampBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE)));
    public static RegistrySupplier<Block> IRON_LANTERN = AbyssalDecor.BLOCKS.register("iron_lantern", () -> new InteractibleRedstoneLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    //end section

    //section : Barriers
    public static RegistrySupplier<Block> VELVET_BARRIER = AbyssalDecor.BLOCKS.register("velvet_barrier", () -> new TiedBarrierBlock(Block.Properties.copy(Blocks.COPPER_BLOCK)));
    public static RegistrySupplier<Block> IRON_BARRIER = AbyssalDecor.BLOCKS.register("iron_barrier", () -> new TiedBarrierBlock(Block.Properties.copy(Blocks.IRON_BLOCK)));
    public static RegistrySupplier<Block> ROPE_BARRIER = AbyssalDecor.BLOCKS.register("rope_barrier", () -> new TiedBarrierBlock(Block.Properties.copy(Blocks.OAK_LOG)));
    public static RegistrySupplier<Block> BARBED_WIRE_BARRIER = AbyssalDecor.BLOCKS.register("barbed_wire_barrier", () -> new TiedBarrierBlock(Block.Properties.copy(Blocks.IRON_BLOCK)));

    public static RegistrySupplier<Block> VELVET_BARRIER_TIE = AbyssalDecor.BLOCKS.register("velvet_barrier_tie", () -> new BarrierTieBlock(Block.Properties.copy(Blocks.RED_WOOL), false));
    public static RegistrySupplier<Block> IRON_BARRIER_TIE = AbyssalDecor.BLOCKS.register("iron_barrier_tie", () -> new BarrierTieBlock(Block.Properties.copy(Blocks.GRAY_WOOL), false));
    public static RegistrySupplier<Block> ROPE_BARRIER_TIE = AbyssalDecor.BLOCKS.register("rope_barrier_tie", () -> new BarrierTieBlock(Block.Properties.copy(Blocks.BROWN_WOOL), false));
    public static RegistrySupplier<Block> BARBED_WIRE_BARRIER_TIE = AbyssalDecor.BLOCKS.register("barbed_wire_barrier_tie", () -> new BarrierTieBlock(Block.Properties.copy(Blocks.IRON_BARS), true));

    public static void register() {
        DeferredRegister<Block> blockRegistry = AbyssalDecor.BLOCKS;

        blockRegistry.register();
    }
}
