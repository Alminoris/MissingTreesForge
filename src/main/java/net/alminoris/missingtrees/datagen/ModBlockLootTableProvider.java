package net.alminoris.missingtrees.datagen;

import net.alminoris.missingtrees.block.ModBlocks;
import net.alminoris.missingtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider
{
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate()
    {
        add(ModBlocks.APPLE_FRUIT_LEAVES.get(), block ->
                leavesOnlyItemDrop(block, Items.APPLE, 0.25F));

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

            if (!name.equals("azalea"))
            {
                dropSelf(ModBlockSetsHelper.WOODEN_SAPLINGS.get(name).get());
                add(ModBlockSetsHelper.LEAVES.get(name).get(), block ->
                        createLeavesDrops(block, ModBlockSetsHelper.WOODEN_SAPLINGS.get(name).get(), NORMAL_LEAVES_SAPLING_CHANCES));
            }

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

    protected LootTable.Builder leavesOnlyItemDrop(Block leaves, Item item, float chance)
    {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(this.doesNotHaveShearsOrSilkTouch())
                                .add(
                                        ((LootPoolSingletonContainer.Builder)this.applyExplosionDecay(
                                                leaves,
                                                LootItem.lootTableItem(item)
                                        )).when(
                                                BonusLevelTableCondition.bonusLevelFlatChance(
                                                        registrylookup.getOrThrow(Enchantments.FORTUNE),
                                                        chance * 2.5F,
                                                        chance * 3.3333336F,
                                                        chance * 10F,
                                                        chance * 20F,
                                                        chance * 24F
                                                )
                                        )
                                )
                );
    }

    private LootItemCondition.Builder hasShearsOrSilkTouch() {
        return HAS_SHEARS.or(this.hasSilkTouch());
    }

    private LootItemCondition.Builder doesNotHaveShearsOrSilkTouch() {
        return this.hasShearsOrSilkTouch().invert();
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}