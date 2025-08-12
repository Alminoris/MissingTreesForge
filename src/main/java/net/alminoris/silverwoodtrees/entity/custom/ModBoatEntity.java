package net.alminoris.silverwoodtrees.entity.custom;

import net.alminoris.silverwoodtrees.entity.ModEntities;
import net.alminoris.silverwoodtrees.item.ModItems;
import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;

public class ModBoatEntity extends Boat
{
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModBoatEntity.class, EntityDataSerializers.INT);

    public ModBoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ModBoatEntity(Level level, double pX, double pY, double pZ) {
        this(ModEntities.MOD_BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariant()) {
            case SILVER_MAPLE -> ModBlockSetsHelper.WOODEN_BOATS.get("silver_maple").get();
            case STAGHORN_SUMAC -> ModBlockSetsHelper.WOODEN_BOATS.get("staghorn_sumac").get();
            case SILVERBERRY -> ModBlockSetsHelper.WOODEN_BOATS.get("silverberry").get();
            default -> ModBlockSetsHelper.WOODEN_BOATS.get("walnut").get();
        };
    }

    public void setVariant(Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public Type getModVariant()
    {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.WALNUT.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound)
    {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(Type.byName(pCompound.getString("Type")));
        }
    }

    public static enum Type implements StringRepresentable
    {
        WALNUT(ModBlockSetsHelper.WOODEN_PLANKS.get("walnut").get(), "walnut"),
        SILVER_MAPLE(ModBlockSetsHelper.WOODEN_PLANKS.get("silver_maple").get(), "silver_maple"),
        STAGHORN_SUMAC(ModBlockSetsHelper.WOODEN_PLANKS.get("staghorn_sumac").get(), "staghorn_sumac"),
        SILVERBERRY(ModBlockSetsHelper.WOODEN_PLANKS.get("silverberry").get(), "silverberry");

        private final String name;
        private final Block planks;
        public static final StringRepresentable.EnumCodec<ModBoatEntity.Type> CODEC = StringRepresentable.fromEnum(ModBoatEntity.Type::values);
        private static final IntFunction<ModBoatEntity.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        private Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

        public String getSerializedName()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }

        public Block getPlanks()
        {
            return this.planks;
        }

        public String toString()
        {
            return this.name;
        }

        /**
         * Get a boat type by its enum ordinal
         */
        public static ModBoatEntity.Type byId(int pId)
        {
            return BY_ID.apply(pId);
        }

        public static ModBoatEntity.Type byName(String pName)
        {
            return CODEC.byName(pName, WALNUT);
        }
    }
}