package net.alminoris.missingtrees.entity.custom;

import net.alminoris.missingtrees.entity.ModEntities;
import net.alminoris.missingtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

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
            case APPLE -> ModBlockSetsHelper.WOODEN_BOATS.get("apple").get();
            case SCOTS_PINE -> ModBlockSetsHelper.WOODEN_BOATS.get("scots_pine").get();
            case SWAMP_OAK -> ModBlockSetsHelper.WOODEN_BOATS.get("swamp_oak").get();
            default -> ModBlockSetsHelper.WOODEN_BOATS.get("azalea").get();
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
    protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_TYPE, Type.AZALEA.ordinal());
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
        AZALEA(ModBlockSetsHelper.WOODEN_PLANKS.get("azalea").get(), "azalea"),
        APPLE(ModBlockSetsHelper.WOODEN_PLANKS.get("apple").get(), "apple"),
        SCOTS_PINE(ModBlockSetsHelper.WOODEN_PLANKS.get("scots_pine").get(), "scots_pine"),
        SWAMP_OAK(ModBlockSetsHelper.WOODEN_PLANKS.get("swamp_oak").get(), "swamp_oak");

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
            return CODEC.byName(pName, AZALEA);
        }
    }
}