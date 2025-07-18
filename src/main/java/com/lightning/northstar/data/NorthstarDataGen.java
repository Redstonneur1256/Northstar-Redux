package com.lightning.northstar.data;

import com.lightning.northstar.Northstar;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.Set;

public class NorthstarDataGen {

    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(generator.getPackOutput(),
                event.getLookupProvider(),
                new RegistrySetBuilder().add(Registries.CONFIGURED_FEATURE, NorthstarConfiguredFeatures::bootstrap),
                Set.of(Northstar.MOD_ID)));
    }

}
