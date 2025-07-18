package com.lightning.northstar.content;

import com.lightning.northstar.block.*;
import com.lightning.northstar.block.tech.astronomy_table.AstronomyTableBlock;
import com.lightning.northstar.block.tech.circuit_engraver.CircuitEngraverBlock;
import com.lightning.northstar.block.tech.cogs.SpaceCogWheelBlock;
import com.lightning.northstar.block.tech.combustion_engine.CombustionEngineBlock;
import com.lightning.northstar.block.tech.computer_rack.TargetingComputerRackBlock;
import com.lightning.northstar.block.tech.electrolysis_machine.ElectrolysisMachineBlock;
import com.lightning.northstar.block.tech.ice_box.IceBoxBlock;
import com.lightning.northstar.block.tech.jet_engine.JetEngineBlock;
import com.lightning.northstar.block.tech.jet_engine.JetEngineItem;
import com.lightning.northstar.block.tech.jet_engine.JetEngineMovementBehaviour;
import com.lightning.northstar.block.tech.oxygen_concentrator.OxygenConcentratorBlock;
import com.lightning.northstar.block.tech.oxygen_detector.OxygenDetectorBlock;
import com.lightning.northstar.block.tech.oxygen_filler.OxygenFillerBlock;
import com.lightning.northstar.block.tech.oxygen_generator.OxygenGeneratorBlock;
import com.lightning.northstar.block.tech.rocket_controls.RocketControlsBlock;
import com.lightning.northstar.block.tech.rocket_controls.RocketControlsInteractionBehaviour;
import com.lightning.northstar.block.tech.rocket_controls.RocketControlsMovementBehaviour;
import com.lightning.northstar.block.tech.rocket_station.RocketStationBlock;
import com.lightning.northstar.block.tech.rocket_station.RocketStationBlockMovingInteraction;
import com.lightning.northstar.block.tech.solar_panel.SolarPanelBlock;
import com.lightning.northstar.block.tech.temperature_regulator.TemperatureRegulatorBlock;
import com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour;
import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.content.processing.basin.BasinMovementBehaviour;
import com.simibubi.create.foundation.data.*;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

import static com.lightning.northstar.Northstar.REGISTRATE;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

public class NorthstarTechBlocks {

    static {
        REGISTRATE.setCreativeTab(NorthstarCreativeModeTab.TECH);
    }

