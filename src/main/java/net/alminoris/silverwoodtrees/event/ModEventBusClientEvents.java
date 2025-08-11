package net.alminoris.silverwoodtrees.event;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.block.entity.ModBlockEntities;
import net.alminoris.silverwoodtrees.entity.client.ModModelLayers;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper.WOOD_NAMES;

@Mod.EventBusSubscriber(modid = SilverwoodTrees.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents
{
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        for(String name : WOOD_NAMES)
        {
            event.registerLayerDefinition(ModModelLayers.BOAT_LAYERS.get(name), BoatModel::createBodyModel);
            event.registerLayerDefinition(ModModelLayers.CHEST_BOAT_LAYERS.get(name), ChestBoatModel::createBodyModel);
        }
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}
