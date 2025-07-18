package com.lightning.northstar.block.tech.cogs;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visual.BlockEntityVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.minecraft.core.Direction;

import java.util.function.Consumer;

public record SpaceCogVisual(PartialModel small, PartialModel big) {

    public BlockEntityVisual<BracketedKineticBlockEntity> create(VisualizationContext context, BracketedKineticBlockEntity blockEntity, float partialTick) {
        if (ICogWheel.isLargeCog(blockEntity.getBlockState())) {
            return new LargeCogVisual(context, blockEntity, partialTick, big);
        }
        return new SingleAxisRotatingVisual<>(context, blockEntity, partialTick, Models.partial(small));
    }

    // Create, please give us a constructor with the model.
    // Copy and paste of BracketedKineticBlockEntityVisual$LargeCogVisual
    public static class LargeCogVisual extends SingleAxisRotatingVisual<BracketedKineticBlockEntity> {

        protected final RotatingInstance additionalShaft;

        private LargeCogVisual(VisualizationContext context, BracketedKineticBlockEntity blockEntity, float partialTick, PartialModel model) {
            super(context, blockEntity, partialTick, Models.partial(model));

            Direction.Axis axis = KineticBlockEntityRenderer.getRotationAxisOf(blockEntity);

            additionalShaft = instancerProvider().instancer(AllInstanceTypes.ROTATING, Models.partial(AllPartialModels.COGWHEEL_SHAFT))
                    .createInstance();

            additionalShaft.rotateToFace(axis)
                    .setup(blockEntity)
                    .setRotationOffset(BracketedKineticBlockEntityRenderer.getShaftAngleOffset(axis, pos))
                    .setPosition(getVisualPosition())
                    .setChanged();
        }

        @Override
        public void update(float pt) {
            super.update(pt);
            additionalShaft.setup(blockEntity)
                    .setRotationOffset(BracketedKineticBlockEntityRenderer.getShaftAngleOffset(rotationAxis(), pos))
                    .setChanged();
        }

        @Override
        public void updateLight(float partialTick) {
            super.updateLight(partialTick);
            relight(additionalShaft);
        }

        @Override
        protected void _delete() {
            super._delete();
            additionalShaft.delete();
        }

        @Override
        public void collectCrumblingInstances(Consumer<Instance> consumer) {
            super.collectCrumblingInstances(consumer);
            consumer.accept(additionalShaft);
        }
    }

}
