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
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class StaghornSumacFoliagePlacer extends CherryFoliagePlacer
{
    public static final MapCodec<StaghornSumacFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> foliagePlacerParts(instance)
                    .and(
                            instance.group(
                                    IntProvider.codec(4, 16).fieldOf("height").forGetter(p -> p.height),
                                    Codec.floatRange(0.0F, 1.0F).fieldOf("wide_bottom_layer_hole_chance").forGetter(p -> p.wideBottomLayerHoleChance),
                                    Codec.floatRange(0.0F, 1.0F).fieldOf("corner_hole_chance").forGetter(p -> p.cornerHoleChance),
                                    Codec.floatRange(0.0F, 1.0F).fieldOf("hanging_leaves_chance").forGetter(p -> p.hangingLeavesChance),
                                    Codec.floatRange(0.0F, 1.0F).fieldOf("hanging_leaves_extension_chance").forGetter(p -> p.hangingLeavesExtensionChance)
                            )
                    )
                    .apply(instance, StaghornSumacFoliagePlacer::new)
    );


    private final IntProvider height;
    private final float wideBottomLayerHoleChance;
    private final float cornerHoleChance;
    private final float hangingLeavesChance;
    private final float hangingLeavesExtensionChance;

    public StaghornSumacFoliagePlacer(
            IntProvider radius,
            IntProvider offset,
            IntProvider height,
            float wideBottomLayerHoleChance,
            float cornerHoleChance,
            float hangingLeavesChance,
            float hangingLeavesExtensionChance)
    {
        super(radius, offset, height, wideBottomLayerHoleChance, cornerHoleChance, hangingLeavesChance, hangingLeavesExtensionChance);
        this.height = height;
        this.wideBottomLayerHoleChance = wideBottomLayerHoleChance;
        this.cornerHoleChance = cornerHoleChance;
        this.hangingLeavesChance = hangingLeavesChance;
        this.hangingLeavesExtensionChance = hangingLeavesExtensionChance;
    }

    @Override
    protected FoliagePlacerType<?> type()
    {
        return ModFoliagePlacerTypes.STAGHORN_SUMAC_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config,
                                 int trunkHeight, FoliageAttachment treeNode, int foliageHeight, int radius, int offset)
    {
        super.createFoliage(world, placer, random, config, trunkHeight, treeNode, foliageHeight, radius, offset);

        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int y = -foliageHeight; y <= offset; y++) {
            int layerRadius = radius + (random.nextInt(2) - 1);
            for (int x = -layerRadius; x <= layerRadius; x++) {
                for (int z = -layerRadius; z <= layerRadius; z++) {
                    double dist = Math.sqrt(x * x + z * z);

                    if (dist <= layerRadius + random.nextFloat() * 0.5f) {
                        if (random.nextFloat() > 0.25f) {
                            BlockPos foliagePos = mutablePos.set(
                                    treeNode.pos().getX() + x,
                                    treeNode.pos().getY() + y,
                                    treeNode.pos().getZ() + z
                            );
                            placer.set(foliagePos, config.foliageProvider.getState(random, foliagePos));
                        }
                    }
                }
            }
        }
    }
}