package net.alminoris.silverwoodtrees.world.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.alminoris.silverwoodtrees.world.tree.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class StaghornSumacTrunkPlacer extends TrunkPlacer
{
    private static final Codec<UniformInt> BRANCH_START_CODEC = ExtraCodecs.validate(UniformInt.CODEC, (p_275181_) -> {
        return p_275181_.getMaxValue() - p_275181_.getMinValue() < 1 ? DataResult.error(() -> {
            return "Need at least 2 blocks variation for the branch starts to fit both branches";
        }) : DataResult.success(p_275181_);
    });
    public static final Codec<StaghornSumacTrunkPlacer> CODEC = RecordCodecBuilder.create((p_273579_) -> {
        return trunkPlacerParts(p_273579_).and(p_273579_.group(IntProvider.codec(1, 3).fieldOf("branch_count").forGetter((p_272644_) -> {
            return p_272644_.branchCount;
        }), IntProvider.codec(2, 16).fieldOf("branch_horizontal_length").forGetter((p_273612_) -> {
            return p_273612_.branchHorizontalLength;
        }), IntProvider.codec(-16, 0, BRANCH_START_CODEC).fieldOf("branch_start_offset_from_top").forGetter((p_272705_) -> {
            return p_272705_.branchStartOffsetFromTop;
        }), IntProvider.codec(-16, 16).fieldOf("branch_end_offset_from_top").forGetter((p_273633_) -> {
            return p_273633_.branchEndOffsetFromTop;
        }))).apply(p_273579_, StaghornSumacTrunkPlacer::new);
    });

    private final IntProvider branchCount;
    private final IntProvider branchHorizontalLength;
    private final UniformInt branchStartOffsetFromTop;
    private final UniformInt secondBranchStartOffsetFromTop;
    private final IntProvider branchEndOffsetFromTop;

    public StaghornSumacTrunkPlacer(
            int baseHeight,
            int firstRandomHeight,
            int secondRandomHeight,
            IntProvider branchCount,
            IntProvider branchHorizontalLength,
            UniformInt branchStartOffsetFromTop,
            IntProvider branchEndOffsetFromTop)
    {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
        this.branchCount = branchCount;
        this.branchHorizontalLength = branchHorizontalLength;
        this.branchStartOffsetFromTop = branchStartOffsetFromTop;
        this.secondBranchStartOffsetFromTop = UniformInt.of(branchStartOffsetFromTop.getMinValue(), branchStartOffsetFromTop.getMaxValue() - 1);
        this.branchEndOffsetFromTop = branchEndOffsetFromTop;
    }

    @Override
    protected TrunkPlacerType<?> type()
    {
        return ModTrunkPlacerTypes.STAGHORN_SUMAC_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world,
                                                            BiConsumer<BlockPos, BlockState> replacer,
                                                            RandomSource random,
                                                            int height,
                                                            BlockPos startPos,
                                                            TreeConfiguration config)
    {
        setDirtAt(world, replacer, random, startPos.below(), config);

        int i = Math.max(0, height - 1 + this.branchStartOffsetFromTop.sample(random));
        int j = Math.max(0, height - 1 + this.secondBranchStartOffsetFromTop.sample(random));
        if (j >= i) {
            j++;
        }

        int k = this.branchCount.sample(random);
        boolean threeBranches = k == 3;
        boolean atLeastTwo = k >= 2;

        int trunkTop;
        if (threeBranches) {
            trunkTop = height;
        } else if (atLeastTwo) {
            trunkTop = Math.max(i, j) + 1;
        } else {
            trunkTop = i + 1;
        }

        for (int m = 0; m < trunkTop; m++)
        {
            this.placeLog(world, replacer, random, startPos.above(m), config);
        }

        List<FoliagePlacer.FoliageAttachment> nodes = new ArrayList<>();
        if (threeBranches) {
            nodes.add(new FoliagePlacer.FoliageAttachment(startPos.above(trunkTop), 0, false));
        }

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);

        Function<BlockState, BlockState> withAxis = state ->
        {
            return state.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis());
        };

        nodes.add(this.generateBranch(world, replacer, random, height, startPos, config, withAxis, direction, i, i < trunkTop - 1, mutable));

        if (atLeastTwo) {
            nodes.add(this.generateBranch(world, replacer, random, height, startPos, config, withAxis, direction.getOpposite(), j, j < trunkTop - 1, mutable));
        }

        return nodes;
    }

    private FoliagePlacer.FoliageAttachment generateBranch(
            LevelSimulatedReader world,
            BiConsumer<BlockPos, BlockState> replacer,
            RandomSource random,
            int height,
            BlockPos startPos,
            TreeConfiguration config,
            Function<BlockState, BlockState> withAxisFunction,
            Direction direction,
            int branchStartOffset,
            boolean branchBelowHeight,
            BlockPos.MutableBlockPos mutablePos)
    {
        mutablePos.set(startPos).move(Direction.UP, branchStartOffset);

        int i = height - 1 + this.branchEndOffsetFromTop.sample(random);
        boolean goUp = branchBelowHeight || i < branchStartOffset;
        int horizontal = this.branchHorizontalLength.sample(random) + (goUp ? 1 : 0);

        BlockPos target = startPos.relative(direction, horizontal).above(i);
        int steps = goUp ? 2 : 1;

        for (int l = 0; l < steps; l++)
        {
            this.placeLog(world, replacer, random, mutablePos.move(direction), config, withAxisFunction);
        }

        Direction verticalDir = target.getY() > mutablePos.getY() ? Direction.UP : Direction.DOWN;

        while (true)
        {
            int dist = mutablePos.distManhattan(target);
            if (dist == 0)
            {
                return new FoliagePlacer.FoliageAttachment(target.above(), 0, false);
            }

            float f = (float) Math.abs(target.getY() - mutablePos.getY()) / dist;
            boolean moveVertically = random.nextFloat() < f;
            mutablePos.move(moveVertically ? verticalDir : direction);
            this.placeLog(world, replacer, random, mutablePos, config, moveVertically ? Function.identity() : withAxisFunction);
        }
    }
}