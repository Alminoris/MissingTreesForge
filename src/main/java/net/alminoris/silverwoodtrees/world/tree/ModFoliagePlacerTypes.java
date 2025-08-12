package net.alminoris.silverwoodtrees.world.tree;

import com.mojang.serialization.MapCodec;
import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.world.tree.custom.SilverMapleFoliagePlacer;
import net.alminoris.silverwoodtrees.world.tree.custom.StaghornSumacFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFoliagePlacerTypes
{
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, SilverwoodTrees.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<SilverMapleFoliagePlacer>> SILVER_MAPLE_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("silver_maple_foliage_placer", () -> new FoliagePlacerType<>(SilverMapleFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<StaghornSumacFoliagePlacer>> STAGHORN_SUMAC_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("staghorn_sumac_foliage_placer", () -> new FoliagePlacerType<>(StaghornSumacFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus)
    {
        FOLIAGE_PLACERS.register(eventBus);
    }
}