package net.alminoris.missingtrees.world.tree;

import net.alminoris.missingtrees.util.helper.ModBlockSetsHelper;
import net.alminoris.missingtrees.world.ModConfiguredFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Optional;

public class ModTreeGrowers
{
    public static final Dictionary<String, ResourceKey<ConfiguredFeature<?, ?>>> keys = new Hashtable<>()
    {{
        put("apple", ModConfiguredFeatures.APPLE_KEY);
        put("scots_pine", TreeFeatures.PINE);
        put("swamp_oak", TreeFeatures.SWAMP_OAK);
    }};

    public static final Dictionary<String, TreeGrower> saplingGenerators = new Hashtable<>()
    {{
        for(String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            if (!name.equals("azalea"))
                put(name, new TreeGrower(name, 0f, Optional.empty(),
                    Optional.empty(),
                    Optional.of(keys.get(name)),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()));
        }
    }};
}