package net.alminoris.silverwoodtrees.item;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.entity.custom.ModBoatEntity;
import net.alminoris.silverwoodtrees.item.custom.ModBoatItem;
import net.alminoris.silverwoodtrees.world.tree.ModTreeGrowers;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper.*;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SilverwoodTrees.MOD_ID);

    static
    {
        for (String name : WOOD_NAMES)
        {
            WOODEN_SIGN_ITEMS.put(name, registerSignItem(name, WOODEN_SIGNS.get(name), WOODEN_WALL_SIGNS.get(name)));
            WOODEN_HANGING_SIGN_ITEMS.put(name, registerHangingSignItem(name, WOODEN_HANGING_SIGNS.get(name), WOODEN_WALL_HANGING_SIGNS.get(name)));

            WOODEN_BOATS.put(name, registerBoatItem(name));
            WOODEN_CHEST_BOATS.put(name, registerChestBoatItem(name));
        }
    }

    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item)
    {
        return ITEMS.register(name, item);
    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }

    public static RegistryObject<Item> registerBoatItem(String name)
    {
        return registerItem(name+"_boat",
                () -> new ModBoatItem(false, ModBoatEntity.Type.byName(name), new Item.Properties()));
    }

    public static RegistryObject<Item> registerChestBoatItem(String name)
    {
        return registerItem(name+"_chest_boat",
                () -> new ModBoatItem(true, ModBoatEntity.Type.byName(name), new Item.Properties()));
    }

    public static RegistryObject<Item> registerSignItem(String name, Supplier<Block> signBlock, Supplier<Block> wallSignBlock)
    {
        return registerItem(name+"_sign_item", ()-> new SignItem(new Item.Properties().stacksTo(16), signBlock.get(), wallSignBlock.get()));
    }

    public static RegistryObject<Item> registerHangingSignItem(String name, Supplier<Block> hangingSignBlock, Supplier<Block> wallHangingSignBlock)
    {
        return registerItem(name+"_hanging_sign_item", ()-> new HangingSignItem(hangingSignBlock.get(), wallHangingSignBlock.get(), new Item.Properties().stacksTo(16)));
    }
}