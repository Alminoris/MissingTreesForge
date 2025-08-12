package net.alminoris.silverwoodtrees.block;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.block.custom.ModHangingSignBlock;
import net.alminoris.silverwoodtrees.block.custom.ModStandingSignBlock;
import net.alminoris.silverwoodtrees.block.custom.ModWallHangingSignBlock;
import net.alminoris.silverwoodtrees.block.custom.ModWallSignBlock;
import net.alminoris.silverwoodtrees.item.ModItems;
import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
                () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    }

    public static RegistryObject<Block> registerLogsBlock(String name)
    {
        return registerBlock(name+"_log",
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    }

    public static RegistryObject<Block> registerWoodsBlock(String name)
    {
        return registerBlock(name+"_wood",
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    }

    public static RegistryObject<Block> registerStrippedLogsBlock(String name)
    {
        return registerBlock("stripped_"+name+"_log",
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    }

    public static RegistryObject<Block> registerStrippedWoodsBlock(String name)
    {
        return registerBlock("stripped_"+name+"_wood",
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    }

    public static RegistryObject<Block> registerPlanksBlock(String name)
    {
        return registerBlock(name+"_planks",
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    }

    public static RegistryObject<Block> registerSlabBlock(String name)
    {
        return registerBlock(name+"_slab",
                () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
    }

    public static RegistryObject<Block> registerStairsBlock(String name, RegistryObject<Block> planks)
    {
        return registerBlock(name+"_stairs",
                () -> new StairBlock(planks.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
    }

    public static RegistryObject<Block> registerFenceBlock(String name)
    {
        return registerBlock(name+"_fence",
                () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    }

    public static RegistryObject<Block> registerFenceGateBlock(String name)
    {
        return registerBlock(name+"_fence_gate",
                () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), ModBlockSetsHelper.WOOD_TYPES.get(name)));
    }

    public static RegistryObject<Block> registerDoorBlock(String name)
    {
        return registerBlock(name+"_door",
                () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK));
    }

    public static RegistryObject<Block> registerTrapdoorBlock(String name)
    {
        return registerBlock(name+"_trapdoor",
                () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK));
    }

    public static RegistryObject<Block> registerButtonBlock(String name)
    {
        return registerBlock(name+"_button",
                () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK, 30, true));
    }

    public static RegistryObject<Block> registerPressurePlateBlock(String name)
    {
        return registerBlock(name+"_pressure_plate",
                () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    }

    public static RegistryObject<Block> registerSaplingBlock(String name, AbstractTreeGrower saplingGenerator)
    {
        return registerBlock(name+"_sapling",
                () -> new SaplingBlock(saplingGenerator, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    }

    public static RegistryObject<Block> registerSignBlock(String name)
    {
        return registerBlock(name + "_sign",
                () -> new ModStandingSignBlock(ModBlockSetsHelper.WOOD_TYPES.get(name), BlockBehaviour.Properties.copy(Blocks.OAK_SIGN)));
    }

    public static RegistryObject<Block> registerWallSignBlock(String name)
    {
        return registerBlock(name+"_wall_sign",
                () -> new ModWallSignBlock(ModBlockSetsHelper.WOOD_TYPES.get(name), BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN)));
    }

    public static RegistryObject<Block> registerHangingSignBlock(String name)
    {
        return registerBlock(name+"_hanging_sign",
                () -> new ModHangingSignBlock(ModBlockSetsHelper.WOOD_TYPES.get(name), BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN)));
    }

    public static RegistryObject<Block> registerWallHangingSignBlock(String name)
    {
        return registerBlock(name+"_wall_hanging_sign",
                () -> new ModWallHangingSignBlock(ModBlockSetsHelper.WOOD_TYPES.get(name), BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN)));
    }
}