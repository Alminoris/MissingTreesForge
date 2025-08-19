package net.alminoris.missingtrees;

import com.mojang.logging.LogUtils;
import net.alminoris.missingtrees.block.ModBlocks;
import net.alminoris.missingtrees.block.entity.ModBlockEntities;
import net.alminoris.missingtrees.entity.ModEntities;
import net.alminoris.missingtrees.entity.client.ModBoatRenderer;
import net.alminoris.missingtrees.item.ModItemGroups;
import net.alminoris.missingtrees.item.ModItems;
import net.alminoris.missingtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static net.alminoris.missingtrees.util.helper.ModBlockSetsHelper.*;
import static net.alminoris.missingtrees.util.helper.ModBlockSetsHelper.WOODEN_CHEST_BOATS;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MissingTrees.MOD_ID)
public class MissingTrees
{
    public static final String MOD_ID = "missingtrees";

    private static final Logger LOGGER = LogUtils.getLogger();

    public MissingTrees(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        ModItemGroups.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModEntities.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        //if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
        //event.accept(EXAMPLE_BLOCK_ITEM);
    }

        // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            for(String name : WOOD_NAMES)
                Sheets.addWoodType(ModBlockSetsHelper.WOOD_TYPES.get(name));

            EntityRenderers.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
            EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));

            event.enqueueWork(() ->
            {
                for (String name : WOOD_NAMES)
                {
                    ItemBlockRenderTypes.setRenderLayer(ModBlockSetsHelper.WOODEN_DOORS.get(name).get(), RenderType.cutout());

                    ItemBlockRenderTypes.setRenderLayer(ModBlockSetsHelper.WOODEN_TRAPDOORS.get(name).get(), RenderType.cutout());

                    if (!name.equals("azalea"))
                        ItemBlockRenderTypes.setRenderLayer(ModBlockSetsHelper.WOODEN_SAPLINGS.get(name).get(), RenderType.cutout());
                }
            });
        }
    }
}