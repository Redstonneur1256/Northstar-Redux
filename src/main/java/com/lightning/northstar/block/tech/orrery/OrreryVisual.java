package com.lightning.northstar.block.tech.orrery;

import com.lightning.northstar.Northstar;
import com.lightning.northstar.block.tech.NorthstarPartialModels;
import com.lightning.northstar.planet.NorthstarPlanet;
import com.lightning.northstar.planet.PlanetOrbitTracker;
import com.mojang.math.Transformation;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.BakedModelBuilder;
import dev.engine_room.flywheel.lib.visual.AbstractBlockEntityVisual;
import dev.engine_room.flywheel.lib.visual.SimpleDynamicVisual;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.model.SimpleModelState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class OrreryVisual extends AbstractBlockEntityVisual<OrreryBlockEntity> implements SimpleDynamicVisual {

    private final RotatingInstance shaft;
    private final TransformedInstance pin;
    private final Map<ResourceLocation, TransformedInstance> instances = new HashMap<>();


    public OrreryVisual(VisualizationContext context, OrreryBlockEntity entity, float partialTick) {
        super(context, entity, partialTick);

        shaft = instancerProvider()
                .instancer(AllInstanceTypes.ROTATING, Models.partial(AllPartialModels.SHAFT_HALF))
                .createInstance()
                .setPosition(getVisualPosition())
                .rotateToFace(Direction.SOUTH, Direction.DOWN);

        pin = instancerProvider()
                .instancer(InstanceTypes.TRANSFORMED, Models.partial(NorthstarPartialModels.GLOBE_PIN))
                .createInstance();
    }

    @Override
    public void beginFrame(Context ctx) {
        refresh(ctx.partialTick());
    }

    private void refresh(float partialTick) {
        PlanetOrbitTracker orbitTracker = Northstar.ORBIT_TRACKER;

        List<PlanetOrbitTracker.PlanetNode> system = orbitTracker.getSystem(blockEntity.root);
        if (system == null) {
            // TODO: what if
            //  - system is changed: planets should be deleted and re-created
            //  - no systems are defined (toggleable datapack disabled)
            blockEntity.root = orbitTracker.getRoots().get(0).key;
            system = orbitTracker.getSystem(blockEntity.root);
            if (system == null) {
                return; // huh?
            }
        }

        for (PlanetOrbitTracker.PlanetNode node : system) {
            ResourceLocation location = node.key;
            NorthstarPlanet planet = node.planet;

            Vector3d pos = orbitTracker.getOrreryPosition(location);
            double rotationProgress = (orbitTracker.getDeltaDays() % planet.rotationPeriodDays()) / planet.rotationPeriodDays();

            TransformedInstance visual = instances.computeIfAbsent(location, loc -> createInstance(planet));

            if (level.dimensionTypeId().equals(planet.dimension())) {
                double lat = Mth.positiveModulo(this.pos.getZ() / planet.circumference() + 0.5, 1) * Math.PI - Math.PI / 2; // north, south
                double lon = Mth.positiveModulo(this.pos.getX() / planet.circumference(), 1) * Math.PI * 2; // east, west

                pin.setIdentityTransform()
                        .translate(getVisualPosition())
                        .translate(pos.x, pos.y + 0.5, pos.z)
                        .rotateCentered((float) (rotationProgress * Mth.TWO_PI + lon + Mth.PI), 0, 1, 0)
                        .rotateCentered((float) lat + Mth.HALF_PI, 1, 0, 0)
                        .setChanged();
            }

            visual.setIdentityTransform()
                    .translate(getVisualPosition())
                    .translate(pos.x, pos.y + 0.5, pos.z)
                    .rotateCentered((float) rotationProgress * Mth.TWO_PI, 0, 1, 0);
            visual.setChanged();
        }
    }

    private TransformedInstance createInstance(NorthstarPlanet planet) {
        ModelBakery bakery = Minecraft.getInstance().getModelManager().getModelBakery();
        Function<Material, TextureAtlasSprite> sprites = Material::sprite;
        ModelBaker baker = new ModelBaker() {
            @Override
            public @NotNull UnbakedModel getModel(@NotNull ResourceLocation location) {
                return bakery.getModel(location);
            }

            @Override
            public @Nullable BakedModel bake(@NotNull ResourceLocation location, @NotNull ModelState transform) {
                return bake(location, transform, getModelTextureGetter());
            }

            @Override
            public @Nullable BakedModel bake(@NotNull ResourceLocation location, @NotNull ModelState state, @NotNull Function<Material, TextureAtlasSprite> sprites) {
                return getModel(location).bake(this, sprites, state, location);
            }

            @Override
            public @NotNull Function<Material, TextureAtlasSprite> getModelTextureGetter() {
                return sprites;
            }
        };
        BakedModel model = baker.bake(planet.orreryModel(), new SimpleModelState(Transformation.identity(), false), baker.getModelTextureGetter());
        assert model != null;

        TransformedInstance instance = instancerProvider()
                .instancer(InstanceTypes.TRANSFORMED, BakedModelBuilder.create(model).build())
                .createInstance();
        relight(instance);
        return instance;
    }

    @Override
    public void updateLight(float partialTick) {
        relight(shaft, pin);
        instances.values().forEach(this::relight);
    }

    @Override
    protected void _delete() {
        shaft.delete();
        pin.delete();
        instances.values().forEach(TransformedInstance::delete);
    }

    @Override
    public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
        consumer.accept(shaft);
        consumer.accept(pin);
        instances.values().forEach(consumer);
    }

}
