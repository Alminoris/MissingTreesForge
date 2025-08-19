package net.alminoris.missingtrees.item;

import net.alminoris.missingtrees.MissingTrees;
import net.alminoris.missingtrees.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.alminoris.missingtrees.util.helper.ModBlockSetsHelper.*;

@Mod.EventBusSubscriber(modid = MissingTrees.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItemGroups
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MissingTrees.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MISSINGTREES_TAB = CREATIVE_MODE_TABS.register("missingtreestab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> Blocks.FLOWERING_AZALEA.asItem().getDefaultInstance())
            .title(Component.translatable("itemgroup.missingtreestab"))
            .displayItems((parameters, output) ->
            {
                for (String name : WOOD_NAMES)
                {
                    if (!name.equals("azalea"))
                    {
                        if (name.equals("apple")) output.accept(Items.APPLE);
                        output.accept(WOODEN_SAPLINGS.get(name).get());
                    }
                    else
                    {
                        output.accept(Blocks.AZALEA);
                        output.accept(Blocks.FLOWERING_AZALEA);
                    }
                    if (!name.equals("azalea"))
                    {
                        output.accept(LEAVES.get(name).get());
                        if (name.equals("apple")) output.accept(ModBlocks.APPLE_FRUIT_LEAVES.get());
                    }
                    else
                    {
                        output.accept(Blocks.AZALEA_LEAVES);
                        output.accept(Blocks.FLOWERING_AZALEA_LEAVES);
                    }
                    output.accept(LOGS.get(name).get());
                    output.accept(WOODS.get(name).get());
                    output.accept(STRIPPED_LOGS.get(name).get());
                    output.accept(STRIPPED_WOODS.get(name).get());
                    output.accept(WOODEN_PLANKS.get(name).get());
                    output.accept(WOODEN_SLABS.get(name).get());
                    output.accept(WOODEN_STAIRS.get(name).get());
                    output.accept(WOODEN_FENCES.get(name).get());
                    output.accept(WOODEN_FENCE_GATES.get(name).get());
                    output.accept(WOODEN_DOORS.get(name).get());
                    output.accept(WOODEN_TRAPDOORS.get(name).get());
                    output.accept(WOODEN_BUTTONS.get(name).get());
                    output.accept(WOODEN_PRESSURE_PLATES.get(name).get());
                    output.accept(WOODEN_SIGN_ITEMS.get(name).get());
                    output.accept(WOODEN_HANGING_SIGN_ITEMS.get(name).get());
                    output.accept(WOODEN_BOATS.get(name).get());
                    output.accept(WOODEN_CHEST_BOATS.get(name).get());
                }
            }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}