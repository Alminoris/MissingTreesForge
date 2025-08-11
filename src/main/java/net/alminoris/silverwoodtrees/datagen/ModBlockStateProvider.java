package net.alminoris.silverwoodtrees.datagen;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider
{
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper)
    {
        super(output, SilverwoodTrees.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        for (String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            blockWithItem(ModBlockSetsHelper.WOODEN_PLANKS.get(name));

            stairsBlock((StairBlock) ModBlockSetsHelper.WOODEN_STAIRS.get(name).get(), blockTexture(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()));
            slabBlock((SlabBlock) ModBlockSetsHelper.WOODEN_SLABS.get(name).get(), blockTexture(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()), blockTexture(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()));

            buttonBlock((ButtonBlock) ModBlockSetsHelper.WOODEN_BUTTONS.get(name).get(), blockTexture(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()));
            pressurePlateBlock((PressurePlateBlock) ModBlockSetsHelper.WOODEN_PRESSURE_PLATES.get(name).get(), blockTexture(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()));

            fenceBlock((FenceBlock) ModBlockSetsHelper.WOODEN_FENCES.get(name).get(), blockTexture(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()));
            fenceGateBlock((FenceGateBlock) ModBlockSetsHelper.WOODEN_FENCE_GATES.get(name).get(), blockTexture(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()));

            doorBlockWithRenderType((DoorBlock) ModBlockSetsHelper.WOODEN_DOORS.get(name).get(), modLoc("block/"+name+"_door_bottom"), modLoc("block/"+name+"_door_top"), "cutout");
            trapdoorBlockWithRenderType((TrapDoorBlock) ModBlockSetsHelper.WOODEN_TRAPDOORS.get(name).get(), modLoc("block/"+name+"_trapdoor"), true, "cutout");

            blockItem(ModBlockSetsHelper.WOODEN_STAIRS.get(name));
            blockItem(ModBlockSetsHelper.WOODEN_SLABS.get(name));
            blockItem(ModBlockSetsHelper.WOODEN_PRESSURE_PLATES.get(name));
            blockItem(ModBlockSetsHelper.WOODEN_FENCE_GATES.get(name));
            blockItem(ModBlockSetsHelper.WOODEN_TRAPDOORS.get(name), "_bottom");

            logBlock((RotatedPillarBlock) ModBlockSetsHelper.LOGS.get(name).get());
            axisBlock((RotatedPillarBlock) ModBlockSetsHelper.WOODS.get(name).get(), blockTexture(ModBlockSetsHelper.LOGS.get(name).get()), blockTexture(ModBlockSetsHelper.LOGS.get(name).get()));
            logBlock((RotatedPillarBlock) ModBlockSetsHelper.STRIPPED_LOGS.get(name).get());
            axisBlock((RotatedPillarBlock) ModBlockSetsHelper.STRIPPED_WOODS.get(name).get(), blockTexture(ModBlockSetsHelper.STRIPPED_LOGS.get(name).get()), blockTexture(ModBlockSetsHelper.STRIPPED_LOGS.get(name).get()));

            blockItem(ModBlockSetsHelper.LOGS.get(name));
            blockItem(ModBlockSetsHelper.WOODS.get(name));
            blockItem(ModBlockSetsHelper.STRIPPED_LOGS.get(name));
            blockItem(ModBlockSetsHelper.STRIPPED_WOODS.get(name));

            leavesBlock(ModBlockSetsHelper.LEAVES.get(name));
            saplingBlock(ModBlockSetsHelper.WOODEN_SAPLINGS.get(name));

            signBlock(((StandingSignBlock) ModBlockSetsHelper.WOODEN_SIGNS.get(name).get()), ((WallSignBlock) ModBlockSetsHelper.WOODEN_WALL_SIGNS.get(name).get()),
                    blockTexture(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()));

            hangingSignBlock(ModBlockSetsHelper.WOODEN_HANGING_SIGNS.get(name).get(), ModBlockSetsHelper.WOODEN_WALL_HANGING_SIGNS.get(name).get(),
                    blockTexture(ModBlockSetsHelper.WOODEN_PLANKS.get(name).get()));
        }
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture)
    {
        ModelFile sign = models().sign(signBlock.getDescriptionId(), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign)
    {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("silverwoodtrees:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("silverwoodtrees:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }
}