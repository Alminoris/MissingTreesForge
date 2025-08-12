package net.alminoris.silverwoodtrees;

import com.mojang.logging.LogUtils;
import net.alminoris.silverwoodtrees.block.ModBlocks;
import net.alminoris.silverwoodtrees.block.entity.ModBlockEntities;
import net.alminoris.silverwoodtrees.entity.ModEntities;
import net.alminoris.silverwoodtrees.entity.client.ModBoatRenderer;
import net.alminoris.silverwoodtrees.item.ModItemGroups;
import net.alminoris.silverwoodtrees.item.ModItems;
import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.alminoris.silverwoodtrees.world.tree.ModFoliagePlacerTypes;
import net.alminoris.silverwoodtrees.world.tree.ModTrunkPlacerTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import static net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper.*;
import static net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper.WOODEN_CHEST_BOATS;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SilverwoodTrees.MOD_ID)
public class SilverwoodTrees
{
    public static final String MOD_ID = "silverwoodtrees";

    private static final Logger LOGGER = LogUtils.getLogger();

    public SilverwoodTrees(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModEntities.register(modEventBus);

        ModTrunkPlacerTypes.register(modEventBus);

        ModFoliagePlacerTypes.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        //if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            //event.accept(EXAMPLE_BLOCK_ITEM);

        if(event.getTab() == ModItemGroups.SILVERWOODTREES_TAB)
        {
            for (String name : WOOD_NAMES)
            {
                event.accept(WOODEN_SAPLINGS.get(name).get());
                event.accept(LEAVES.get(name).get());
                event.accept(LOGS.get(name).get());
                event.accept(WOODS.get(name).get());
                event.accept(STRIPPED_LOGS.get(name).get());
                event.accept(STRIPPED_WOODS.get(name).get());
                event.accept(WOODEN_PLANKS.get(name).get());
                event.accept(WOODEN_SLABS.get(name).get());
                event.accept(WOODEN_STAIRS.get(name).get());
                event.accept(WOODEN_FENCES.get(name).get());
                event.accept(WOODEN_FENCE_GATES.get(name).get());
                event.accept(WOODEN_DOORS.get(name).get());
                event.accept(WOODEN_TRAPDOORS.get(name).get());
                event.accept(WOODEN_BUTTONS.get(name).get());
                event.accept(WOODEN_PRESSURE_PLATES.get(name).get());
                event.accept(WOODEN_SIGN_ITEMS.get(name).get());
                event.accept(WOODEN_HANGING_SIGN_ITEMS.get(name).get());
                event.accept(WOODEN_BOATS.get(name).get());
                event.accept(WOODEN_CHEST_BOATS.get(name).get());
            }
        }
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

            event.enqueueWork(() -> {
                for (String name : WOOD_NAMES) {
                    // Двери
                    ItemBlockRenderTypes.setRenderLayer(ModBlockSetsHelper.WOODEN_DOORS.get(name).get(), RenderType.cutout());

                    // Люки
                    ItemBlockRenderTypes.setRenderLayer(ModBlockSetsHelper.WOODEN_TRAPDOORS.get(name).get(), RenderType.cutout());

                    // Саженцы
                    ItemBlockRenderTypes.setRenderLayer(ModBlockSetsHelper.WOODEN_SAPLINGS.get(name).get(), RenderType.cutout());
                }
            });
        }
    }
}