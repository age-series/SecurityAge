package org.ageseries.securityage

import org.ageseries.securityage.blocks.TestBlock
import net.minecraft.world.level.block.Block
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.addGenericListener

@Mod(SecurityAge.MODID)
object SecurityAge {
    const val MODID = "securityage"
    val LOGGER = LogManager.getLogger()

    init {
        MOD_BUS.addGenericListener({ event: RegistryEvent.Register<Block> -> registerBlocks(event) })
        MOD_BUS.register(this)
    }

    private fun registerBlocks(event: RegistryEvent.Register<Block>) {
        LOGGER.info("Registering blocks for Security Age")
        val testBlock = TestBlock()
        testBlock.setRegistryName(MODID, "test_block")
        event.registry.register(testBlock)

        event.registry.keys.forEach {
            LOGGER.info("${it.namespace}, ${it.path}")
        }
    }
}
