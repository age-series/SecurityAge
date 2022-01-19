package org.ageseries.securityage.items

import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Material

// This doesn't have an item so you have to test it as follows:
// /setblock ~ ~-1 ~ securityage:test_block
class TestItem : Item(Properties()) {
    override fun getDescriptionId() = "block.securityage.test_item"



}
