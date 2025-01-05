package net.starrysock.abyssaldecor.registry;

import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AbyssalVoxelShapes {

    public static VoxelShape BARRIER_BASE = Shapes.create(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    public static VoxelShape BARRIER_TIP = Shapes.create(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D);
    public static VoxelShape BARRIER_TIE = Shapes.create(-8.0D, -3.0D, 7.0D, 24.0D, 6.0D, 9.0D);
}
