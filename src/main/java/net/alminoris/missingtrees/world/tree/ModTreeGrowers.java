package net.alminoris.missingtrees.world.tree;

import net.alminoris.missingtrees.util.helper.ModBlockSetsHelper;
import net.alminoris.missingtrees.world.ModConfiguredFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Dictionary;
import java.util.Hashtable;

public class ModTreeGrowers
{
    public static final Dictionary<String, ResourceKey<ConfiguredFeature<?, ?>>> keys = new Hashtable<>()
    {{
        put("apple", ModConfiguredFeatures.APPLE_KEY);
        put("scots_pine", TreeFeatures.PINE);
        put("swamp_oak", TreeFeatures.SWAMP_OAK);
    }};

    public static final Dictionary<String, AbstractTreeGrower> saplingGenerators = new Hashtable<>()
    {{
        for(String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            put(name, new CustomTreeGrower(keys.get(name)));
        }
    }};
}