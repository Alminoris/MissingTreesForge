package net.alminoris.missingtrees.item;

import net.alminoris.missingtrees.MissingTrees;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MissingTrees.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItemGroups
{
    public static CreativeModeTab MISSINGTREES_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event)
    {
        MISSINGTREES_TAB = event.registerCreativeModeTab(ResourceLocation.fromNamespaceAndPath(MissingTrees.MOD_ID, "missingtreestab"),
                builder -> builder.icon(() -> new ItemStack(Blocks.FLOWERING_AZALEA.asItem()))
                        .title(Component.translatable("itemgroup.missingtreestab")));
    }
}