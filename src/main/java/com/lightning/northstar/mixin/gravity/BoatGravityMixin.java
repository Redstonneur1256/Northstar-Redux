package com.lightning.northstar.mixin.gravity;

import com.lightning.northstar.Northstar;
import com.lightning.northstar.planet.PlanetOrbitTracker;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Boat.class)
public abstract class BoatGravityMixin extends Entity {

    public BoatGravityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @ModifyConstant(method = "floatBoat",
            constant = @Constant(doubleValue = (double) -0.04f))
    private double northstar$modifyGravity(double constant) {
        PlanetOrbitTracker.PlanetNode planet = Northstar.ORBIT_TRACKER.getPlanet(level());
        if (planet != null) {
            return constant * planet.planet.gravityScale();
        }
        return constant;
    }

}
