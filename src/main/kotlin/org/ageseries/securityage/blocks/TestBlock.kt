package org.ageseries.securityage.blocks

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Material

// This doesn't have an item so you have to test it as follows:
// /setblock ~ ~-1 ~ securityage:test_block
class TestBlock : Block(Properties.of(Material.STONE)) {
    override fun getDescriptionId() = "block.securityage.test_block"
}
