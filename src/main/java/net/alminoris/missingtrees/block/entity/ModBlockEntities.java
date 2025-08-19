package net.alminoris.missingtrees.block.entity;

import net.alminoris.missingtrees.MissingTrees;
import net.alminoris.missingtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MissingTrees.MOD_ID);

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModBlockSetsHelper.WOODEN_SIGNS.get("azalea").get(), ModBlockSetsHelper.WOODEN_WALL_SIGNS.get("azalea").get(),
                            ModBlockSetsHelper.WOODEN_SIGNS.get("apple").get(), ModBlockSetsHelper.WOODEN_WALL_SIGNS.get("apple").get(),
                            ModBlockSetsHelper.WOODEN_SIGNS.get("scots_pine").get(), ModBlockSetsHelper.WOODEN_WALL_SIGNS.get("scots_pine").get(),
                            ModBlockSetsHelper.WOODEN_SIGNS.get("swamp_oak").get(), ModBlockSetsHelper.WOODEN_WALL_SIGNS.get("swamp_oak").get()).build(null));

    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                            ModBlockSetsHelper.WOODEN_HANGING_SIGNS.get("azalea").get(), ModBlockSetsHelper.WOODEN_WALL_HANGING_SIGNS.get("azalea").get(),
                            ModBlockSetsHelper.WOODEN_HANGING_SIGNS.get("apple").get(), ModBlockSetsHelper.WOODEN_WALL_HANGING_SIGNS.get("apple").get(),
                            ModBlockSetsHelper.WOODEN_HANGING_SIGNS.get("scots_pine").get(), ModBlockSetsHelper.WOODEN_WALL_HANGING_SIGNS.get("scots_pine").get(),
                            ModBlockSetsHelper.WOODEN_HANGING_SIGNS.get("swamp_oak").get(), ModBlockSetsHelper.WOODEN_WALL_HANGING_SIGNS.get("swamp_oak").get()).build(null));


    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}