package net.alminoris.missingtrees.datagen;

import net.alminoris.missingtrees.MissingTrees;
import net.alminoris.missingtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider
{
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(packOutput, completableFuture, lookupCompletableFuture, MissingTrees.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        for (String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            tag(ItemTags.LOGS_THAT_BURN)
                    .add(ModBlockSetsHelper.LOGS.get(name).get().asItem())
                    .add(ModBlockSetsHelper.WOODS.get(name).get().asItem())
                    .add(ModBlockSetsHelper.STRIPPED_LOGS.get(name).get().asItem())
                    .add(ModBlockSetsHelper.STRIPPED_WOODS.get(name).get().asItem());

            tag(ItemTags.PLANKS)
                    .add(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get().asItem());

            tag(ItemTags.HANGING_SIGNS)
                    .add(ModBlockSetsHelper.WOODEN_HANGING_SIGN_ITEMS.get(name).get().asItem());

            tag(ItemTags.SIGNS)
                    .add(ModBlockSetsHelper.WOODEN_SIGN_ITEMS.get(name).get().asItem());
        }
    }
}