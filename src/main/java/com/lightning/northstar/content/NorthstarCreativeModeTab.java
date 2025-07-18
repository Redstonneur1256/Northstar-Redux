package com.lightning.northstar.content;

import com.lightning.northstar.Northstar;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.lightning.northstar.Northstar.REGISTRATE;

public class NorthstarCreativeModeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Northstar.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ITEMS = CREATIVE_TABS
            .register("northstar_items", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.northstar.items"))
                    .icon(() -> new ItemStack(NorthstarItems.MARTIAN_STEEL.get()))
                    .displayItems(createItemDisplay(NorthstarCreativeModeTab.ITEMS))
                    .build());

    public static final RegistryObject<CreativeModeTab> BLOCKS = CREATIVE_TABS
            .register("northstar_blocks", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.northstar.blocks"))
                    .icon(() -> new ItemStack(NorthstarBlocks.MARTIAN_STEEL_BLOCK.get()))
                    .displayItems(createItemDisplay(NorthstarCreativeModeTab.BLOCKS))
                    .build());

    public static final RegistryObject<CreativeModeTab> TECH = CREATIVE_TABS
            .register("northstar_tech", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.northstar.tech"))
                    .icon(() -> new ItemStack(NorthstarBlocks.TELESCOPE.get()))
                    .displayItems(createItemDisplay(NorthstarCreativeModeTab.TECH))
                    .build());

    private static CreativeModeTab.DisplayItemsGenerator createItemDisplay(RegistryObject<CreativeModeTab> tab) {
        return (parameters, output) -> {
            for (RegistryEntry<Item> item : REGISTRATE.getAll(Registries.ITEM)) {
                if (CreateRegistrate.isInCreativeTab(item, tab)) {
                    output.accept(item.get());
                }
            }
        };
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }

}
