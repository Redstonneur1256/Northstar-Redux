package com.lightning.northstar.mixin.gravity;

import com.lightning.northstar.Northstar;
import com.lightning.northstar.planet.PlanetOrbitTracker;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemEntity.class)
public abstract class ItemEntityGravityMixin extends Entity {

    public ItemEntityGravityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = -0.04D))
    private double northstar$modifyGravity(double constant) {
        PlanetOrbitTracker.PlanetNode planet = Northstar.ORBIT_TRACKER.getPlanet(level());
        if (planet != null) {
            return constant * planet.planet.gravityScale();
        }

        // FIXME: what?
        //if (PLANET_GRAV == MARS_GRAV && level().getRainLevel(0) > 0 && level().getRawBrightness(entity.blockPosition(), 0) == 15 && !isSpectator()) {
        //    setDeltaMovement(velocity.x() + 0.01, velocity.y(), velocity.z() - 0.01);
        //}
        return constant;
    }

}
