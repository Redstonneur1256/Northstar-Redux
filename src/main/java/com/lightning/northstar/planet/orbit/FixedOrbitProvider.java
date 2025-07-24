package com.lightning.northstar.planet.orbit;

import com.lightning.northstar.Northstar;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import org.joml.Vector3d;
import org.joml.Vector3f;

public record FixedOrbitProvider(Vector3f position) implements OrbitProvider {

    public static final ResourceLocation NAME = Northstar.asResource("fixed");
    public static final Codec<FixedOrbitProvider> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ExtraCodecs.VECTOR3F.fieldOf("position").forGetter(FixedOrbitProvider::position)
    ).apply(instance, FixedOrbitProvider::new));

    @Override
    public ResourceLocation codec() {
        return NAME;
    }

    @Override
    public double approximateRadius() {
        return position.length();
    }

    @Override
    public Vector3d calculatePosition(double deltaDays, Vector3d dest) {
        return position.get(dest);
    }

}
