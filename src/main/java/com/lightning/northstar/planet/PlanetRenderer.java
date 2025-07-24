package com.lightning.northstar.planet;

import com.lightning.northstar.Northstar;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.util.Mth;
import org.joml.*;

import java.lang.Math;

public class PlanetRenderer {

    public static void render(ClientLevel level, PoseStack pose, Matrix4f projectionMatrix, Camera camera, float partialTick) {
        PlanetOrbitTracker orbitTracker = Northstar.ORBIT_TRACKER;

        PlanetOrbitTracker.PlanetNode currentPlanetNode = orbitTracker.getPlanet(level.dimensionTypeId());
        if (currentPlanetNode == null) {
            // TODO: maybe assign multiple dimensions per planet and add compatibility with other mods so that
            //  overworld-like dimensions such as the aether also gets the stars rendered (maybe tags, northstar:overworld_like)
            return; // probably the nether, the end or another dimension, don't render anything
        }
        NorthstarPlanet currentPlanet = currentPlanetNode.planet;

        double rotationProgress = (orbitTracker.getDeltaDays() % currentPlanet.rotationPeriodDays()) / currentPlanet.rotationPeriodDays();

        // circumference is in kilometers but use meters here so that the earth is only
        //  40.000 blocks instead of 40.000.000 blocks.
        double lat = Mth.positiveModulo(camera.getPosition().z() / currentPlanet.circumference() + 0.5, 1) * Math.PI - Math.PI / 2; // north, south
        double lon = Mth.positiveModulo(camera.getPosition().x() / currentPlanet.circumference(), 1) * Math.PI * 2; // east, west

        Minecraft.getInstance().getWindow().setTitle("Lat: %.06f, Lon: %.06f".formatted(Math.toDegrees(lat), Math.toDegrees(lon)));

        Quaterniond viewRotation = new Quaterniond()
                .rotateX(Mth.HALF_PI) // flip Y and Z because planets rotate around the Y axis but we want them to rotate around the Z axis when viewed from the equator
                .rotateLocalZ(lon + rotationProgress * Mth.TWO_PI) // rotate the planet itself and also ourselves around the planet
                .rotateLocalX(lat); // rotate around the latitude

        // get our position in the universe
        Vector3d universePosition = orbitTracker.getOrbitPosition(currentPlanetNode.key);

        // view direction from our planet to the target planet
        Vector3d direction = new Vector3d();
        Matrix4f mat = new Matrix4f();

        ShaderInstance shader = RenderSystem.getShader();
        FogRenderer.setupNoFog();
        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        GameRenderer.getPositionTexShader().PROJECTION_MATRIX.set(projectionMatrix);

        // TODO: sort by distance from current planet for rendering
        for (PlanetOrbitTracker.PlanetNode node : orbitTracker.getPlanets().values()) {
            if (node.key.equals(currentPlanetNode.key)) {
                continue; // don't render the planet we're on
            }
            NorthstarPlanet planet = node.planet;
            Vector3d targetPosition = node.position;

            direction.set(targetPosition)
                    .sub(universePosition)
                    .rotate(viewRotation);

            double distanceAU = direction.length();
            direction.mul(1.0 / distanceAU); // normalize for rendering

            BufferBuilder buffer = Tesselator.getInstance().getBuilder();

            RenderSystem.setShaderTexture(0, planet.textures().get(PlanetSprite.CLOSE).withPrefix("textures/"));

            buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

            pose.pushPose();

            mat.rotationTowards(direction.get(new Vector3f()), new Vector3f(0, 1, 0));
            pose.mulPoseMatrix(mat);

            pose.translate(0, 0, -1); // translate (0, -1, 0)
            renderQuad(pose, buffer, 0.3f); // render a sprite with normal (0, 1, 0)

            BufferUploader.drawWithShader(buffer.end());

            pose.popPose();
        }

        RenderSystem.setShader(() -> shader);
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
    }

    public static void renderQuad(PoseStack pose, VertexConsumer vc, float size) {
        Matrix4f matrix = pose.last().pose();
        float halfSize = size / 2f;
        vc.vertex(matrix, -halfSize, halfSize, 0).uv(0, 0).endVertex();
        vc.vertex(matrix, -halfSize, -halfSize, 0).uv(0, 1).endVertex();
        vc.vertex(matrix, halfSize, -halfSize, 0).uv(1, 1).endVertex();
        vc.vertex(matrix, halfSize, halfSize, 0).uv(1, 0).endVertex();
    }

}
