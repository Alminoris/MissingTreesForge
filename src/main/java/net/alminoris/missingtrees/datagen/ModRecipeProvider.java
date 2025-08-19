package net.alminoris.missingtrees.datagen;

import net.alminoris.missingtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries)
    {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput)
    {
        for (String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            stairBuilder(ModBlockSetsHelper.WOODEN_STAIRS.get(name).get(), Ingredient.of(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).group(name)
                    .unlockedBy(getHasName(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()), has(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).save(recipeOutput);
            slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlockSetsHelper.WOODEN_SLABS.get(name).get(), ModBlockSetsHelper.WOODEN_PLANKS.get(name).get());

            buttonBuilder(ModBlockSetsHelper.WOODEN_BUTTONS.get(name).get(), Ingredient.of(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).group(name)
                    .unlockedBy(getHasName(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()), has(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).save(recipeOutput);
            pressurePlate(recipeOutput, ModBlockSetsHelper.WOODEN_PRESSURE_PLATES.get(name).get(), ModBlockSetsHelper.WOODEN_PLANKS.get(name).get());

            fenceBuilder(ModBlockSetsHelper.WOODEN_FENCES.get(name).get(), Ingredient.of(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).group(name)
                    .unlockedBy(getHasName(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()), has(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).save(recipeOutput);
            fenceGateBuilder(ModBlockSetsHelper.WOODEN_FENCE_GATES.get(name).get(), Ingredient.of(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).group(name)
                    .unlockedBy(getHasName(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()), has(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).save(recipeOutput);

            doorBuilder(ModBlockSetsHelper.WOODEN_DOORS.get(name).get(), Ingredient.of(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).group(name)
                    .unlockedBy(getHasName(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()), has(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).save(recipeOutput);
            trapdoorBuilder(ModBlockSetsHelper.WOODEN_TRAPDOORS.get(name).get(), Ingredient.of(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).group(name)
                    .unlockedBy(getHasName(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()), has(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).save(recipeOutput);

            planksFromLog(recipeOutput, ModBlockSetsHelper.WOODEN_PLANKS.get(name).get(), ModBlockSetsHelper.LOGS.get(name).get());

            woodFromLogs(recipeOutput, ModBlockSetsHelper.WOODS.get(name).get(), ModBlockSetsHelper.LOGS.get(name).get());

            woodFromLogs(recipeOutput, ModBlockSetsHelper.STRIPPED_WOODS.get(name).get(), ModBlockSetsHelper.STRIPPED_LOGS.get(name).get());

            woodenBoat(recipeOutput, ModBlockSetsHelper.WOODEN_BOATS.get(name).get(), ModBlockSetsHelper.WOODEN_PLANKS.get(name).get());
            chestBoat(recipeOutput, ModBlockSetsHelper.WOODEN_CHEST_BOATS.get(name).get(), ModBlockSetsHelper.WOODEN_PLANKS.get(name).get());

            signBuilder(ModBlockSetsHelper.WOODEN_SIGN_ITEMS.get(name).get(), Ingredient.of(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).group(name)
                    .unlockedBy(getHasName(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()), has(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get())).save(recipeOutput);

            hangingSign(recipeOutput, ModBlockSetsHelper.WOODEN_HANGING_SIGN_ITEMS.get(name).get(),ModBlockSetsHelper.WOODEN_PLANKS.get(name).get());
        }
    }

    private static void planksFromLog(RecipeOutput p_298877_, ItemLike output, ItemLike input)
    {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .requires(input)
                .group("planks")
                .unlockedBy("has_log", has(input))
                .save(p_298877_);
    }
}