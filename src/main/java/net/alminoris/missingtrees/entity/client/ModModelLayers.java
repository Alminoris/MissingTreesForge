package net.alminoris.missingtrees.entity.client;

import net.alminoris.missingtrees.MissingTrees;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import java.util.Dictionary;
import java.util.Hashtable;

import static net.alminoris.missingtrees.util.helper.ModBlockSetsHelper.WOOD_NAMES;

public class ModModelLayers
{
    public static final Dictionary<String, ModelLayerLocation> BOAT_LAYERS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, new ModelLayerLocation(
                     ResourceLocation.fromNamespaceAndPath(MissingTrees.MOD_ID, "boat/"+name), "main"));
    }};

    public static final Dictionary<String, ModelLayerLocation> CHEST_BOAT_LAYERS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, new ModelLayerLocation(
                    ResourceLocation.fromNamespaceAndPath(MissingTrees.MOD_ID, "chest_boat/"+name), "main"));
    }};
}