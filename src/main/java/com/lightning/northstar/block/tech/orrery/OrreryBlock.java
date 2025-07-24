package com.lightning.northstar.block.tech.orrery;

import com.lightning.northstar.content.NorthstarBlockEntityTypes;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class OrreryBlock extends Block implements IBE<OrreryBlockEntity> {

    public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D);

    public OrreryBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public Class<OrreryBlockEntity> getBlockEntityClass() {
        return OrreryBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends OrreryBlockEntity> getBlockEntityType() {
        return NorthstarBlockEntityTypes.ORRERY.get();
    }

}
