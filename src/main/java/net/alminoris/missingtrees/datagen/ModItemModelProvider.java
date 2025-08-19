package net.alminoris.missingtrees.datagen;

import net.alminoris.missingtrees.MissingTrees;
import net.alminoris.missingtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider
{

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, MissingTrees.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        for (String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            buttonItem(ModBlockSetsHelper.WOODEN_BUTTONS.get(name), ModBlockSetsHelper.WOODEN_PLANKS.get(name));
            fenceItem(ModBlockSetsHelper.WOODEN_FENCES.get(name), ModBlockSetsHelper.WOODEN_PLANKS.get(name));

            simpleBlockItem(ModBlockSetsHelper.WOODEN_DOORS.get(name));

            if (!name.equals("azalea"))
                saplingItem(ModBlockSetsHelper.WOODEN_SAPLINGS.get(name));

            simpleItem(ModBlockSetsHelper.WOODEN_SIGN_ITEMS.get(name));
            simpleItem(ModBlockSetsHelper.WOODEN_HANGING_SIGN_ITEMS.get(name));

            simpleItem(ModBlockSetsHelper.WOODEN_BOATS.get(name));
            simpleItem(ModBlockSetsHelper.WOODEN_CHEST_BOATS.get(name));
        }
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item)
    {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(MissingTrees.MOD_ID,"block/" + item.getId().getPath()));
    }

    public void buttonItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock)
    {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MissingTrees.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void fenceItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock)
    {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MissingTrees.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(MissingTrees.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<? extends Block> item)
    {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(MissingTrees.MOD_ID,"item/" + item.getId().getPath()));
    }
}