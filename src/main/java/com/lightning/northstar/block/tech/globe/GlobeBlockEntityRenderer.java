package com.lightning.northstar.block.tech.globe;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import org.jetbrains.annotations.NotNull;

public class GlobeBlockEntityRenderer implements BlockEntityRenderer<GlobeBlockEntity> {

    public GlobeBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(@NotNull GlobeBlockEntity blockEntity, float pPartialTick,
                       @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer,
                       int packedLight,
                       int packedOverlay) {
        // double theta = longitudeFrac * 2 * Math.PI;
        //double phi   = (1.0 - latitudeFrac) * Math.PI;
        //
        //double dx = Math.sin(phi) * Math.cos(theta);
        //double dy = Math.cos(phi);
        //double dz = Math.sin(phi) * Math.sin(theta);
        //
        //// Project direction vector onto cube
        //double ax = Math.abs(dx);
        //double ay = Math.abs(dy);
        //double az = Math.abs(dz);
        //
        //double scale = 1.0 / Math.max(ax, Math.max(ay, az));
        //
        //double x = dx * scale * globeRadius;
        //double y = dy * scale * globeRadius;
        //double z = dz * scale * globeRadius;
    }

}
