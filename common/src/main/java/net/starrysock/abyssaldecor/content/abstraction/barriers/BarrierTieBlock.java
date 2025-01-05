package net.starrysock.abyssaldecor.content.abstraction.barriers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.registry.AbyssalVoxelShapes;

public class BarrierTieBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

    public static BooleanProperty DANGEROUS = BooleanProperty.create("dangerous");

    public BarrierTieBlock(Properties properties, boolean isDangerous) {
        super(properties);
        this.registerDefaultState((BlockState) this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(DANGEROUS, isDangerous).setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(BlockStateProperties.WATERLOGGED);
        builder.add(DANGEROUS);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return AbyssalVoxelShapes.BARRIER_TIE;
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (blockState.getValue(DANGEROUS)) entity.hurt(level.damageSources().thorns(null), 1.0F);
        super.entityInside(blockState, level, blockPos, entity);
    }
}
