package org.ageseries.securityage.blocks

import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Material
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.phys.shapes.Shapes

class LaserBlock : Block(Properties.of(Material.STONE)) {
    override fun getDescriptionId(): String {
        return "laser_block"
    }

    override fun getCollisionShape(
        state: BlockState,
        getter: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        // a laser beam is a cylinder-like shape
        return Shapes.box(0.15, 0.15, 0.0, 0.75, 0.75, 1.0)
    }
}