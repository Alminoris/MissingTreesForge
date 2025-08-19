package net.alminoris.missingtrees.util.helper;

import net.alminoris.missingtrees.MissingTrees;
import net.alminoris.missingtrees.world.tree.ModTreeGrowers;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

import static net.alminoris.missingtrees.block.ModBlocks.*;

public class ModBlockSetsHelper
{
    public static final String[] WOOD_NAMES = new String[] { "azalea", "apple", "scots_pine", "swamp_oak" };
    
    public static final Map<String, WoodType> WOOD_TYPES = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> LEAVES = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> LOGS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> STRIPPED_LOGS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> STRIPPED_WOODS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_PLANKS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_SLABS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_STAIRS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_FENCES = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_FENCE_GATES = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_DOORS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_TRAPDOORS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_BUTTONS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_PRESSURE_PLATES = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_SIGNS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_WALL_SIGNS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_HANGING_SIGNS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_WALL_HANGING_SIGNS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> WOODEN_SIGN_ITEMS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> WOODEN_HANGING_SIGN_ITEMS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> WOODEN_SAPLINGS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> WOODEN_BOATS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> WOODEN_CHEST_BOATS = new HashMap<>();

    static
    {
        for (String name : WOOD_NAMES)
        {
            WOOD_TYPES.put(name, WoodType.register(new WoodType(MissingTrees.MOD_ID + ":" + name, BlockSetType.OAK)));
            if (!name.equals("azalea")) LEAVES.put(name, registerLeavesBlock(name));
            LOGS.put(name, registerLogsBlock(name));
            WOODS.put(name, registerWoodsBlock(name));
            STRIPPED_LOGS.put(name, registerStrippedLogsBlock(name));
            STRIPPED_WOODS.put(name, registerStrippedWoodsBlock(name));
            WOODEN_PLANKS.put(name, registerPlanksBlock(name));
            WOODEN_SLABS.put(name, registerSlabBlock(name));
            WOODEN_STAIRS.put(name, registerStairsBlock(name, WOODEN_PLANKS.get(name)));
            WOODEN_FENCES.put(name, registerFenceBlock(name));
            WOODEN_FENCE_GATES.put(name, registerFenceGateBlock(name));
            WOODEN_DOORS.put(name, registerDoorBlock(name));
            WOODEN_TRAPDOORS.put(name, registerTrapdoorBlock(name));
            WOODEN_BUTTONS.put(name, registerButtonBlock(name));
            WOODEN_PRESSURE_PLATES.put(name, registerPressurePlateBlock(name));

            WOODEN_SIGNS.put(name, registerSignBlock(name));
            WOODEN_WALL_SIGNS.put(name, registerWallSignBlock(name));
            WOODEN_HANGING_SIGNS.put(name, registerHangingSignBlock(name));
            WOODEN_WALL_HANGING_SIGNS.put(name, registerWallHangingSignBlock(name));

            if (!name.equals("azalea")) WOODEN_SAPLINGS.put(name, registerSaplingBlock(name, ModTreeGrowers.saplingGenerators.get(name)));
        }
    }
}