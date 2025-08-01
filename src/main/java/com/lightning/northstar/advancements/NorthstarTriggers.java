package com.lightning.northstar.advancements;

import net.minecraft.advancements.CriteriaTriggers;

import java.util.LinkedList;
import java.util.List;

public class NorthstarTriggers {

    private static final List<CriterionTriggerBase2ElectricBoogaloo<?>> triggers = new LinkedList<>();

    public static SimpleNorthstarTrigger addSimple(String id) {
        return add(new SimpleNorthstarTrigger(id));
    }

    private static <T extends CriterionTriggerBase2ElectricBoogaloo<?>> T add(T instance) {
        triggers.add(instance);
        return instance;
    }

    public static void register() {
        triggers.forEach(CriteriaTriggers::register);
    }

}
