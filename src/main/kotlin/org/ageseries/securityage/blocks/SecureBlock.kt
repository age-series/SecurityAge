package org.ageseries.securityage.blocks

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Material

class SecureBlock : Block(Properties.of(Material.STONE)) {
    @Override
    public fun hasTileEntity(state: BlockState): Boolean {
        return true
    }

    @Override
    public fun createTileEntity(state: BlockState, world: BlockView?): BlockEntity? {
        return SecureBlockTE()
    }
}