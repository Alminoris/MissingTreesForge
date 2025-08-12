package net.alminoris.silverwoodtrees.item;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SilverwoodTrees.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItemGroups
{
    public static CreativeModeTab SILVERWOODTREES_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event)
    {
        SILVERWOODTREES_TAB = event.registerCreativeModeTab(ResourceLocation.fromNamespaceAndPath(SilverwoodTrees.MOD_ID, "silverwoodtreestab"),
                builder -> builder.icon(() -> new ItemStack(ModBlockSetsHelper.WOODEN_SAPLINGS.get("silverberry").get().asItem()))
                        .title(Component.translatable("itemgroup.silverwoodtreestab")));
    }
}