package net.alminoris.silverwoodtrees.datagen.loot;

import net.alminoris.silverwoodtrees.block.ModBlocks;
import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider
{
    public ModBlockLootTables()
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate()
    {
        for (String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            dropSelf(ModBlockSetsHelper.LOGS.get(name).get());
            dropSelf(ModBlockSetsHelper.STRIPPED_LOGS.get(name).get());
            dropSelf(ModBlockSetsHelper.WOODS.get(name).get());
            dropSelf(ModBlockSetsHelper.STRIPPED_WOODS.get(name).get());
            dropSelf(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get());
            add(ModBlockSetsHelper.WOODEN_SLABS.get(name).get(), block -> createSlabItemTable(ModBlockSetsHelper.WOODEN_SLABS.get(name).get()));
            dropSelf(ModBlockSetsHelper.WOODEN_STAIRS.get(name).get());
            dropSelf(ModBlockSetsHelper.WOODEN_FENCES.get(name).get());
            dropSelf(ModBlockSetsHelper.WOODEN_FENCE_GATES.get(name).get());
            dropSelf(ModBlockSetsHelper.WOODEN_TRAPDOORS.get(name).get());
            dropSelf(ModBlockSetsHelper.WOODEN_BUTTONS.get(name).get());
            dropSelf(ModBlockSetsHelper.WOODEN_PRESSURE_PLATES.get(name).get());
            add(ModBlockSetsHelper.WOODEN_DOORS.get(name).get(),
                    block -> createDoorTable(ModBlockSetsHelper.WOODEN_DOORS.get(name).get()));
            dropSelf(ModBlockSetsHelper.WOODEN_SAPLINGS.get(name).get());
            add(ModBlockSetsHelper.LEAVES.get(name).get(), block ->
                    createLeavesDrops(block, ModBlockSetsHelper.WOODEN_SAPLINGS.get(name).get(), NORMAL_LEAVES_SAPLING_CHANCES));

            add(ModBlockSetsHelper.WOODEN_SIGNS.get(name).get(), block ->
                    createSingleItemTable(ModBlockSetsHelper.WOODEN_SIGN_ITEMS.get(name).get()));
            add(ModBlockSetsHelper.WOODEN_WALL_SIGNS.get(name).get(), block ->
                    createSingleItemTable(ModBlockSetsHelper.WOODEN_SIGN_ITEMS.get(name).get()));
            add(ModBlockSetsHelper.WOODEN_HANGING_SIGNS.get(name).get(), block ->
                    createSingleItemTable(ModBlockSetsHelper.WOODEN_HANGING_SIGN_ITEMS.get(name).get()));
            add(ModBlockSetsHelper.WOODEN_WALL_HANGING_SIGNS.get(name).get(), block ->
                    createSingleItemTable(ModBlockSetsHelper.WOODEN_HANGING_SIGN_ITEMS.get(name).get()));
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
