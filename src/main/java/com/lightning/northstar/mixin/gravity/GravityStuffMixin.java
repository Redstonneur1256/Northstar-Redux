package com.lightning.northstar.mixin.gravity;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
public class GravityStuffMixin {

    /*@Inject(method = "travel", at = @At("TAIL"))
    public void northstar$travel(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (fall_disabled > 0) {
            fall_disabled--;
            entity.fallDistance = 0;
        }
        if (RocketHandler.isInRocket(entity) && entity.getY() > 1500) {
            fall_disabled = 400;
        }
        Vec3 velocity = entity.getDeltaMovement();
        boolean isInOrbit = NorthstarPlanets.isInOrbit(entity.level().dimension());
        if (entity.level().dimension() == NorthstarDimensions.MARS_DIM_KEY) {
            PLANET_GRAV = MARS_GRAV;
        } else if (entity.level().dimension() == NorthstarDimensions.VENUS_DIM_KEY) {
            PLANET_GRAV = VENUS_GRAV;
        } else if (entity.level().dimension() == NorthstarDimensions.MOON_DIM_KEY) {
            PLANET_GRAV = MOON_GRAV;
        } else if (entity.level().dimension() == NorthstarDimensions.MERCURY_DIM_KEY) {
            PLANET_GRAV = MERCURY_GRAV;
        } else if (isInOrbit) {
            PLANET_GRAV = OUTER_MOON_GRAV;
        } else {
            PLANET_GRAV = EARTH_GRAV;
        }

        if (entity.isFallFlying() || entity.isInFluidType()) {
            PLANET_GRAV = EARTH_GRAV;
        }
        if (!entity.isNoGravity() && !entity.isInWater() && !entity.isInLava() && !entity.hasEffect(MobEffects.SLOW_FALLING)) {
            float dust_push = 0;
            if (entity.level().getRainLevel(0) > 0 && entity.level().getRawBrightness(entity.blockPosition(), -1) == 16 && !entity.isSpectator() && (entity.level().dimension() == NorthstarDimensions.MARS_DIM_KEY)
                    && entity.level().isInWorldBounds(entity.blockPosition()) && !RocketHandler.isInRocket(entity)) {
                dust_push = 0.005f;
            }
            if (entity instanceof Player ply) {
                if (ply.isCreative()) {
                    dust_push = 0;
                }
            }

            double newGrav = CONSTANT * PLANET_GRAV;
            float crouchPush = 0;
            if (!isInOrbit) {
                entity.setDeltaMovement(velocity.x() + dust_push, velocity.y() + (CONSTANT - newGrav), velocity.z() - dust_push);
            } else {
                if (entity.isCrouching()) {
                    crouchPush = 0.05f;
                }
                float vel_y = (float) Mth.clamp(velocity.y(), -0.3, 15);
                entity.setDeltaMovement(velocity.x() + dust_push, vel_y + (CONSTANT - newGrav) - crouchPush, velocity.z() - dust_push);
            }
        }
        if (isInOrbit) {
            if (entity.getY() < 0 && !entity.level().isClientSide) {
                if (entity.level().dimension() == NorthstarDimensions.EARTH_ORBIT_DIM_KEY) {
                    ServerLevel destLevel = entity.level().getServer().getLevel(Level.OVERWORLD);
                    if (entity instanceof ServerPlayer player) {
                        changePlayerDimension(destLevel, player, new PortalForcer(destLevel));
                    } else {
                        changeDimensionCustom(destLevel, entity, new PortalForcer(destLevel));
                    }
                }
            }
        }
    }*/

}
