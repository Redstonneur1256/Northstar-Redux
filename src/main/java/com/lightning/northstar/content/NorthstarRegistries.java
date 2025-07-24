package com.lightning.northstar.content;

import com.lightning.northstar.Northstar;
import com.lightning.northstar.planet.NorthstarPlanet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class NorthstarRegistries {

    public static final ResourceKey<Registry<NorthstarPlanet>> PLANETS = ResourceKey.createRegistryKey(Northstar.asResource("planets"));

}
