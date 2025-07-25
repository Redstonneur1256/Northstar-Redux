package com.lightning.northstar;

import com.lightning.northstar.advancements.NorthstarAdvancements;
import com.lightning.northstar.advancements.NorthstarTriggers;
import com.lightning.northstar.block.tech.NorthstarPartialModels;
import com.lightning.northstar.block.tech.astronomy_table.AstronomyTableScreen;
import com.lightning.northstar.block.tech.rocket_station.RocketStationScreen;
import com.lightning.northstar.block.tech.telescope.TelescopeScreen;
import com.lightning.northstar.client.renderer.armor.SpaceSuitLayerRenderer;
import com.lightning.northstar.content.*;
import com.lightning.northstar.contraptions.RocketHandler;
import com.lightning.northstar.data.NorthstarDataGen;
import com.lightning.northstar.entity.*;
import com.lightning.northstar.item.NorthstarEnchantments;
import com.lightning.northstar.item.NorthstarPotions;
import com.lightning.northstar.item.NorthstarRecipeTypes;
import com.lightning.northstar.particle.NorthstarParticles;
import com.lightning.northstar.ponder.NorthstarPonderPlugin;
import com.lightning.northstar.world.OxygenStuff;
import com.lightning.northstar.world.TemperatureStuff;
import com.lightning.northstar.world.dimension.NorthstarDimensions;
import com.lightning.northstar.world.dimension.NorthstarPlanets;
import com.lightning.northstar.world.features.NorthstarFeatures;
import com.lightning.northstar.world.features.trunkplacers.NorthstarTrunkPlacerTypes;
import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.createmod.catnip.lang.FontHelper;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(Northstar.MOD_ID)
public class Northstar {

    // Define mod id in a common place for everything to reference
    public static final double GRAV_CONSTANT = 0.08;
    public static final double EARTH_GRAV = 1;
    public static final double MARS_GRAV = 0.37;
    public static final double VENUS_GRAV = 0.89;

    public static final String MOD_ID = "northstar";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    static {
        REGISTRATE.setTooltipModifierFactory(item -> new ItemDescription.Modifier(item, new FontHelper.Palette(TooltipHelper.styleFromColor(0x9ba4ae), TooltipHelper.styleFromColor(0x80afd2))).andThen(TooltipModifier.mapNull(KineticStats.create(item))));
    }


    private void onRegister(RegisterEvent evt) {
        NorthstarContraptionTypes.register();
    }

    public Northstar(FMLJavaModLoadingContext modContext) {
        IEventBus modEventBus = modContext.getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        GeckoLib.initialize();
        REGISTRATE.registerEventListeners(modEventBus);
        modEventBus.addListener(this::onRegister);

        NorthstarCreativeModeTab.register(modEventBus);
        NorthstarItems.register();
        NorthstarBlocks.register(modEventBus);
        NorthstarBlockEntityTypes.register(modEventBus);
        NorthstarPotions.register(modEventBus);
        NorthstarEnchantments.register();
        NorthstarTechBlocks.register();
        NorthstarFeatures.register(modEventBus);
        NorthstarRecipeTypes.register(modEventBus);
        NorthstarParticles.register(modEventBus);
        NorthstarSounds.register(modEventBus);
        NorthstarMenuTypes.register(modEventBus);
        NorthstarPlanets.register();
        NorthstarDimensions.register();
        NorthstarEntityTypes.register(modEventBus);
        NorthstarFluids.register();

        NorthstarTrunkPlacerTypes.register(modEventBus);
        NorthstarPartialModels.register();

        OxygenStuff.register();
        TemperatureStuff.register();

        RocketHandler.register();

        PonderIndex.addPlugin(new NorthstarPonderPlugin());

        modEventBus.addListener(Northstar::init);
        modEventBus.addListener(this::registerSpawnPlacements);
        modEventBus.addListener(NorthstarDataGen::gatherData);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> NorthstarClient.onCtorClient(modEventBus, forgeEventBus));


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void init(FMLCommonSetupEvent event) {
        NorthstarPackets.registerPackets();

        event.enqueueWork(() -> {
            NorthstarAdvancements.register();
            NorthstarTriggers.register();
        });
    }

