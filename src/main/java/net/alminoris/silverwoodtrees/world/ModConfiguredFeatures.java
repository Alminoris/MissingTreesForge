package net.alminoris.silverwoodtrees.world;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.alminoris.silverwoodtrees.world.tree.custom.SilverMapleFoliagePlacer;
import net.alminoris.silverwoodtrees.world.tree.custom.StaghornSumacTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModConfiguredFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> WALNUT_KEY = registerKey("walnut");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_MAPLE_KEY = registerKey("silver_maple");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STAGHORN_SUMAC_KEY = registerKey("staghorn_sumac");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVERBERRY_KEY = registerKey("silverberry");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context)
    {
        register(context, WALNUT_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlockSetsHelper.LOGS.get("walnut").get()),
                new BendingTrunkPlacer(4, 2, 3, 4, ConstantInt.of(2)),

                BlockStateProvider.simple(ModBlockSetsHelper.LEAVES.get("walnut").get()),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),

                new TwoLayersFeatureSize(2, 0, 2)).build());

        register(context, SILVER_MAPLE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlockSetsHelper.LOGS.get("silver_maple").get()),
                new StraightTrunkPlacer(5, 1, 1),

                BlockStateProvider.simple(ModBlockSetsHelper.LEAVES.get("silver_maple").get()),
                new SilverMapleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 7),

                new TwoLayersFeatureSize(2, 0, 2)).build());

        register(context, STAGHORN_SUMAC_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlockSetsHelper.LOGS.get("staghorn_sumac").get()),
                new StaghornSumacTrunkPlacer(2, 1, 1,
                        ConstantInt.of(3),
                        UniformInt.of(2, 3),
                        UniformInt.of(-1, 0),
                        UniformInt.of(1, 2)),

                BlockStateProvider.simple(ModBlockSetsHelper.LEAVES.get("staghorn_sumac").get()),
                new CherryFoliagePlacer(
                        UniformInt.of(3, 4),
                        ConstantInt.of(2),
                        UniformInt.of(4, 6),
                        0.4f,
                        0.3f,
                        0.2f,
                        0.15f
                ),

                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, SILVERBERRY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlockSetsHelper.LOGS.get("silverberry").get()),
                new ForkingTrunkPlacer(3, 2, 2),

                BlockStateProvider.simple(ModBlockSetsHelper.LEAVES.get("silverberry").get()),
                new BushFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), 2),

                new TwoLayersFeatureSize(1, 0, 1)).build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(SilverwoodTrees.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}