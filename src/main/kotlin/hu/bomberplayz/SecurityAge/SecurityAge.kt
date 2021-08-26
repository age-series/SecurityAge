package hu.bomberplayz.SecurityAge

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import hu.bomberplayz.SecurityAge.SecurityAge
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent
import net.minecraftforge.fml.InterModComms
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent
import net.minecraftforge.fml.InterModComms.IMCMessage
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import java.util.stream.Collectors

// The value here should match an entry in the META-INF/mods.toml file
@Mod("securityage")
class SecurityAge {
    private fun setup(event: FMLCommonSetupEvent) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT")
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.registryName)
    }

    private fun enqueueIMC(event: InterModEnqueueEvent) {
        // some example code to dispatch IMC to another mod

    }

    private fun processIMC(event: InterModProcessEvent) {
        // some example code to receive and process InterModComms from other mods

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    fun onServerStarting(event: FMLServerStartingEvent?) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting")
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
    object RegistryEvents {
        @SubscribeEvent
        fun onBlocksRegistry(blockRegistryEvent: RegistryEvent.Register<Block?>?) {
            // register a new block here
            LOGGER.info("HELLO from Register Block")
        }
    }

    companion object {
        // Directly reference a log4j logger.
        private val LOGGER = LogManager.getLogger()
    }

    init {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().modEventBus.addListener { event: FMLCommonSetupEvent -> setup(event) }
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().modEventBus.addListener { event: InterModEnqueueEvent -> enqueueIMC(event) }
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().modEventBus.addListener { event: InterModProcessEvent -> processIMC(event) }

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this)
    }
}