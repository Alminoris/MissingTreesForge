package net.alminoris.missingtrees.datagen;

import net.alminoris.missingtrees.MissingTrees;
import net.alminoris.missingtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider
{
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, MissingTrees.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        for (String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            tag(BlockTags.FENCES).add(ModBlockSetsHelper.WOODEN_FENCES.get(name).get());
            tag(BlockTags.FENCE_GATES).add(ModBlockSetsHelper.WOODEN_FENCE_GATES.get(name).get());

            tag(BlockTags.LOGS_THAT_BURN)
                    .add(ModBlockSetsHelper.LOGS.get(name).get())
                    .add(ModBlockSetsHelper.WOODS.get(name).get())
                    .add(ModBlockSetsHelper.STRIPPED_LOGS.get(name).get())
                    .add(ModBlockSetsHelper.STRIPPED_WOODS.get(name).get());

            tag(BlockTags.PLANKS)
                    .add(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get());

            if (!name.equals("azalea"))
            {
                tag(BlockTags.SAPLINGS)
                        .add(ModBlockSetsHelper.WOODEN_SAPLINGS.get(name).get());
            }

            tag(BlockTags.WOODEN_SLABS)
                    .add(ModBlockSetsHelper.WOODEN_SLABS.get(name).get());

            tag(BlockTags.WOODEN_STAIRS)
                    .add(ModBlockSetsHelper.WOODEN_STAIRS.get(name).get());

            tag(BlockTags.WOODEN_FENCES)
                    .add(ModBlockSetsHelper.WOODEN_FENCES.get(name).get());

            tag(BlockTags.WOODEN_BUTTONS)
                    .add(ModBlockSetsHelper.WOODEN_BUTTONS.get(name).get());

            tag(BlockTags.WOODEN_PRESSURE_PLATES)
                    .add(ModBlockSetsHelper.WOODEN_PRESSURE_PLATES.get(name).get());

            tag(BlockTags.WOODEN_TRAPDOORS)
                    .add(ModBlockSetsHelper.WOODEN_TRAPDOORS.get(name).get());

            tag(BlockTags.WOODEN_DOORS)
                    .add(ModBlockSetsHelper.WOODEN_DOORS.get(name).get());

            tag(BlockTags.STANDING_SIGNS)
                    .add(ModBlockSetsHelper.WOODEN_SIGNS.get(name).get());

            tag(BlockTags.WALL_SIGNS)
                    .add(ModBlockSetsHelper.WOODEN_WALL_SIGNS.get(name).get());

            tag(BlockTags.CEILING_HANGING_SIGNS)
                    .add(ModBlockSetsHelper.WOODEN_HANGING_SIGNS.get(name).get());

            tag(BlockTags.WALL_HANGING_SIGNS)
                    .add(ModBlockSetsHelper.WOODEN_WALL_HANGING_SIGNS.get(name).get());
        }
    }
}