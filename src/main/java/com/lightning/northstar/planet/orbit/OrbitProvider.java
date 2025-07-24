package com.lightning.northstar.planet.orbit;

import com.mojang.serialization.Codec;
import com.simibubi.create.api.registry.SimpleRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3d;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public interface OrbitProvider {

    // TODO: worth an actual registry?
    Map<ResourceLocation, Codec<? extends OrbitProvider>> REGISTRY = new ConcurrentHashMap<>();
    Codec<OrbitProvider> CODEC = ResourceLocation.CODEC.dispatch(
            OrbitProvider::codec,
            name -> Objects.requireNonNull(REGISTRY.get(name), "Couldn't find orbit codec \"" + name + "\""));

    ResourceLocation codec();

    double approximateRadius();

    Vector3d calculatePosition(double deltaDays, Vector3d dest);

}
