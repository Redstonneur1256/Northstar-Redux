package com.lightning.northstar.content;

import com.lightning.northstar.planet.orbit.FixedOrbitProvider;
import com.lightning.northstar.planet.orbit.OrbitProvider;
import com.lightning.northstar.planet.orbit.PhysicalOrbitProvider;
import com.lightning.northstar.planet.orbit.SimpleOrbitProvider;

public class NorthstarOrbitProviders {

    public static void register() {
        OrbitProvider.REGISTRY.put(FixedOrbitProvider.NAME, FixedOrbitProvider.CODEC);
        OrbitProvider.REGISTRY.put(PhysicalOrbitProvider.NAME, PhysicalOrbitProvider.CODEC);
        OrbitProvider.REGISTRY.put(SimpleOrbitProvider.NAME, SimpleOrbitProvider.CODEC);
    }

}
