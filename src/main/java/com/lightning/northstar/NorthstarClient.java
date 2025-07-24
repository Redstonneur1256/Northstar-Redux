package com.lightning.northstar;

import com.lightning.northstar.block.tech.rocket_controls.RocketControlsHandler;
import com.lightning.northstar.item.armor.RemainingOxygenOverlay;
import com.lightning.northstar.particle.NorthstarParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(Dist.CLIENT)
public class NorthstarClient {

    public static void onCtorClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        modEventBus.addListener(NorthstarParticles::registerFactories);
    }

    @SubscribeEvent
    public static void onTick(ClientTickEvent event) {
        if (event.phase == Phase.START) {
            RocketControlsHandler.tick();
        }
    }

    @SubscribeEvent
    public static void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == Phase.START) {
            ClientLevel level = Minecraft.getInstance().level;
            if (level != null) {
                long baseDays = level.getDayTime() / 24000L;
                double days = baseDays + (level.getDayTime() % 24000L + 6000L + event.renderTickTime) / 24000.0;
                Northstar.ORBIT_TRACKER.updateOrbits(days);
            }
        }
    }

    @EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class ModBusEvents {
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAbove(VanillaGuiOverlay.AIR_LEVEL.id(), "remaining_oxygen", RemainingOxygenOverlay.INSTANCE);
        }
    }

}
