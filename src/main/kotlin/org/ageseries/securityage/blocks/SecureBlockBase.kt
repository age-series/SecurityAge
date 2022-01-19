package org.ageseries.securityage.blocks

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Material
import net.minecraft.world.level.Level

// This doesn't have an item so you have to test it as follows:
// /setblock ~ ~-1 ~ securityage:test_block
class SecureBlockBase {

    public fun tryUnlockWith(level: Level, pos: BlockPos, player: Player): Boolean {
        
        return false
    }



}