package com.lightning.northstar.block.tech.orrery;

import com.lightning.northstar.Northstar;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class OrreryBlockEntity extends BlockEntity {

    public ResourceLocation root = Northstar.asResource("sun");

    public OrreryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);

        root = tag.contains("rootPlanet") ? ResourceLocation.tryParse(tag.getString("rootPlanet")) : null;
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);

        if (root != null) {
            tag.putString("rootPlanet", root.toString());
        }
    }

}
