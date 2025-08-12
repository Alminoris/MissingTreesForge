package net.alminoris.silverwoodtrees.world.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.alminoris.silverwoodtrees.world.tree.ModFoliagePlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class SilverMapleFoliagePlacer extends FancyFoliagePlacer
{
    public static final Codec<SilverMapleFoliagePlacer> CODEC = RecordCodecBuilder.create(
            instance -> blobParts(instance).apply(instance, SilverMapleFoliagePlacer::new)
    );

    public SilverMapleFoliagePlacer(IntProvider radius, IntProvider offset, int height)
    {
        super(radius, offset, height);
    }

    @Override
    protected FoliagePlacerType<?> type()
    {
        return ModFoliagePlacerTypes.SILVER_MAPLE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(
            LevelSimulatedReader world,
            FoliagePlacer.FoliageSetter placer,
            RandomSource random,
            TreeConfiguration config,
            int trunkHeight,
            FoliagePlacer.FoliageAttachment treeNode,
            int foliageHeight,
            int radius,
            int offset)
    {
        int c = 0;
        for (int i = offset; i >= offset - foliageHeight; i--)
        {
            int rad = radius;

            int i1 = i != offset && i != offset - foliageHeight ? 1 : 0;
            if (c == 3)
            {
                int j = rad + i1;
                this.placeLeavesRowWithoutCorners(world, placer, random, config, treeNode.pos(), j, i, treeNode.doubleTrunk());
            }
            else if (c < 3)
            {
                rad--;
                int j = rad + i1;
                this.placeLeavesRow(world, placer, random, config, treeNode.pos(), j, i, treeNode.doubleTrunk());
            }
            else
            {
                int j = rad + i1;
                this.placeLeavesRow(world, placer, random, config, treeNode.pos(), j, i, treeNode.doubleTrunk());
            }
            c++;
        }
    }

    protected void placeLeavesRowWithoutCorners(
            LevelSimulatedReader world,
            FoliagePlacer.FoliageSetter placer,
            RandomSource random,
            TreeConfiguration config,
            BlockPos centerPos,
            int radius,
            int y,
            boolean giantTrunk)
    {
        int i = giantTrunk ? 1 : 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int j = -radius; j <= radius + i; j++)
        {
            for (int k = -radius; k <= radius + i; k++)
            {
                if ((Math.abs(j) == radius && Math.abs(k) == radius) ||
                        (Math.abs(j) == radius && Math.abs(k) == radius - 1) ||
                        (Math.abs(j) == radius - 1 && Math.abs(k) == radius) ||
                        (Math.abs(j) == radius - 1 && Math.abs(k) == radius - 1) ||
                        (Math.abs(j) == radius && Math.abs(k) == radius - 2) ||
                        (Math.abs(j) == radius - 2 && Math.abs(k) == radius) ||
                        (Math.abs(j) == radius - 2 && Math.abs(k) == radius - 1) ||
                        (Math.abs(j) == radius - 1 && Math.abs(k) == radius - 2) ||
                        (Math.abs(j) == radius - 2 && Math.abs(k) == radius - 2) ||
                        (Math.abs(j) == radius - 3 && Math.abs(k) == radius - 3) ||
                        (Math.abs(j) == radius && Math.abs(k) == radius - 3) ||
                        (Math.abs(j) == radius - 3 && Math.abs(k) == radius))
                {
                    continue;
                }

                if (!this.shouldSkipLocationSigned(random, j, y, k, radius, giantTrunk))
                {
                    blockpos$mutableblockpos.setWithOffset(centerPos, j, y, k);
                    tryPlaceLeaf(world, placer, random, config, blockpos$mutableblockpos);
                }
            }
        }
    }
}