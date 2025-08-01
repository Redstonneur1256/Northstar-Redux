package com.lightning.northstar.world.features.trunkplacers;

import com.lightning.northstar.Northstar;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class AlienTrunkPlacerType {

    public static final TrunkPlacerType<CoilerTrunkPlacer> COILER_TRUNK_PLACER = register("coiler_trunk_placer", CoilerTrunkPlacer.CODEC);
    public static final TrunkPlacerType<WilterTrunkPlacer> WILTER_TRUNK_PLACER = register("wilter_trunk_placer", WilterTrunkPlacer.CODEC);
    public static final TrunkPlacerType<ArgyreTrunkPlacer> ARGYRE_TRUNK_PLACER = register("argyre_trunk_placer", ArgyreTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String key, Codec<P> codec) {
        return Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, Northstar.asResource(key), new TrunkPlacerType<>(codec));
    }

}
