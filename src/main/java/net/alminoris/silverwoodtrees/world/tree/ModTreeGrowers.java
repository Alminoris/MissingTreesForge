package net.alminoris.silverwoodtrees.world.tree;

import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.alminoris.silverwoodtrees.world.ModConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Optional;

public class ModTreeGrowers
{
    public static final Dictionary<String, ResourceKey<ConfiguredFeature<?, ?>>> keys = new Hashtable<>()
    {{
        put("walnut", ModConfiguredFeatures.WALNUT_KEY);
        put("silver_maple", ModConfiguredFeatures.SILVER_MAPLE_KEY);
        put("staghorn_sumac", ModConfiguredFeatures.STAGHORN_SUMAC_KEY);
        put("silverberry", ModConfiguredFeatures.SILVERBERRY_KEY);
    }};

    public static final Dictionary<String, AbstractTreeGrower> saplingGenerators = new Hashtable<>()
    {{
        for(String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            put(name, new CustomTreeGrower(keys.get(name)));
        }
    }};
}