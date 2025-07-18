package com.lightning.northstar.world.features;

import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.function.Supplier;

public class FeatureRegisterer {
    public static <T extends Feature<?>> Supplier<T> registerFeature(String name, Supplier<T> feature) {
        throw new AssertionError();
    }
}
