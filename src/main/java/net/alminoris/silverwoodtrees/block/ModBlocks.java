package net.alminoris.silverwoodtrees.block;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.block.custom.ModHangingSignBlock;
import net.alminoris.silverwoodtrees.block.custom.ModStandingSignBlock;
import net.alminoris.silverwoodtrees.block.custom.ModWallHangingSignBlock;
import net.alminoris.silverwoodtrees.block.custom.ModWallSignBlock;
import net.alminoris.silverwoodtrees.item.ModItems;
import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SilverwoodTrees.MOD_ID);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }

    public static RegistryObject<Block> registerLeavesBlock(String name)
    {
        return registerBlock(name+"_leaves",
                () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    }

    public static RegistryObject<Block> registerLogsBlock(String name)
    {
        return registerBlock(name+"_log",
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    }

    public static RegistryObject<Block> registerWoodsBlock(String name)
    {
        return registerBlock(name+"_wood",
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    }

    public static RegistryObject<Block> registerStrippedLogsBlock(String name)
    {
        return registerBlock("stripped_"+name+"_log",
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    }

    public static RegistryObject<Block> registerStrippedWoodsBlock(String name)
    {
        return registerBlock("stripped_"+name+"_wood",
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
    }

    public static RegistryObject<Block> registerPlanksBlock(String name)
    {
        return registerBlock(name+"_planks",
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    }

    public static RegistryObject<Block> registerSlabBlock(String name)
    {
        return registerBlock(name+"_slab",
                () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    }

    public static RegistryObject<Block> registerStairsBlock(String name, RegistryObject<Block> planks)
    {
        return registerBlock(name+"_stairs",
                () -> new StairBlock(planks.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    }

    public static RegistryObject<Block> registerFenceBlock(String name)
    {
        return registerBlock(name+"_fence",
                () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    }

    public static RegistryObject<Block> registerFenceGateBlock(String name)
    {
        return registerBlock(name+"_fence_gate",
                () -> new FenceGateBlock(ModBlockSetsHelper.WOOD_TYPES.get(name), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    }

    public static RegistryObject<Block> registerDoorBlock(String name)
    {
        return registerBlock(name+"_door",
                () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    }

    public static RegistryObject<Block> registerTrapdoorBlock(String name)
    {
        return registerBlock(name+"_trapdoor",
                () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    }

    public static RegistryObject<Block> registerButtonBlock(String name)
    {
        return registerBlock(name+"_button",
                () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    }

    public static RegistryObject<Block> registerPressurePlateBlock(String name)
    {
        return registerBlock(name+"_pressure_plate",
                () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    }

    public static RegistryObject<Block> registerSaplingBlock(String name, TreeGrower saplingGenerator)
    {
        return registerBlock(name+"_sapling",
                () -> new SaplingBlock(saplingGenerator, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    }

    public static RegistryObject<Block> registerSignBlock(String name)
    {
        return registerBlock(name + "_sign",
                () -> new ModStandingSignBlock(ModBlockSetsHelper.WOOD_TYPES.get(name), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    }

    public static RegistryObject<Block> registerWallSignBlock(String name)
    {
        return registerBlock(name+"_wall_sign",
                () -> new ModWallSignBlock(ModBlockSetsHelper.WOOD_TYPES.get(name), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    }

    public static RegistryObject<Block> registerHangingSignBlock(String name)
    {
        return registerBlock(name+"_hanging_sign",
                () -> new ModHangingSignBlock(ModBlockSetsHelper.WOOD_TYPES.get(name), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    }

    public static RegistryObject<Block> registerWallHangingSignBlock(String name)
    {
        return registerBlock(name+"_wall_hanging_sign",
                () -> new ModWallHangingSignBlock(ModBlockSetsHelper.WOOD_TYPES.get(name), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));
    }
}