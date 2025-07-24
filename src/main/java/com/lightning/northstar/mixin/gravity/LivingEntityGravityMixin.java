package com.lightning.northstar.mixin.gravity;

import com.lightning.northstar.Northstar;
import com.lightning.northstar.planet.PlanetOrbitTracker;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityGravityMixin extends Entity {

    public LivingEntityGravityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/attributes/AttributeInstance;getValue()D"))
    private double northstar$modifyGravity(AttributeInstance instance) {
        PlanetOrbitTracker.PlanetNode planet = Northstar.ORBIT_TRACKER.getPlanet(level());
        if (planet != null) {
            return instance.getValue() * planet.planet.gravityScale();
        }
        return instance.getValue();
    }

    @ModifyVariable(method = "calculateFallDamage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private float northstar$modifyFallDamageGravity(float fallDistance) {
        PlanetOrbitTracker.PlanetNode planet = Northstar.ORBIT_TRACKER.getPlanet(level());
        if (planet != null) {
            return fallDistance * planet.planet.gravityScale();
        }
        return fallDistance;
    }

}
