package net.alminoris.silverwoodtrees.block.entity;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SilverwoodTrees.MOD_ID);

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModBlockSetsHelper.WOODEN_SIGNS.get("walnut").get(), ModBlockSetsHelper.WOODEN_WALL_SIGNS.get("walnut").get(),
                            ModBlockSetsHelper.WOODEN_SIGNS.get("silver_maple").get(), ModBlockSetsHelper.WOODEN_WALL_SIGNS.get("silver_maple").get(),
                            ModBlockSetsHelper.WOODEN_SIGNS.get("staghorn_sumac").get(), ModBlockSetsHelper.WOODEN_WALL_SIGNS.get("staghorn_sumac").get(),
                            ModBlockSetsHelper.WOODEN_SIGNS.get("silverberry").get(), ModBlockSetsHelper.WOODEN_WALL_SIGNS.get("silverberry").get()).build(null));

    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                            ModBlockSetsHelper.WOODEN_HANGING_SIGNS.get("walnut").get(), ModBlockSetsHelper.WOODEN_WALL_HANGING_SIGNS.get("walnut").get(),
                            ModBlockSetsHelper.WOODEN_HANGING_SIGNS.get("silver_maple").get(), ModBlockSetsHelper.WOODEN_WALL_HANGING_SIGNS.get("silver_maple").get(),
                            ModBlockSetsHelper.WOODEN_HANGING_SIGNS.get("staghorn_sumac").get(), ModBlockSetsHelper.WOODEN_WALL_HANGING_SIGNS.get("staghorn_sumac").get(),
                            ModBlockSetsHelper.WOODEN_HANGING_SIGNS.get("silverberry").get(), ModBlockSetsHelper.WOODEN_WALL_HANGING_SIGNS.get("silverberry").get()).build(null));


    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}