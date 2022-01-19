package org.ageseries.securityage.data

import net.minecraft.ChatFormatting
import net.minecraft.world.level.material.MaterialColor
import net.minecraft.world.level.material.MaterialColor.GOLD
import net.minecraft.nbt.CompoundTag

enum class BittingDescriptor {

    WOOD(4, 4),
    IRON(6, 5),
    GOLD(7, 6),
    DIAMOND(8, 8);

    public val MAX_POSITIONS: Int = 8
    public val MAX_SETTINGS: Int = 8

    public val VALUES: Array<BittingDescriptor> = BittingDescriptor.values()

    public var positions: Int? = null
    public var settings: Int? = null

    constructor(positions: Int, settings: Int) {
        this.positions = positions
        this.settings = settings
    }



    open fun variant(): String? {
        when (this) {
            WOOD -> return "wood"
            IRON -> return "iron"
            GOLD -> return "gold"
            DIAMOND -> return "diamond"
        }
        return "wood"
    }

    open fun fromVariant(name: String?): BittingDescriptor? {
        when (name) {
            "wood" -> return WOOD
            "iron" -> return IRON
            "gold" -> return GOLD
            "diamond" -> return DIAMOND
        }
        return WOOD
    }

    open fun toNBT(): CompoundTag? {
        val tag = CompoundTag()
        tag.putString("variant", variant())
        return tag
    }

    open fun fromNBT(tag: CompoundTag): BittingDescriptor? {
        return fromVariant(tag.getString("variant"))
    }

    open fun formattedName(): String? {
        when (this) {
            WOOD -> return "Wood" + ChatFormatting.RESET
            IRON -> return "Iron" + ChatFormatting.RESET
            GOLD -> return "Gold" + ChatFormatting.RESET
            DIAMOND -> return "Diamond" + ChatFormatting.RESET
        }
        return "Unlisted: " + toString() + ChatFormatting.RESET
    }



}