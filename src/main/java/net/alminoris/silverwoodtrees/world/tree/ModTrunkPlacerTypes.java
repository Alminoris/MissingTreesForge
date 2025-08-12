package net.alminoris.silverwoodtrees.world.tree;

import com.mojang.serialization.MapCodec;
import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.world.tree.custom.StaghornSumacTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrunkPlacerTypes
{
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, SilverwoodTrees.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<StaghornSumacTrunkPlacer>> STAGHORN_SUMAC_TRUNK_PLACER =
            TRUNK_PLACERS.register("staghorn_sumac_trunk_placer",
                    () -> new TrunkPlacerType<>(StaghornSumacTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus)
    {
        TRUNK_PLACERS.register(eventBus);
    }
}