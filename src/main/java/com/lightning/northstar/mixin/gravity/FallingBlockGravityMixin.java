package com.lightning.northstar.mixin.gravity;

import com.lightning.northstar.Northstar;
import com.lightning.northstar.planet.PlanetOrbitTracker;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockGravityMixin extends Entity {

    public FallingBlockGravityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = -0.04))
    private double northstar$modifyGravity(double constant) {
        PlanetOrbitTracker.PlanetNode planet = Northstar.ORBIT_TRACKER.getPlanet(level());
        if (planet != null) {
            return constant * planet.planet.gravityScale();
        }
        return constant;
    }

}
