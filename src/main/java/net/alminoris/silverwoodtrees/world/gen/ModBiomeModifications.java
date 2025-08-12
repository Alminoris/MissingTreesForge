package net.alminoris.silverwoodtrees.world.gen;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.world.ModPlacedFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifications
{
    public static final ResourceKey<BiomeModifier> ADD_WALNUT_TREE = registerKey("add_tree_walnut");

    public static final ResourceKey<BiomeModifier> ADD_SILVER_MAPLE_TREE = registerKey("add_tree_silver_maple");

    public static final ResourceKey<BiomeModifier> ADD_STAGHORN_SUMAC_TREE = registerKey("add_tree_staghorn_sumac");

    public static final ResourceKey<BiomeModifier> ADD_SILVERBERRY_TREE = registerKey("add_tree_silverberry");

    public static void bootstrap(BootstapContext<BiomeModifier> context)
    {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_WALNUT_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.FOREST)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.WALNUT_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_SILVER_MAPLE_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.FLOWER_FOREST)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.SILVER_MAPLE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_STAGHORN_SUMAC_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SAVANNA_PLATEAU)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.STAGHORN_SUMAC_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_SILVERBERRY_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.TAIGA)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.SILVERBERRY_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name)
    {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(SilverwoodTrees.MOD_ID, name));
    }
}