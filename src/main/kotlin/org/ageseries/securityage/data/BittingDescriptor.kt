package org.ageseries.securityage.data

import net.minecraft.ChatFormatting
import net.minecraft.world.level.material.MaterialColor
import net.minecraft.world.level.material.MaterialColor.GOLD
import net.minecraft.nbt.CompoundTag

enum class BittingDescriptor(positions: Int, settings: Int) {

    WOOD(4, 4),
    IRON(6, 5),
    GOLD(7, 6),
    DIAMOND(8, 8);


    val MAX_POSITIONS: Int = 8
    val MAX_SETTINGS: Int = 8

    val VALUES: Array<BittingDescriptor> = BittingDescriptor.values()

    var positions: Int = -1
    var settings: Int = -1

    fun variant(): String{
        for (i in 0..BittingDescriptor.values().size - 1) {
            val vale = BittingDescriptor.values()[i]
            when (vale) {
                WOOD -> return "wood"
                IRON -> return "iron"
                GOLD -> return "gold"
                DIAMOND -> return "diamond"
            }
        }
        return "wood"
    }

    fun toNBT(): CompoundTag {
        val tag = CompoundTag()
        tag.putString("variant", variant())
        return tag
    }

    fun formattedName(): String {
        for (i in 0..BittingDescriptor.values().size - 1) {
            val vale = BittingDescriptor.values()[i]
            when (vale) {
                WOOD -> return "wood"
                IRON -> return "iron"
                GOLD -> return "gold"
                DIAMOND -> return "diamond"
            }
        }
        return "unlisted: " + variant()
    }


    companion object {








        fun fromVariant(name: String?): BittingDescriptor {
            when (name) {
                "wood" -> return WOOD
                "iron" -> return IRON
                "gold" -> return GOLD
                "diamond" -> return DIAMOND
            }
            return WOOD
        }



        fun fromNBT(tag: CompoundTag): BittingDescriptor {
            return fromVariant(tag.getString("variant"))
        }


    }



}