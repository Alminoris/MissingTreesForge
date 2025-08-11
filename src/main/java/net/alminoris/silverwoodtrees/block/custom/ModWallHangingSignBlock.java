package net.alminoris.silverwoodtrees.block.custom;

import net.alminoris.silverwoodtrees.block.entity.ModHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWallHangingSignBlock extends WallHangingSignBlock
{
    public ModWallHangingSignBlock(WoodType pType, Properties pProperties)
    {
        super(pType, pProperties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return new ModHangingSignBlockEntity(pPos, pState);
    }
}