package com.lightning.northstar.mixin;

import com.lightning.northstar.world.dimension.NorthstarPlanets;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.WalkAnimationState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {

    @Unique
    private T northstar$capturedEntity;

    private LivingEntityRendererMixin(Context pContext) {
        super(pContext);
    }

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At("HEAD"))
    private void captureEntity(T entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        northstar$capturedEntity = entity;
    }

    @Redirect(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/WalkAnimationState;speed(F)F"))
    private float changeWalkAnimationSpeed(WalkAnimationState instance, float pPartialTick) {
        T entity = northstar$capturedEntity;
        float gravityMultiplier = entity.onGround() ? 1 :
                Mth.clamp((float) NorthstarPlanets.getGravMultiplier(entity.level().dimension()), 0.25f, 1f);

        if (!entity.onGround() && gravityMultiplier < 0.7 && entity.isInWater() && !entity.isVisuallySwimming() && !entity.isFallFlying()) {
            gravityMultiplier *= 1.2f;
        }

        return instance.speed(pPartialTick) * gravityMultiplier;
    }

}
