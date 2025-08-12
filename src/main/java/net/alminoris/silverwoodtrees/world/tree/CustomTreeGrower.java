package net.alminoris.silverwoodtrees.world.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class CustomTreeGrower extends AbstractTreeGrower
{
    private final ResourceKey<ConfiguredFeature<?, ?>> key;

    public CustomTreeGrower(ResourceKey<ConfiguredFeature<?, ?>> key)
    {
        this.key = key;
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers)
    {
        return key;
    }
}