    private void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(NorthstarEntityTypes.MARS_WORM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MarsWormEntity::wormSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(NorthstarEntityTypes.MARS_TOAD.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MarsToadEntity::toadSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(NorthstarEntityTypes.MARS_COBRA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MarsCobraEntity::cobraSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(NorthstarEntityTypes.MARS_MOTH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MarsMothEntity::mothSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);

        event.register(NorthstarEntityTypes.VENUS_MIMIC.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                VenusMimicEntity::mimicSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(NorthstarEntityTypes.VENUS_SCORPION.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                VenusScorpionEntity::scorpionSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(NorthstarEntityTypes.VENUS_STONE_BULL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                VenusStoneBullEntity::stoneBullSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(NorthstarEntityTypes.VENUS_VULTURE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                VenusVultureEntity::vultureSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);

        event.register(NorthstarEntityTypes.MOON_SNAIL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MoonSnailEntity::snailSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(NorthstarEntityTypes.MOON_LUNARGRADE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MoonLunargradeEntity::lunargradeSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(NorthstarEntityTypes.MOON_EEL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MoonEelEntity::eelSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);

        event.register(NorthstarEntityTypes.MERCURY_RAPTOR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MercuryRaptorEntity::raptorSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(NorthstarEntityTypes.MERCURY_ROACH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MercuryRoachEntity::roachSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(NorthstarEntityTypes.MERCURY_TORTOISE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MercuryTortoiseEntity::tortoiseSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class CommonEventListener {

        private static void registerItem(BuildCreativeModeTabContentsEvent event, String planet) {
            ItemStack earth = new ItemStack(NorthstarItems.STAR_MAP.get());
            earth.setHoverName(Component.translatable("item.northstar.star_map_" + planet).setStyle(Style.EMPTY.withColor(ChatFormatting.AQUA).withItalic(false)));
            CompoundTag earthTag = earth.getOrCreateTagElement("Planet");
            earthTag.putString("name", planet);
            event.accept(earth);
        }

        private static void registerSpaceSuit(BuildCreativeModeTabContentsEvent event, Item item) {
            ItemStack stack = new ItemStack(item);
            CompoundTag tag = stack.getOrCreateTag();
            tag.putInt("Oxygen", OxygenStuff.maximumOxy);
            ListTag lore = new ListTag();
            lore.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal("Oxygen: " + OxygenStuff.maximumOxy + "mb").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY).withItalic(false))).toString()));
            stack.getOrCreateTagElement("display").put("Lore", lore);
            event.accept(stack);
        }

        @SubscribeEvent
        public static void onRegisterItems(BuildCreativeModeTabContentsEvent event) {
            if (event.getTab() == NorthstarCreativeModeTab.ITEMS.get()) {
                registerItem(event, "earth");
                registerItem(event, "moon");
                registerItem(event, "mars");
                registerItem(event, "mercury");
                registerItem(event, "venus");

                registerSpaceSuit(event, NorthstarItems.IRON_SPACE_SUIT_CHESTPIECE.get());
                registerSpaceSuit(event, NorthstarItems.MARTIAN_STEEL_SPACE_SUIT_CHESTPIECE.get());
            }
        }

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
            MenuScreens.register(NorthstarMenuTypes.TELESCOPE_MENU.get(), TelescopeScreen::new);
            MenuScreens.register(NorthstarMenuTypes.ASTRONOMY_TABLE_MENU.get(), AstronomyTableScreen::new);
            MenuScreens.register(NorthstarMenuTypes.ROCKET_STATION.get(), RocketStationScreen::new);
        }

        @SubscribeEvent
        public static void addEntityRendererLayers(EntityRenderersEvent.AddLayers event) {
            EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();

            SpaceSuitLayerRenderer.registerOnAll(dispatcher);
        }

        @SubscribeEvent
        public static void registerRenderers(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(NorthstarFluids.SULFURIC_ACID.get().getSource(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(NorthstarFluids.SULFURIC_ACID.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(NorthstarFluids.LIQUID_HYDROGEN.get().getSource(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(NorthstarFluids.LIQUID_HYDROGEN.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(NorthstarFluids.LIQUID_OXYGEN.get().getSource(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(NorthstarFluids.LIQUID_OXYGEN.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(NorthstarFluids.METHANE.get().getSource(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(NorthstarFluids.METHANE.get(), RenderType.translucent());
        }

    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

}
