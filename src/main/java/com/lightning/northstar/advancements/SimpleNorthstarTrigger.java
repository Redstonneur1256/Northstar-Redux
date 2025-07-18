package com.lightning.northstar.advancements;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class SimpleNorthstarTrigger extends CriterionTriggerBase2ElectricBoogaloo<SimpleNorthstarTrigger.Instance> {

    public SimpleNorthstarTrigger(String id) {
        super(id);
    }

    @Override
    public Instance createInstance(JsonObject json, DeserializationContext context) {
        return new Instance(getId());
    }

    public void trigger(ServerPlayer player) {
        super.trigger(player, null);
    }

    public Instance instance() {
        return new Instance(getId());
    }

    public static class Instance extends CriterionTriggerBase2ElectricBoogaloo.Instance {

        public Instance(ResourceLocation criterion) {
            super(criterion, ContextAwarePredicate.ANY);
        }

        @Override
        protected boolean test(@Nullable List<Supplier<Object>> suppliers) {
            return true;
        }
    }

}
