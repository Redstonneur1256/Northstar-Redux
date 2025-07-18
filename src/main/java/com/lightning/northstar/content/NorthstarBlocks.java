package com.lightning.northstar.content;

import com.lightning.northstar.Northstar;
import com.lightning.northstar.block.*;
import com.lightning.northstar.block.crops.*;
import com.lightning.northstar.block.tech.telescope.TelescopeBlock;
import com.lightning.northstar.data.NorthstarConfiguredFeatures;
import com.lightning.northstar.world.features.grower.ArgyreSaplingTreeGrower;
import com.lightning.northstar.world.features.grower.CoilerTreeGrower;
import com.lightning.northstar.world.features.grower.WilterTreeGrower;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.ToIntFunction;

import static com.lightning.northstar.Northstar.REGISTRATE;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static net.minecraft.world.level.block.Blocks.OAK_PLANKS;
import static net.minecraft.world.level.block.Blocks.STONE;

public class NorthstarBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Northstar.MOD_ID);

    static {
        REGISTRATE.setCreativeTab(NorthstarCreativeModeTab.BLOCKS);
    }

    // martian steel stuff yay :]
    public static final BlockEntry<Block> MARTIAN_STEEL_BLOCK = REGISTRATE.block("martian_steel_block", Block::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(30f, 15f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<Block> MARTIAN_STEEL_SHEETMETAL = REGISTRATE.block("martian_steel_sheetmetal", Block::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(5f, 15f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<SlabBlock> MARTIAN_STEEL_SHEETMETAL_SLAB = REGISTRATE.block("martian_steel_sheetmetal_slab", SlabBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(5f, 15f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<VerticalSlabBlock> MARTIAN_STEEL_SHEETMETAL_VERTICAL_SLAB = REGISTRATE.block("martian_steel_sheetmetal_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(5f, 15f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<Block> MARTIAN_STEEL_PLATING = REGISTRATE.block("martian_steel_plating", Block::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(5f, 15f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<Block> MARTIAN_STEEL_LARGE_PLATING = REGISTRATE.block("martian_steel_large_plating", Block::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(5f, 15f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<SlabBlock> MARTIAN_STEEL_PLATING_SLAB = REGISTRATE.block("martian_steel_plating_slab", SlabBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(5f, 15f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<VerticalSlabBlock> MARTIAN_STEEL_PLATING_VERTICAL_SLAB = REGISTRATE.block("martian_steel_plating_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(5f, 15f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<StairBlock> MARTIAN_STEEL_PLATING_STAIRS = REGISTRATE.block("martian_steel_plating_stairs", p -> new StairBlock(() -> MARTIAN_STEEL_PLATING.get()
                    .defaultBlockState(), p))
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(5f, 15f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<RotatedPillarBlock> MARTIAN_STEEL_PILLAR = REGISTRATE.block("martian_steel_pillar", RotatedPillarBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(5f, 15f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<GrateBlock> MARTIAN_STEEL_GRATE = REGISTRATE.block("martian_steel_grate", GrateBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(4f, 12f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .isSuffocating(NorthstarBlocks::never)
                    .isViewBlocking(NorthstarBlocks::never))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> MARTIAN_STEEL_LAMP = REGISTRATE.block("martian_steel_lamp", Block::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .lightLevel(state -> 15)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(4f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<Block> MARTIAN_STEEL_BLUE_LAMP = REGISTRATE.block("martian_steel_blue_lamp", Block::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .lightLevel(state -> 15)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(4f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    // iron stuff
    public static final BlockEntry<Block> IRON_SHEETMETAL = REGISTRATE.block("iron_sheetmetal", Block::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(4f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<SlabBlock> IRON_SHEETMETAL_SLAB = REGISTRATE.block("iron_sheetmetal_slab", SlabBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(4f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<VerticalSlabBlock> IRON_SHEETMETAL_VERTICAL_SLAB = REGISTRATE.block("iron_sheetmetal_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(4f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<Block> IRON_PLATING = REGISTRATE.block("iron_plating", Block::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(4f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<SlabBlock> IRON_PLATING_SLAB = REGISTRATE.block("iron_plating_slab", SlabBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(4f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<VerticalSlabBlock> IRON_PLATING_VERTICAL_SLAB = REGISTRATE.block("iron_plating_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(4f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<StairBlock> IRON_PLATING_STAIRS = REGISTRATE.block("iron_plating_stairs", p -> new StairBlock(() -> IRON_PLATING.get()
                    .defaultBlockState(), p))
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(4f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<RotatedPillarBlock> IRON_PILLAR = REGISTRATE.block("iron_pillar", RotatedPillarBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(4f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<GrateBlock> IRON_GRATE = REGISTRATE.block("iron_grate", GrateBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(4f, 8f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .isSuffocating(NorthstarBlocks::never)
                    .isViewBlocking(NorthstarBlocks::never))
            .simpleItem()
            .register();


    public static final BlockEntry<GrateBlock> VENT_BLOCK = REGISTRATE.block("vent_block", GrateBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(4f, 8f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .isSuffocating(NorthstarBlocks::never)
                    .isViewBlocking(NorthstarBlocks::never))
            .simpleItem()
            .register();


    //tungsten stuff
    public static final BlockEntry<Block> TUNGSTEN_BLOCK = REGISTRATE.block("tungsten_block", Block::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(30f, 16f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<Block> TUNGSTEN_SHEETMETAL = REGISTRATE.block("tungsten_sheetmetal", Block::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(6f, 16f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<SlabBlock> TUNGSTEN_SHEETMETAL_SLAB = REGISTRATE.block("tungsten_sheetmetal_slab", SlabBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(6f, 16f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<VerticalSlabBlock> TUNGSTEN_SHEETMETAL_VERTICAL_SLAB = REGISTRATE.block("tungsten_sheetmetal_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(6f, 16f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<Block> TUNGSTEN_PLATING = REGISTRATE.block("tungsten_plating", Block::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(6f, 16f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<SlabBlock> TUNGSTEN_PLATING_SLAB = REGISTRATE.block("tungsten_plating_slab", SlabBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(6f, 16f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<VerticalSlabBlock> TUNGSTEN_PLATING_VERTICAL_SLAB = REGISTRATE.block("tungsten_plating_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(6f, 16f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<StairBlock> TUNGSTEN_PLATING_STAIRS = REGISTRATE.block("tungsten_plating_stairs", p -> new StairBlock(TUNGSTEN_PLATING.get()
                    .defaultBlockState(), p))
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(6f, 16f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RotatedPillarBlock> TUNGSTEN_PILLAR = REGISTRATE.block("tungsten_pillar", RotatedPillarBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(6f, 16f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    public static final BlockEntry<GrateBlock> TUNGSTEN_GRATE = REGISTRATE.block("tungsten_grate", GrateBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .strength(5f, 16f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .isSuffocating(NorthstarBlocks::never)
                    .isViewBlocking(NorthstarBlocks::never))
            .simpleItem()
            .register();


    public static final BlockEntry<Block> GLOWSTONE_LAMP = REGISTRATE.block("glowstone_lamp", Block::new) /*.initialProperties(SharedProperties::decoration)*/.properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS)
                    .strength(2f, 5f))
            .simpleItem()
            .register();


    public static final BlockEntry<GravelBlock> MARS_SAND = REGISTRATE.block("mars_sand", GravelBlock::new) /*.initialProperties(SharedProperties::sand)*/.properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.GRAVEL)
                    .strength(0.5f, 1.6f))
            .simpleItem()
            .register();


    public static final BlockEntry<GravelBlock> MARS_GRAVEL = REGISTRATE.block("mars_gravel", GravelBlock::new) /*.initialProperties(SharedProperties::SAND)*/.properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.GRAVEL)
                    .strength(0.65f, 2.0f))
            .simpleItem()
            .register();


    public static final BlockEntry<MarsSoilBlock> MARS_SOIL = REGISTRATE.block("mars_soil", MarsSoilBlock::new) /*.initialProperties(SharedProperties::DIRT)*/.properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.GRAVEL)
                    .strength(0.5f, 8f))
            .simpleItem()
            .register();


    public static final BlockEntry<MartianGrassBlock> MARTIAN_GRASS = REGISTRATE.block("martian_grass", MartianGrassBlock::new) /*.initialProperties(SharedProperties::DIRT)*/.properties(p -> p.mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.GRASS)
                    .strength(0.65f, 8f)
                    .randomTicks())
            .simpleItem()
            .register();


    public static final BlockEntry<MartianTallGrassBlock> MARTIAN_TALL_GRASS = REGISTRATE.block("martian_tall_grass", MartianTallGrassBlock::new) /*.initialProperties(SharedProperties::REPLACEABLE_PLANT)*/.properties(p -> p.mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.GRASS)
                    .noCollission()
                    .instabreak()
                    .randomTicks()
                    .offsetType(BlockBehaviour.OffsetType.XYZ))
            .simpleItem()
            .register();


    public static final BlockEntry<MarsFarmlandBlock> MARS_FARMLAND = REGISTRATE.block("mars_farmland", MarsFarmlandBlock::new) /*.initialProperties(SharedProperties::DIRT)*/.properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .randomTicks()
                    .sound(SoundType.GRAVEL)
                    .strength(0.5f, 8f))
            .simpleItem()
            .register();


    public static final BlockEntry<MarsWormNestBlock> MARS_WORM_NEST = REGISTRATE.block("mars_worm_nest", MarsWormNestBlock::new) /*.initialProperties(SharedProperties::LEAVES)*/.properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .randomTicks()
                    .sound(SoundType.GRASS)
                    .strength(0.2f, 0.2f))
            .simpleItem()
            .register();


    public static final BlockEntry<MarsRootBlock> MARS_ROOTS = REGISTRATE.block("mars_roots", MarsRootBlock::new) /*.initialProperties(SharedProperties::LEAVES)*/.properties(p -> p.mapColor(MapColor.TERRACOTTA_GRAY)
                    .sound(SoundType.VINE)
                    .noOcclusion()
                    .noCollission()
                    .strength(0.2f)
                    .randomTicks()
                    .isSuffocating(NorthstarBlocks::never)
                    .isViewBlocking(NorthstarBlocks::never))
            .simpleItem()
            .register();


    public static final BlockEntry<MarsRootBlock> GLOWING_MARS_ROOTS = REGISTRATE.block("glowing_mars_roots", MarsRootBlock::new) /*.initialProperties(SharedProperties::LEAVES)*/.properties(p -> p.mapColor(MapColor.TERRACOTTA_GRAY)
                    .lightLevel(state -> {
                        return 10;
                    }).

                    sound(SoundType.VINE).

                    noOcclusion().

                    noCollission().

                    strength(0.2f).

                    randomTicks().

                    isSuffocating(NorthstarBlocks::never).

                    isViewBlocking(NorthstarBlocks::never)).

            register();


    public static final BlockEntry<RotatedPillarBlock> STRIPPED_COILER_LOG = REGISTRATE.block("stripped_coiler_log", RotatedPillarBlock::new) /*.initialProperties(SharedProperties::WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .sound(SoundType.WOOD)
                    .strength(2f))
            .simpleItem()
            .register();


    public static final BlockEntry<LogBlock> COILER_LOG = REGISTRATE.block("coiler_log", p -> new LogBlock(p, STRIPPED_COILER_LOG.get())) /*.initialProperties(SharedProperties::WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .sound(SoundType.WOOD)
                    .strength(2f))
            .simpleItem()
            .register();


    public static final BlockEntry<Block> COILER_PLANKS = REGISTRATE.block("coiler_planks", Block::new) /*.initialProperties(SharedProperties::WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .sound(SoundType.WOOD)
                    .strength(2f, 3f))
            .simpleItem()
            .register();


    public static final BlockEntry<SlabBlock> COILER_SLAB = REGISTRATE.block("coiler_slab", SlabBlock::new) /*.initialProperties(SharedProperties::WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .sound(SoundType.WOOD)
                    .strength(2f, 3f))
            .simpleItem()
            .register();


    public static final BlockEntry<StairBlock> COILER_STAIRS = REGISTRATE.block("coiler_stairs", p -> new StairBlock(() -> OAK_PLANKS.defaultBlockState(), p)) /*/BlockBehaviour.Properties.of(Material.WOOD*/.properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .sound(SoundType.WOOD)
                    .strength(2f, 3f))
            .simpleItem()
            .register();


    public static final BlockEntry<LeavesBlock> COILER_LEAVES = REGISTRATE.block("coiler_leaves", LeavesBlock::new) /*.initialProperties(SharedProperties::LEAVES)*/.properties(p -> p.mapColor(MapColor.COLOR_MAGENTA)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .strength(0.5f)
                    .isSuffocating(NorthstarBlocks::never)
                    .isViewBlocking(NorthstarBlocks::never))
            .simpleItem()
            .register();


    public static final BlockEntry<VineBlock> COILER_VINES = REGISTRATE.block("coiler_vines", VineBlock::new) /*.initialProperties(SharedProperties::LEAVES)*/.properties(p -> p.mapColor(MapColor.COLOR_MAGENTA)
                    .sound(SoundType.VINE)
                    .noOcclusion()
                    .noCollission()
                    .randomTicks()
                    .strength(0.2f)
                    .isSuffocating(NorthstarBlocks::never)
                    .isViewBlocking(NorthstarBlocks::never))
            .simpleItem()
            .register();


    public static final BlockEntry<SaplingBlock> COILER_SAPLING = REGISTRATE.block("coiler_sapling", p -> new SaplingBlock(new CoilerTreeGrower(), p)) /*BlockBehaviour.Properties.of(Material.PLANT).*/.properties(p -> p.sound(SoundType.GRASS)
                    .randomTicks()
                    .instabreak()
                    .noCollission())
            .simpleItem()
            .register();


    public static final BlockEntry<MartianStrawberryBushBlock> MARTIAN_STRAWBERRY_BUSH = REGISTRATE.block("martian_strawberry_bush", MartianStrawberryBushBlock::new) /*.initialProperties(SharedProperties::PLANT)*/.properties(p -> p.sound(SoundType.GRASS)
                    .randomTicks()
                    .instabreak()
                    .noCollission())
            .simpleItem()
            .register();


    public static final BlockEntry<RotatedPillarBlock> STRIPPED_WILTER_LOG = REGISTRATE.block("stripped_wilter_log", RotatedPillarBlock::new) /*.initialProperties(SharedProperties::WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.WOOD)
                    .strength(2f))
            .simpleItem()
            .register();


    public static final BlockEntry<LogBlock> WILTER_LOG = REGISTRATE.block("wilter_log", p -> new LogBlock(p, STRIPPED_WILTER_LOG.get())) /*.initialProperties(SharedProperties::WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.WOOD)
                    .strength(2f))
            .simpleItem()
            .register();


    public static final BlockEntry<Block> WILTER_PLANKS = REGISTRATE.block("wilter_planks", Block::new) /*.initialProperties(SharedProperties::WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.WOOD)
                    .strength(2f))
            .simpleItem()
            .register();


    public static final BlockEntry<SlabBlock> WILTER_SLAB = REGISTRATE.block("wilter_slab", SlabBlock::new) /*.initialProperties(SharedProperties::WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.WOOD)
                    .strength(2f, 3f))
            .simpleItem()
            .register();


    public static final BlockEntry<StairBlock> WILTER_STAIRS = REGISTRATE.block("wilter_stairs", p -> new StairBlock(() -> OAK_PLANKS.defaultBlockState(), p)) /*.initialProperties(WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.WOOD)
                    .strength(2f, 3f))
            .simpleItem()
            .register();


    public static final BlockEntry<RotatedPillarBlock> STRIPPED_ARGYRE_LOG = REGISTRATE.block("stripped_argyre_log", RotatedPillarBlock::new) /*.initialProperties(SharedProperties::WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_RED)
                    .sound(SoundType.WOOD)
                    .strength(2f))
            .simpleItem()
            .register();


    public static final BlockEntry<LogBlock> ARGYRE_LOG = REGISTRATE.block("argyre_log", p -> new LogBlock(p, STRIPPED_ARGYRE_LOG.get())) /*.initialProperties(SharedProperties::WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_RED)
                    .sound(SoundType.WOOD)
                    .strength(2f))
            .simpleItem()
            .register();


    public static final BlockEntry<LeavesBlock> ARGYRE_LEAVES = REGISTRATE.block("argyre_leaves", LeavesBlock::new) /*.initialProperties(SharedProperties::LEAVES)*/.properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .strength(0.5f)
                    .isSuffocating(NorthstarBlocks::never)
                    .isViewBlocking(NorthstarBlocks::never))
            .simpleItem()
            .register();


    public static final BlockEntry<Block> ARGYRE_PLANKS = REGISTRATE.block("argyre_planks", Block::new) /*.initialProperties(SharedProperties::WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_RED)
                    .sound(SoundType.WOOD)
                    .strength(2f, 3f))
            .simpleItem()
            .register();


    public static final BlockEntry<SlabBlock> ARGYRE_SLAB = REGISTRATE.block("argyre_slab", SlabBlock::new) /*.initialProperties(SharedProperties::WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_RED)
                    .sound(SoundType.WOOD)
                    .strength(2f, 3f))
            .simpleItem()
            .register();


    public static final BlockEntry<StairBlock> ARGYRE_STAIRS = REGISTRATE.block("argyre_stairs", p -> new StairBlock(() -> OAK_PLANKS.defaultBlockState(), p)) /*.initialProperties(WOOD)*/.properties(p -> p.mapColor(MapColor.COLOR_RED)
                    .sound(SoundType.WOOD)
                    .strength(2f, 3f))
            .simpleItem()
            .register();


    public static final BlockEntry<SaplingBlock> ARGYRE_SAPLING = REGISTRATE.block("argyre_sapling", p -> new SaplingBlock(new ArgyreSaplingTreeGrower(), p)) /*BlockBehaviour.Properties.of(Material.PLANT).*/.properties(p -> p.sound(SoundType.GRASS)
                    .randomTicks()
                    .instabreak()
                    .noCollission())
            .simpleItem()
            .register();


    public static final BlockEntry<LeavesBlock> WILTER_LEAVES = REGISTRATE.block("wilter_leaves", LeavesBlock::new) /*.initialProperties(SharedProperties::LEAVES)*/.properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .strength(0.5f)
                    .isSuffocating(NorthstarBlocks::never)
                    .isViewBlocking(NorthstarBlocks::never))
            .simpleItem()
            .register();


    public static final BlockEntry<SaplingBlock> WILTER_SAPLING = REGISTRATE.block("wilter_sapling", p -> new SaplingBlock(new WilterTreeGrower(), p)) /*BlockBehaviour.Properties.of(Material.PLANT).*/.properties(p -> p.sound(SoundType.GRASS)
                    .randomTicks()
                    .instabreak()
                    .noCollission())
            .simpleItem()
            .register();


    public static final BlockEntry<MarsTulipBlock> MARS_TULIP = REGISTRATE.block("mars_tulip", MarsTulipBlock::new) /*.initialProperties(SharedProperties::PLANT)*/.properties(p -> p.sound(SoundType.GRASS)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .randomTicks()
                    .instabreak()
                    .noCollission()
                    .offsetType(BlockBehaviour.OffsetType.XZ))
            .simpleItem()
            .register();

    public static final BlockEntry<MarsPalmBlock> MARS_PALM = REGISTRATE.block("mars_palm", MarsPalmBlock::new) /*.initialProperties(SharedProperties::PLANT)*/.properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.GRASS)
                    .randomTicks()
                    .instabreak()
                    .noCollission()
                    .offsetType(BlockBehaviour.OffsetType.XZ))
            .simpleItem()
            .register();

    public static final BlockEntry<MarsSproutBlock> MARS_SPROUT = REGISTRATE.block("mars_sprout", MarsSproutBlock::new) /*.initialProperties(SharedProperties::PLANT)*/.properties(p -> p.sound(SoundType.GRASS)
                    .mapColor(MapColor.COLOR_PINK)
                    .randomTicks()
                    .instabreak()
                    .noCollission()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .lightLevel((l) -> 7))
            .simpleItem()
            .register();

    public static final BlockEntry<MartianTallFlowerBlock> MARS_SPROUT_BIG = REGISTRATE.block("mars_sprout_big", MartianTallFlowerBlock::new) /*.initialProperties(SharedProperties::PLANT)*/.properties(p -> p.sound(SoundType.GRASS)
                    .randomTicks()
                    .instabreak()
                    .noCollission()
                    .lightLevel((l) -> {
                        return 14;
                    })).

            register();

    public static final BlockEntry<PointedCrimsiteBlock> POINTED_CRIMSITE = REGISTRATE.block("pointed_crimsite", PointedCrimsiteBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_RED)
                    .sound(SoundType.DEEPSLATE)
                    .strength(3.5f, 12f)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .dynamicShape()
                    .offsetType(BlockBehaviour.OffsetType.XZ))
            .simpleItem()
            .register();


    //mars stone stuff
    public static final BlockEntry<Block> MARS_STONE = REGISTRATE.block("mars_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(3.5f, 8f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MARS_DEEP_STONE = REGISTRATE.block("mars_deep_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(4.5f, 8f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    //mars deco blocks
    public static final BlockEntry<Block> MARS_STONE_BRICKS = REGISTRATE.block("mars_stone_bricks", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<SlabBlock> MARS_STONE_BRICK_SLAB = REGISTRATE.block("mars_stone_brick_slab", SlabBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<VerticalSlabBlock> MARS_STONE_BRICK_SLAB_VERTICAL = REGISTRATE.block("mars_stone_brick_slab_vertical", VerticalSlabBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<StairBlock> MARS_STONE_BRICK_STAIRS = REGISTRATE.block("mars_stone_brick_stairs", p -> new StairBlock(() -> STONE.defaultBlockState(), p)) /*.initalProperties(STONE)*/.properties(p -> p.sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<WallBlock> MARS_STONE_BRICK_WALL = REGISTRATE.block("mars_stone_brick_wall", WallBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RotatedPillarBlock> MARS_STONE_PILLAR = REGISTRATE.block("mars_stone_pillar", RotatedPillarBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> CHISELED_MARS_STONE = REGISTRATE.block("chiseled_mars_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> POLISHED_MARS_STONE = REGISTRATE.block("polished_mars_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MARS_STONE_LAMP = REGISTRATE.block("mars_stone_lamp", Block::new) /*.initialProperties(SharedProperties::DECORATION)*/.properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .lightLevel(state -> {
                        return 15;
                    }).

                    sound(SoundType.GLASS).

                    strength(3f, 6.5f).

                    requiresCorrectToolForDrops()).

            register();

    //mars ores
    public static final BlockEntry<Block> MARS_IRON_ORE = REGISTRATE.block("mars_iron_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MARS_COPPER_ORE = REGISTRATE.block("mars_copper_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MARS_GOLD_ORE = REGISTRATE.block("mars_gold_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MARS_DIAMOND_ORE = REGISTRATE.block("mars_diamond_ore", p -> new DropExperienceBlock(p, UniformInt.of(3, 7)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RedStoneOreBlock> MARS_REDSTONE_ORE = REGISTRATE.block("mars_redstone_ore", RedStoneOreBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .lightLevel(litBlockEmission(9)))
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MARS_QUARTZ_ORE = REGISTRATE.block("mars_quartz_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    //mars deep ores
    public static final BlockEntry<Block> MARS_DEEP_IRON_ORE = REGISTRATE.block("mars_deep_iron_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MARS_DEEP_COPPER_ORE = REGISTRATE.block("mars_deep_copper_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MARS_DEEP_GOLD_ORE = REGISTRATE.block("mars_deep_gold_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MARS_DEEP_DIAMOND_ORE = REGISTRATE.block("mars_deep_diamond_ore", p -> new DropExperienceBlock(p, UniformInt.of(3, 7)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(7f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RedStoneOreBlock> MARS_DEEP_REDSTONE_ORE = REGISTRATE.block("mars_deep_redstone_ore", RedStoneOreBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .lightLevel(litBlockEmission(9)))
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MARS_DEEP_QUARTZ_ORE = REGISTRATE.block("mars_deep_quartz_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> VOLCANIC_ASH = REGISTRATE.block("volcanic_ash", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.TUFF)
                    .strength(0.4f, 2f))
            .simpleItem()
            .register();


    public static final BlockEntry<Block> VOLCANIC_ROCK = REGISTRATE.block("volcanic_rock", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_BROWN)
                    .sound(SoundType.TUFF)
                    .strength(2f, 4f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    //moon stone stuff
    public static final BlockEntry<GravelBlock> MOON_SAND = REGISTRATE.block("moon_sand", GravelBlock::new) /*.initialProperties(SharedProperties::SAND)*/.properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.SAND)
                    .strength(0.5f, 8.0f))
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MOON_STONE = REGISTRATE.block("moon_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(3.5f, 8f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MOON_DEEP_STONE = REGISTRATE.block("moon_deep_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(4.5f, 8f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<FrostBlock> FROST = REGISTRATE.block("frost", FrostBlock::new) /*.initialProperties(SharedProperties::ICE)*/.properties(p -> p.mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .sound(SoundType.CALCITE)
                    .friction(0.989F)
                    .noOcclusion()
                    .noCollission()
                    .strength(0.2f)
                    .isSuffocating(NorthstarBlocks::never)
                    .isViewBlocking(NorthstarBlocks::never))
            .simpleItem()
            .register();


    //moon deco stuff ayy
    public static final BlockEntry<Block> MOON_STONE_BRICKS = REGISTRATE.block("moon_stone_bricks", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<SlabBlock> MOON_STONE_BRICK_SLAB = REGISTRATE.block("moon_stone_brick_slab", SlabBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<VerticalSlabBlock> MOON_STONE_BRICK_SLAB_VERTICAL = REGISTRATE.block("moon_stone_brick_slab_vertical", VerticalSlabBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<StairBlock> MOON_STONE_BRICK_STAIRS = REGISTRATE.block("moon_stone_brick_stairs", p -> new StairBlock(() -> STONE.defaultBlockState(), p)) /*.initialProperties(STONE)*/.properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<WallBlock> MOON_STONE_BRICK_WALL = REGISTRATE.block("moon_stone_brick_wall", WallBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RotatedPillarBlock> MOON_STONE_PILLAR = REGISTRATE.block("moon_stone_pillar", RotatedPillarBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MOON_STONE_LAMP = REGISTRATE.block("moon_stone_lamp", Block::new) /*.initialProperties(SharedProperties::DECORATION)*/.properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .lightLevel(state -> {
                        return 15;
                    }).

                    sound(SoundType.GLASS).

                    strength(3f, 6.5f).

                    requiresCorrectToolForDrops()).

            register();

    public static final BlockEntry<Block> CHISELED_MOON_STONE = REGISTRATE.block("chiseled_moon_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> POLISHED_MOON_STONE = REGISTRATE.block("polished_moon_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    //moon ores
    public static final BlockEntry<Block> MOON_IRON_ORE = REGISTRATE.block("moon_iron_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MOON_COPPER_ORE = REGISTRATE.block("moon_copper_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MOON_GOLD_ORE = REGISTRATE.block("moon_gold_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MOON_DIAMOND_ORE = REGISTRATE.block("moon_diamond_ore", p -> new DropExperienceBlock(p, UniformInt.of(3, 7)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RedStoneOreBlock> MOON_REDSTONE_ORE = REGISTRATE.block("moon_redstone_ore", RedStoneOreBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .lightLevel(litBlockEmission(9)))
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MOON_LAPIS_ORE = REGISTRATE.block("moon_lapis_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MOON_ZINC_ORE = REGISTRATE.block("moon_zinc_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MOON_GLOWSTONE_ORE = REGISTRATE.block("moon_glowstone_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> {
                        return 15;
                    })).

            register();

    //moon deep ores
    public static final BlockEntry<Block> MOON_DEEP_IRON_ORE = REGISTRATE.block("moon_deep_iron_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MOON_DEEP_COPPER_ORE = REGISTRATE.block("moon_deep_copper_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MOON_DEEP_GOLD_ORE = REGISTRATE.block("moon_deep_gold_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MOON_DEEP_DIAMOND_ORE = REGISTRATE.block("moon_deep_diamond_ore", p -> new DropExperienceBlock(p, UniformInt.of(3, 7)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(7f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RedStoneOreBlock> MOON_DEEP_REDSTONE_ORE = REGISTRATE.block("moon_deep_redstone_ore", RedStoneOreBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .lightLevel(litBlockEmission(9)))
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MOON_DEEP_LAPIS_ORE = REGISTRATE.block("moon_deep_lapis_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MOON_DEEP_ZINC_ORE = REGISTRATE.block("moon_deep_zinc_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MOON_DEEP_GLOWSTONE_ORE = REGISTRATE.block("moon_deep_glowstone_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> {
                        return 15;
                    })).

            register();

    public static final BlockEntry<Block> LUNAR_SAPPHIRE_BLOCK = REGISTRATE.block("lunar_sapphire_block", Block::new) /*.initialProperties(SharedProperties::AMETHYST)*/.properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.AMETHYST)
                    .strength(2f, 5f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<ClusterBlock> LUNAR_SAPPHIRE_CLUSTER = REGISTRATE.block("lunar_sapphire_cluster", p -> new ClusterBlock(7, 3, p)) /*.initialProperties(AMETHYST)*/.properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.AMETHYST)
                    .strength(2f, 5f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<ClusterBlock> SMALL_LUNAR_SAPPHIRE_BUD = REGISTRATE.block("small_lunar_sapphire_bud", p -> new ClusterBlock(3, 4, p)) /*.initialProperties(AMETHYST)*/.properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.AMETHYST)
                    .strength(1.5f, 5f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<ClusterBlock> MEDIUM_LUNAR_SAPPHIRE_BUD = REGISTRATE.block("medium_lunar_sapphire_bud", p -> new ClusterBlock(4, 3, p)) /*.initialProperties(AMETHYST)*/.properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.AMETHYST)
                    .strength(1.5f, 5f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<ClusterBlock> LARGE_LUNAR_SAPPHIRE_BUD = REGISTRATE.block("large_lunar_sapphire_bud", p -> new ClusterBlock(5, 3, p))
            .properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.AMETHYST)
                    .strength(1.5f, 5f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<BuddingCrystalBlock> BUDDING_LUNAR_SAPPHIRE_BLOCK = REGISTRATE.block("budding_lunar_sapphire_block", p -> new BuddingCrystalBlock(p, SMALL_LUNAR_SAPPHIRE_BUD.get(), MEDIUM_LUNAR_SAPPHIRE_BUD.get(), LARGE_LUNAR_SAPPHIRE_BUD.get(), LUNAR_SAPPHIRE_CLUSTER.get())) /*.initialProperties(SharedProperties::AMETHYST)*/.properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.AMETHYST)
                    .strength(2f, 5f)
                    .randomTicks()
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    //venus stone stuff
    public static final BlockEntry<Block> VENUS_STONE = REGISTRATE.block("venus_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.DEEPSLATE)
                    .strength(3.5f, 8f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> VENUS_DEEP_STONE = REGISTRATE.block("venus_deep_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.DEEPSLATE)
                    .strength(4.5f, 8f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<GravelBlock> VENUS_GRAVEL = REGISTRATE.block("venus_gravel", GravelBlock::new) /*.initialProperties(SharedProperties::SAND)*/.properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.GRAVEL)
                    .strength(1.1f, 2f))
            .simpleItem()
            .register();


    public static final BlockEntry<VenusExhaustBlock> VENUS_PLUME = REGISTRATE.block("venus_plume", VenusExhaustBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.DEEPSLATE)
                    .strength(3.5f, 8f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<VenusMushroomBlock> SPIKE_FUNGUS = REGISTRATE
            .block("spike_fungus", p -> new VenusMushroomBlock(p,
                    () -> configuredFeatures().getHolderOrThrow(NorthstarConfiguredFeatures.SPIKE_FUNGUS),
                    () -> null))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.FUNGUS)
                    .strength(0f, 0.5f)
                    .noCollission()
                    .instabreak())
            .simpleItem()
            .register();

    public static final BlockEntry<VenusMushroomBlock> BLOOM_FUNGUS = REGISTRATE
            .block("bloom_fungus", p -> new VenusMushroomBlock(p,
                    () -> configuredFeatures().getHolderOrThrow(NorthstarConfiguredFeatures.BLOOM_FUNGUS),
                    () -> configuredFeatures().getHolderOrThrow(NorthstarConfiguredFeatures.ROOF_BLOOM_FUNGUS)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.FUNGUS)
                    .strength(0f, 0.5f)
                    .noCollission()
                    .instabreak()
                    .lightLevel(state -> 7))
            .simpleItem()
            .register();


    public static final BlockEntry<VenusMushroomBlock> PLATE_FUNGUS = REGISTRATE
            .block("plate_fungus", p -> new VenusMushroomBlock(p,
                    () -> configuredFeatures().getHolderOrThrow(NorthstarConfiguredFeatures.PLATE_FUNGUS),
                    () -> configuredFeatures().getHolderOrThrow(NorthstarConfiguredFeatures.ROOF_PLATE_FUNGUS)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GREEN)
                    .sound(SoundType.FUNGUS)
                    .strength(0f, 0.5f)
                    .noCollission()
                    .instabreak())
            .simpleItem()
            .register();

    public static final BlockEntry<HugeMushroomBlock> SPIKE_FUNGUS_BLOCK = REGISTRATE.block("spike_fungus_block", HugeMushroomBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.FUNGUS)
                    .strength(3f, 6f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<HugeMushroomBlock> BLOOM_FUNGUS_BLOCK = REGISTRATE.block("bloom_fungus_block", HugeMushroomBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.FUNGUS)
                    .strength(3f, 6f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<HugeMushroomBlock> BLOOM_FUNGUS_STEM_BLOCK = REGISTRATE.block("bloom_fungus_stem_block", HugeMushroomBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_ORANGE)
                    .sound(SoundType.FUNGUS)
                    .strength(3f, 6f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<HugeMushroomBlock> PLATE_FUNGUS_STEM_BLOCK = REGISTRATE.block("plate_fungus_stem_block", HugeMushroomBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                    .sound(SoundType.FUNGUS)
                    .strength(3f, 6f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<HugeMushroomBlock> PLATE_FUNGUS_CAP_BLOCK = REGISTRATE.block("plate_fungus_cap_block", HugeMushroomBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GREEN)
                    .sound(SoundType.FUNGUS)
                    .strength(4f, 6f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<TallFungusBlock> TOWER_FUNGUS = REGISTRATE
            .block("tower_fungus", p -> new TallFungusBlock(p,
                    () -> configuredFeatures().getHolderOrThrow(NorthstarConfiguredFeatures.TOWER_FUNGUS),
                    () -> configuredFeatures().getHolderOrThrow(NorthstarConfiguredFeatures.ROOF_TOWER_FUNGUS)))
            /*.initialProperties(SharedProperties::PLANT)*/
            .properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.FUNGUS)
                    .randomTicks()
                    .instabreak()
                    .noCollission())
            .simpleItem()
            .register();

    public static final BlockEntry<HugeMushroomBlock> TOWER_FUNGUS_STEM_BLOCK = REGISTRATE.block("tower_fungus_stem_block", HugeMushroomBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.FUNGUS)
                    .strength(3f, 6f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<HugeMushroomBlock> TOWER_FUNGUS_CAP_BLOCK = REGISTRATE.block("tower_fungus_cap_block", HugeMushroomBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.FUNGUS)
                    .strength(4f, 6f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<VenusVinesBlock> VENUS_VINES = REGISTRATE.block("venus_vines", VenusVinesBlock::new) /*.initialProperties(SharedProperties::LEAVES)*/.properties(p -> p.mapColor(MapColor.COLOR_GREEN)
                    .sound(SoundType.GRASS)
                    .strength(0.5f, 0.5f)
                    .randomTicks()
                    .noCollission()
                    .noOcclusion())
            .simpleItem()
            .register();


    public static final BlockEntry<VenusVinesBlock> GLOWING_VENUS_VINES = REGISTRATE.block("glowing_venus_vines", VenusVinesBlock::new) /*.initialProperties(SharedProperties::LEAVES)*/.properties(p -> p.mapColor(MapColor.COLOR_GREEN)
                    .sound(SoundType.GRASS)
                    .strength(0.5f, 0.5f)
                    .randomTicks()
                    .noCollission()
                    .noOcclusion()
                    .lightLevel((light) -> {
                        return 11;
                    })).

            register();

    public static final BlockEntry<VenusTallMyceliumBlock> TALL_VENUS_MYCELIUM = REGISTRATE.block("tall_venus_mycelium", VenusTallMyceliumBlock::new) /*.initialProperties(SharedProperties::REPLACEABLE_PLANT)*/.properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.GRASS)
                    .noCollission()
                    .instabreak()
                    .randomTicks()
                    .offsetType(BlockBehaviour.OffsetType.XZ))
            .simpleItem()
            .register();


    //venus deco blocks
    public static final BlockEntry<Block> VENUS_STONE_BRICKS = REGISTRATE.block("venus_stone_bricks", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<SlabBlock> VENUS_STONE_BRICK_SLAB = REGISTRATE.block("venus_stone_brick_slab", SlabBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<VerticalSlabBlock> VENUS_STONE_BRICK_SLAB_VERTICAL = REGISTRATE.block("venus_stone_brick_slab_vertical", VerticalSlabBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<StairBlock> VENUS_STONE_BRICK_STAIRS = REGISTRATE.block("venus_stone_brick_stairs", p -> new StairBlock(() -> STONE.defaultBlockState(), p)) /*.initialProperties(STONE)*/.properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<WallBlock> VENUS_STONE_BRICK_WALL = REGISTRATE.block("venus_stone_brick_wall", WallBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RotatedPillarBlock> VENUS_STONE_PILLAR = REGISTRATE.block("venus_stone_pillar", RotatedPillarBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> CHISELED_VENUS_STONE = REGISTRATE.block("chiseled_venus_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> POLISHED_VENUS_STONE = REGISTRATE.block("polished_venus_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> VENUS_STONE_LAMP = REGISTRATE.block("venus_stone_lamp", Block::new) /*.initialProperties(SharedProperties::DECORATION)*/.properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .lightLevel(state -> {
                        return 15;
                    }).

                    sound(SoundType.GLASS).

                    strength(3f, 6.5f).

                    requiresCorrectToolForDrops()).

            register();

    //venus ores
    public static final BlockEntry<Block> VENUS_COAL_ORE = REGISTRATE.block("venus_coal_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> VENUS_IRON_ORE = REGISTRATE.block("venus_iron_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> VENUS_COPPER_ORE = REGISTRATE.block("venus_copper_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> VENUS_GOLD_ORE = REGISTRATE.block("venus_gold_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> VENUS_DIAMOND_ORE = REGISTRATE.block("venus_diamond_ore", p -> new DropExperienceBlock(p, UniformInt.of(3, 7)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RedStoneOreBlock> VENUS_REDSTONE_ORE = REGISTRATE.block("venus_redstone_ore", RedStoneOreBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .lightLevel(litBlockEmission(9)))
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> VENUS_QUARTZ_ORE = REGISTRATE.block("venus_quartz_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> VENUS_GLOWSTONE_ORE = REGISTRATE.block("venus_glowstone_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> {
                        return 6;
                    })).

            register();

    //venus deep ores
    public static final BlockEntry<Block> VENUS_DEEP_IRON_ORE = REGISTRATE.block("venus_deep_iron_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> VENUS_DEEP_COPPER_ORE = REGISTRATE.block("venus_deep_copper_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> VENUS_DEEP_GOLD_ORE = REGISTRATE.block("venus_deep_gold_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> VENUS_DEEP_DIAMOND_ORE = REGISTRATE.block("venus_deep_diamond_ore", p -> new DropExperienceBlock(p, UniformInt.of(3, 7)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(7f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RedStoneOreBlock> VENUS_DEEP_REDSTONE_ORE = REGISTRATE.block("venus_deep_redstone_ore", RedStoneOreBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .lightLevel(litBlockEmission(9)))
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> VENUS_DEEP_QUARTZ_ORE = REGISTRATE.block("venus_deep_quartz_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> VENUS_DEEP_GLOWSTONE_ORE = REGISTRATE.block("venus_deep_glowstone_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> 6))
            .simpleItem()
            .register();


    //mercury stone stuff
    public static final BlockEntry<Block> MERCURY_STONE = REGISTRATE.block("mercury_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(3.5f, 8f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MERCURY_DEEP_STONE = REGISTRATE.block("mercury_deep_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(4.5f, 8f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    //mercury deco blocks
    public static final BlockEntry<Block> MERCURY_STONE_BRICKS = REGISTRATE.block("mercury_stone_bricks", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<SlabBlock> MERCURY_STONE_BRICK_SLAB = REGISTRATE.block("mercury_stone_brick_slab", SlabBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<VerticalSlabBlock> MERCURY_STONE_BRICK_SLAB_VERTICAL = REGISTRATE.block("mercury_stone_brick_slab_vertical", VerticalSlabBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<StairBlock> MERCURY_STONE_BRICK_STAIRS = REGISTRATE.block("mercury_stone_brick_stairs", p -> new StairBlock(() -> STONE.defaultBlockState(), p)) /*.initialProperties(STONE)*/.properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<WallBlock> MERCURY_STONE_BRICK_WALL = REGISTRATE.block("mercury_stone_brick_wall", WallBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RotatedPillarBlock> MERCURY_STONE_PILLAR = REGISTRATE.block("mercury_stone_pillar", RotatedPillarBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> CHISELED_MERCURY_STONE = REGISTRATE.block("chiseled_mercury_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> POLISHED_MERCURY_STONE = REGISTRATE.block("polished_mercury_stone", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(3.5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MERCURY_STONE_LAMP = REGISTRATE.block("mercury_stone_lamp", Block::new) /*.initialProperties(SharedProperties::DECORATION)*/.properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .lightLevel(state -> {
                        return 15;
                    }).

                    sound(SoundType.GLASS).

                    strength(3f, 6.5f).

                    requiresCorrectToolForDrops()).

            register();

    //mercury ores
    public static final BlockEntry<Block> MERCURY_IRON_ORE = REGISTRATE.block("mercury_iron_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MERCURY_COPPER_ORE = REGISTRATE.block("mercury_copper_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MERCURY_GOLD_ORE = REGISTRATE.block("mercury_gold_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MERCURY_DIAMOND_ORE = REGISTRATE.block("mercury_diamond_ore", p -> new DropExperienceBlock(p, UniformInt.of(3, 7)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RedStoneOreBlock> MERCURY_REDSTONE_ORE = REGISTRATE.block("mercury_redstone_ore", RedStoneOreBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .lightLevel(litBlockEmission(9)))
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MERCURY_LAPIS_ORE = REGISTRATE.block("mercury_lapis_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MERCURY_ZINC_ORE = REGISTRATE.block("mercury_zinc_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MERCURY_GLOWSTONE_ORE = REGISTRATE.block("mercury_glowstone_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(5f, 12f)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> {
                        return 15;
                    })).

            register();

    public static final BlockEntry<DropExperienceBlock> MERCURY_TUNGSTEN_ORE = REGISTRATE.block("mercury_tungsten_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 20f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    //mercury deep ores
    public static final BlockEntry<Block> MERCURY_DEEP_IRON_ORE = REGISTRATE.block("mercury_deep_iron_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MERCURY_DEEP_COPPER_ORE = REGISTRATE.block("mercury_deep_copper_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MERCURY_DEEP_GOLD_ORE = REGISTRATE.block("mercury_deep_gold_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MERCURY_DEEP_DIAMOND_ORE = REGISTRATE.block("mercury_deep_diamond_ore", p -> new DropExperienceBlock(p, UniformInt.of(3, 7)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(7f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RedStoneOreBlock> MERCURY_DEEP_REDSTONE_ORE = REGISTRATE.block("mercury_deep_redstone_ore", RedStoneOreBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .lightLevel(litBlockEmission(9)))
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MERCURY_DEEP_LAPIS_ORE = REGISTRATE.block("mercury_deep_lapis_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MERCURY_DEEP_ZINC_ORE = REGISTRATE.block("mercury_deep_zinc_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MERCURY_DEEP_GLOWSTONE_ORE = REGISTRATE.block("mercury_deep_glowstone_ore", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(6f, 12f)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> 15))
            .simpleItem()
            .register();


    public static final BlockEntry<DropExperienceBlock> MERCURY_DEEP_TUNGSTEN_ORE = REGISTRATE.block("mercury_deep_tungsten_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5)))
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.DEEPSLATE)
                    .strength(7f, 20f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<RotatedPillarBlock> CALORIAN_LOG = REGISTRATE.block("calorian_log", RotatedPillarBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.BASALT)
                    .strength(4f, 4f))
            .simpleItem()
            .register();


    public static final BlockEntry<Block> CALORIAN_PLANKS = REGISTRATE.block("calorian_planks", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.BASALT)
                    .strength(4f, 4f))
            .simpleItem()
            .register();


    public static final BlockEntry<SlabBlock> CALORIAN_SLAB = REGISTRATE.block("calorian_slab", SlabBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.BASALT)
                    .strength(4f, 4f))
            .simpleItem()
            .register();


    public static final BlockEntry<StairBlock> CALORIAN_STAIRS = REGISTRATE.block("calorian_stairs", p -> new StairBlock(() -> CALORIAN_PLANKS.get()
                    .defaultBlockState(), p)) /*.initialProperties(STONE)*/.properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.BASALT)
                    .strength(4f, 4f))
            .simpleItem()
            .register();


    public static final BlockEntry<MercuryShelfFungusBlock> MERCURY_SHELF_FUNGUS = REGISTRATE.block("mercury_shelf_fungus", MercuryShelfFungusBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.BASALT)
                    .strength(1f, 1f)
                    .noCollission()
                    .noOcclusion()
                    .isSuffocating(NorthstarBlocks::never)
                    .isViewBlocking(NorthstarBlocks::never))
            .simpleItem()
            .register();


    public static final BlockEntry<HugeMushroomBlock> MERCURY_SHELF_FUNGUS_BLOCK = REGISTRATE.block("mercury_shelf_fungus_block", HugeMushroomBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.BASALT)
                    .strength(4f, 6f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<MercuryCactusBlock> MERCURY_CACTUS = REGISTRATE.block("mercury_cactus", MercuryCactusBlock::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.BASALT)
                    .strength(4f, 6f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<Block> MONOLITHITE = REGISTRATE.block("monolithite", Block::new)
            /*.initialProperties(SharedProperties::STONE)*/
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(100f, 100f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<MethaneIceBlock> METHANE_ICE = REGISTRATE.block("methane_ice", MethaneIceBlock::new) /*.initialProperties(SharedProperties::ICE)*/.properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .sound(SoundType.GLASS)
                    .friction(0.989F)
                    .randomTicks()
                    .strength(0.5F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();


    public static final BlockEntry<IcicleBlock> ICICLE = REGISTRATE.block("icicle", IcicleBlock::new) /*.initialProperties(SharedProperties::ICE)*/.properties(p -> p.mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .sound(SoundType.GLASS)
                    .strength(3.5f, 12f)
                    .noOcclusion()
                    .dynamicShape()
                    .offsetType(BlockBehaviour.OffsetType.XZ))
            .simpleItem()
            .register();


    static {
        REGISTRATE.setCreativeTab(NorthstarCreativeModeTab.TECH);
    }

    public static final BlockEntry<TelescopeBlock> TELESCOPE = REGISTRATE.block("telescope", TelescopeBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.COLOR_BROWN)
                    .isViewBlocking(NorthstarBlocks::never)
                    .sound(SoundType.COPPER)
                    .strength(8f, 8f))
            .properties(BlockBehaviour.Properties::noOcclusion)
            .transform(axeOrPickaxe())
            .addLayer(() -> RenderType::cutoutMipped) /*transform(BlockStressDefaults.setCapacity(128.0)) .transform(BlockStressDefaults.setGeneratorSpeed(SolarPanelBlock::getSpeedRange))*/.item()
            .transform(customItemModel())
            .simpleItem()
            .register();


    public static final BlockEntry<InterplanetaryNavigatorBlock> INTERPLANETARY_NAVIGATOR = REGISTRATE.block("interplanetary_navigator", InterplanetaryNavigatorBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.METAL)
                    .strength(8f, 8f)
                    .noOcclusion()
                    .isViewBlocking(NorthstarBlocks::never))
            .simpleItem()
            .register();


    public static final BlockEntry<OxygenBubbleGeneratorBlock> OXYGEN_BUBBLE_GENERATOR = REGISTRATE.block("oxygen_bubble_generator", OxygenBubbleGeneratorBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.COPPER)
                    .strength(8f, 8f)
                    .requiresCorrectToolForDrops())
            .simpleItem()
            .register();

    private static boolean never(BlockState blockstate, BlockGetter blockgetter, BlockPos blockpos) {
        return false;
    }

    private static ToIntFunction<BlockState> litBlockEmission(int level) {
        return state -> state.getValue(BlockStateProperties.LIT) ? level : 0;
    }

    private static Registry<ConfiguredFeature<?,?>> configuredFeatures() {
        return Minecraft.getInstance().level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
