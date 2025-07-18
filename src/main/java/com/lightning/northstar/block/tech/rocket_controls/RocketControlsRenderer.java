package com.lightning.northstar.block.tech.rocket_controls;

import com.lightning.northstar.block.tech.NorthstarPartialModels;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.render.ContraptionMatrices;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;
import com.simibubi.create.foundation.virtualWorld.VirtualRenderWorld;
import dev.engine_room.flywheel.lib.transform.TransformStack;
import net.createmod.catnip.math.AngleHelper;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

public class RocketControlsRenderer extends SafeBlockEntityRenderer<RocketControlsBlockEntity> {

    public RocketControlsRenderer(BlockEntityRendererProvider.Context context) {
    }

    public static void render(MovementContext context, VirtualRenderWorld renderWorld, ContraptionMatrices matrices,
                              MultiBufferSource buffer, float equipAnimation, float firstLever, float secondLever) {
        BlockState state = context.state;
        Direction facing = state.getValue(RocketControlsBlock.FACING);

        float hAngle = 180 + AngleHelper.horizontalAngle(facing);
        PoseStack ms = matrices.getModel();
        double yOffset = Mth.lerp(equipAnimation * equipAnimation, -0.15f, 0.05f);

        float vAngle = Mth.clamp(firstLever * 70 - 25, -60, 60);
        SuperByteBuffer lever = CachedBuffers.partial(NorthstarPartialModels.CONTROL_LEVER, state);
        ms.pushPose();
        TransformStack.of(ms)
                .center()
                .rotateY(hAngle)
                .translate(0, 0, 4 / 16f)
                .rotateX(vAngle - 60)
                .translate(0, yOffset, 0)
                .rotateX(60)
                .uncenter()
                .translate(0, -2 / 16f, -3 / 16f);
        lever.transform(ms)
                //.light(matrices.getWorld(), ContraptionRenderDispatcher.getContraptionWorldLight(context, renderWorld))
                .renderInto(matrices.getViewProjection(), buffer.getBuffer(RenderType.solid()));
        ms.popPose();

    }

    @Override
    protected void renderSafe(RocketControlsBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource bufferSource, int light, int overlay) {
        BlockState state = be.getBlockState();
        Direction facing = state.getValue(RocketControlsBlock.FACING);

        float hAngle = 180 + AngleHelper.horizontalAngle(facing);
        double yOffset = Mth.lerp(1 * 1, -0.15f, 0.05f);

        float vAngle = (float) Mth.clamp(1 * 70 - 25, -60, 60);
        SuperByteBuffer lever = CachedBuffers.partial(NorthstarPartialModels.CONTROL_LEVER, state);
        ms.pushPose();
        TransformStack.of(ms)
                .center()
                .rotateY(hAngle)
                .translate(0, 0, 4 / 16f)
                .rotateX(vAngle - 60)
                .translate(0, yOffset, 0)
                .rotateX(60)
                .uncenter()
                .translate(0, -2 / 16f, -3 / 16f);
        lever.transform(ms);
        ms.popPose();
    }

}
