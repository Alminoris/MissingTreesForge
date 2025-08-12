package net.alminoris.silverwoodtrees.world;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures
{
    public static final ResourceKey<PlacedFeature> WALNUT_PLACED_KEY = registerKey("walnut_placed");
    public static final ResourceKey<PlacedFeature> SILVER_MAPLE_PLACED_KEY = registerKey("silver_maple_placed");
    public static final ResourceKey<PlacedFeature> STAGHORN_SUMAC_PLACED_KEY = registerKey("staghorn_sumac_placed");
    public static final ResourceKey<PlacedFeature> SILVERBERRY_PLACED_KEY = registerKey("silverberry_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context)
    {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, WALNUT_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WALNUT_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1),
                        ModBlockSetsHelper.WOODEN_SAPLINGS.get("walnut").get()));

        register(context, SILVER_MAPLE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_MAPLE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1),
                        ModBlockSetsHelper.WOODEN_SAPLINGS.get("silver_maple").get()));

        register(context, STAGHORN_SUMAC_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.STAGHORN_SUMAC_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1),
                        ModBlockSetsHelper.WOODEN_SAPLINGS.get("staghorn_sumac").get()));

        register(context, SILVERBERRY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVERBERRY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.05f, 1),
                        ModBlockSetsHelper.WOODEN_SAPLINGS.get("silverberry").get()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(SilverwoodTrees.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}