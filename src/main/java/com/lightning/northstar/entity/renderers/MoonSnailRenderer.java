package com.lightning.northstar.entity.renderers;

import com.lightning.northstar.content.NorthstarEntityResources;
import com.lightning.northstar.entity.MoonSnailEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MoonSnailRenderer extends GeoEntityRenderer<MoonSnailEntity> {

    public MoonSnailRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(NorthstarEntityResources.SNAIL_MODEL, true));
    }

    @Override
    public RenderType getRenderType(MoonSnailEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(getTextureLocation(animatable));
    }

}