    public static final BlockEntry<SolarPanelBlock> SOLAR_PANEL = REGISTRATE
            .block("solar_panel", SolarPanelBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.CLAY)
                    .noOcclusion())
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> BlockStateGen.directionalBlockIgnoresWaterlogged(c, p, s -> AssetLookup.partialBaseModel(c, p)))
            .addLayer(() -> RenderType::cutoutMipped)
            .onRegister(b -> BlockStressValues.CAPACITIES.register(b, () -> 128.0))
            .onRegister(BlockStressValues.setGeneratorSpeed(SolarPanelBlock.getSpeedRange().getSecond(), true))
            .item()
            .transform(customItemModel())
            .register();


    public static final BlockEntry<LaserLenseBlock> LASER_LENSE = REGISTRATE
            .block("laser_lense", LaserLenseBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion())
            .properties(p -> p.sound(SoundType.COPPER))
            .transform(pickaxeOnly())
            .addLayer(() -> RenderType::cutoutMipped)
            .simpleItem()

            .register();
    public static final BlockEntry<LaserBlock> LASER = REGISTRATE
            .block("laser", LaserBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_RED)
                    .noOcclusion()
                    .noCollission()
                    .lightLevel(state -> 15))
            .simpleItem()
            .register();
    public static final BlockEntry<CircuitEngraverBlock> CIRCUIT_ENGRAVER = REGISTRATE
            .block("circuit_engraver", CircuitEngraverBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .noOcclusion()
                    .isViewBlocking(NorthstarTechBlocks::never))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.horizontalBlockProvider(true))
            .onRegister(b -> BlockStressValues.IMPACTS.register(b, () -> 8))
            .item(AssemblyOperatorBlockItem::new)
            .transform(customItemModel())
            .register();

    public static final BlockEntry<OxygenConcentratorBlock> OXYGEN_CONCENTRATOR = REGISTRATE
            .block("oxygen_concentrator", OxygenConcentratorBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion()
                    .isViewBlocking(NorthstarTechBlocks::never)
                    .strength(6, 6))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.horizontalBlockProvider(true))
            //.transform(CStress.setImpact(16.0))
            .onRegister(b -> BlockStressValues.IMPACTS.register(b, () -> 16))
            .addLayer(() -> RenderType::cutoutMipped)
            .simpleItem()
            .register();

    public static final BlockEntry<OxygenFillerBlock> OXYGEN_FILLER = REGISTRATE
            .block("oxygen_filler", OxygenFillerBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion()
                    .isViewBlocking(NorthstarTechBlocks::never)
                    .strength(6, 6))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.horizontalBlockProvider(true))
            .addLayer(() -> RenderType::cutoutMipped)
            .simpleItem()
            .register();


    public static final BlockEntry<TemperatureRegulatorBlock> TEMPERATURE_REGULATOR = REGISTRATE
            .block("temperature_regulator", TemperatureRegulatorBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion()
                    .isViewBlocking(NorthstarTechBlocks::never)
                    .strength(8, 8))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.horizontalBlockProvider(true))
            .onRegister(b -> BlockStressValues.IMPACTS.register(b, () -> 16))
            .addLayer(() -> RenderType::cutoutMipped)
            .simpleItem()
            .register();

    public static final BlockEntry<OxygenGeneratorBlock> OXYGEN_GENERATOR = REGISTRATE
            .block("oxygen_generator", OxygenGeneratorBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion()
                    .isViewBlocking(NorthstarTechBlocks::never)
                    .strength(8, 8))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.horizontalBlockProvider(true))
            .onRegister(b -> BlockStressValues.IMPACTS.register(b, () -> 16))
            .addLayer(() -> RenderType::cutoutMipped)
            .simpleItem()
            .register();


    public static final BlockEntry<CombustionEngineBlock> COMBUSTION_ENGINE = REGISTRATE
            .block("combustion_engine", CombustionEngineBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion()
                    .isViewBlocking(NorthstarTechBlocks::never)
                    .strength(8, 8))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.horizontalBlockProvider(true))
            .onRegister(b -> BlockStressValues.CAPACITIES.register(b, () -> 256))
            .onRegister(BlockStressValues.setGeneratorSpeed(CombustionEngineBlock.getSpeedRange().getSecond(), true))
            .addLayer(() -> RenderType::cutoutMipped)
            .simpleItem()
            .register();

    public static final BlockEntry<JetEngineBlock> JET_ENGINE = REGISTRATE
            .block("jet_engine", JetEngineBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .noOcclusion()
                    .isViewBlocking(NorthstarTechBlocks::never))
            .transform(pickaxeOnly())
            .addLayer(() -> RenderType::cutoutMipped)
            .onRegister(MovementBehaviour.movementBehaviour(new JetEngineMovementBehaviour()))
            .item(JetEngineItem::new)
            .build()
            .register();

    public static final BlockEntry<AstronomyTableBlock> ASTRONOMY_TABLE = REGISTRATE
            .block("astronomy_table", AstronomyTableBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion()
                    .sound(SoundType.WOOD))
            .transform(pickaxeOnly())
            .simpleItem()
            .register();


    public static final BlockEntry<IceBoxBlock> ICE_BOX = REGISTRATE
            .block("ice_box", IceBoxBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK))
            .transform(pickaxeOnly())
            .addLayer(() -> RenderType::cutoutMipped)
            .onRegister(MovementBehaviour.movementBehaviour(new BasinMovementBehaviour()))
            .item()
            .transform(customItemModel("_", "block"))
            .register();

    public static final BlockEntry<ElectrolysisMachineBlock> ELECTROLYSIS_MACHINE = REGISTRATE
            .block("electrolysis_machine", ElectrolysisMachineBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .isViewBlocking(NorthstarTechBlocks::never)
                    .sound(SoundType.NETHERITE_BLOCK).noOcclusion())
            .transform(pickaxeOnly())
            .addLayer(() -> RenderType::cutoutMipped)
            .item()
            .transform(customItemModel("_", "block"))
            .register();
    public static final BlockEntry<RocketStationBlock> ROCKET_STATION = REGISTRATE
            .block("rocket_station", RocketStationBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK))
            .transform(pickaxeOnly())
            .simpleItem()
            .onRegister(MovingInteractionBehaviour.interactionBehaviour(new RocketStationBlockMovingInteraction()))
            .lang("Rocket Station")
            .register();
    public static final BlockEntry<RocketControlsBlock> ROCKET_CONTROLS = REGISTRATE
            .block("rocket_controls", RocketControlsBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion()
                    .sound(SoundType.NETHERITE_BLOCK))
            .transform(pickaxeOnly())
            .onRegister(MovementBehaviour.movementBehaviour(new RocketControlsMovementBehaviour()))
            .onRegister(MovingInteractionBehaviour.interactionBehaviour(new RocketControlsInteractionBehaviour()))
            .simpleItem()
            .lang("Rocket Controls")
            .register();
    public static final BlockEntry<TargetingComputerRackBlock> COMPUTER_RACK = REGISTRATE
            .block("computer_rack", TargetingComputerRackBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion()
                    .sound(SoundType.NETHERITE_BLOCK))
            .transform(pickaxeOnly())
            .simpleItem()
            .lang("Computer Rack")
            .register();
    public static final BlockEntry<OxygenDetectorBlock> OXYGEN_DETECTOR = REGISTRATE
            .block("oxygen_detector", OxygenDetectorBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion()
                    .sound(SoundType.NETHERITE_BLOCK))
            .transform(pickaxeOnly())
            .simpleItem()
            .lang("Oxygen Detector")
            .register();

    public static final BlockEntry<SpaceDoorBlock> IRON_SPACE_DOOR = REGISTRATE
            .block("iron_space_door", p -> new SpaceDoorBlock(p, BlockSetType.IRON, false))
            .transform(BuilderTransformers.slidingDoor("train"))
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_CYAN)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .noOcclusion())
            .register();

    static {
        REGISTRATE.setCreativeTab(NorthstarCreativeModeTab.BLOCKS);
    }

    public static final BlockEntry<ExtinguishedTorchBlock> EXTINGUISHED_TORCH = REGISTRATE
            .block("extinguished_torch", ExtinguishedTorchBlock::new)
            .properties(p -> p.sound(SoundType.WOOD)
                    .noCollission()
                    .instabreak())
            .addLayer(() -> RenderType::cutoutMipped)
            .register();

    public static final BlockEntry<ExtinguishedTorchWallBlock> EXTINGUISHED_TORCH_WALL = REGISTRATE
            .block("extinguished_torch_wall", ExtinguishedTorchWallBlock::new)
            .properties(p -> p.sound(SoundType.WOOD)
                    .noCollission()
                    .instabreak())
            .addLayer(() -> RenderType::cutoutMipped)
            .register();

    public static final BlockEntry<ExtinguishedLanternBlock> EXTINGUISHED_LANTERN = REGISTRATE
            .block("extinguished_lantern", ExtinguishedLanternBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.sound(SoundType.LANTERN))
            .addLayer(() -> RenderType::cutoutMipped)
            .simpleItem()
            .register();

    public static final BlockEntry<GlowstoneTorchBlock> GLOWSTONE_TORCH = REGISTRATE
            .block("glowstone_torch", GlowstoneTorchBlock::new)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .lightLevel(value -> 15)
                    .sound(SoundType.METAL)
                    .noCollission()
                    .instabreak())
            .addLayer(() -> RenderType::cutoutMipped)
            .register();

    public static final BlockEntry<GlowstoneTorchWallBlock> GLOWSTONE_TORCH_WALL = REGISTRATE
            .block("glowstone_torch_wall", GlowstoneTorchWallBlock::new)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .lightLevel(value -> 15)
                    .sound(SoundType.METAL)
                    .noCollission()
                    .instabreak())
            .addLayer(() -> RenderType::cutoutMipped)
            .register();

    public static final BlockEntry<LanternBlock> GLOWSTONE_LANTERN = REGISTRATE
            .block("glowstone_lantern", LanternBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .lightLevel(value -> 15)
                    .sound(SoundType.LANTERN))
            .addLayer(() -> RenderType::cutoutMipped)
            .simpleItem()
            .register();

    public static final BlockEntry<CrystalBlock> AMETHYST_CRYSTAL = REGISTRATE
            .block("amethyst_crystal", CrystalBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_PURPLE)
                    .lightLevel(value -> 5)
                    .sound(SoundType.AMETHYST_CLUSTER))
            .addLayer(() -> RenderType::cutoutMipped)
            .simpleItem()
            .register();

    public static final BlockEntry<CrystalBlock> LUNAR_SAPPHIRE_CRYSTAL = REGISTRATE
            .block("lunar_sapphire_crystal", CrystalBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .lightLevel(value -> 7)
                    .sound(SoundType.AMETHYST_CLUSTER))
            .addLayer(() -> RenderType::cutoutMipped)
            .simpleItem()
            .register();

    public static final BlockEntry<SpaceCogWheelBlock> IRON_COGWHEEL = REGISTRATE
            .block("iron_cogwheel", SpaceCogWheelBlock::small)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.METAL))
            .onRegister(b -> BlockStressValues.IMPACTS.register(b, () -> 0))
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item(CogwheelBlockItem::new)
            .build()
            .register();

    public static final BlockEntry<SpaceCogWheelBlock> IRON_LARGE_COGWHEEL = REGISTRATE
            .block("iron_large_cogwheel", SpaceCogWheelBlock::large)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.METAL))
            .onRegister(b -> BlockStressValues.IMPACTS.register(b, () -> 0))
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item(CogwheelBlockItem::new)
            .build()
            .register();

    public static void register() {
    }

    private static boolean never(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

}
