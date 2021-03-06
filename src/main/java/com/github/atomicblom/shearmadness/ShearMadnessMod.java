package com.github.atomicblom.shearmadness;

import com.github.atomicblom.shearmadness.api.BehaviourRegistry;
import com.github.atomicblom.shearmadness.api.events.RegisterShearMadnessBehaviourEvent;
import com.github.atomicblom.shearmadness.capability.ChiseledSheepCapability;
import com.github.atomicblom.shearmadness.capability.ChiseledSheepCapabilityStorage;
import com.github.atomicblom.shearmadness.api.capability.IChiseledSheepCapability;
import com.github.atomicblom.shearmadness.configuration.ConfigurationHandler;
import com.github.atomicblom.shearmadness.networking.CheckSheepChiseledRequestMessage;
import com.github.atomicblom.shearmadness.networking.CheckSheepChiseledRequestMessageHandler;
import com.github.atomicblom.shearmadness.networking.SheepChiseledMessage;
import com.github.atomicblom.shearmadness.networking.SheepChiseledMessageHandler;
import com.github.atomicblom.shearmadness.proxy.Proxies;
import com.github.atomicblom.shearmadness.utility.Reference;
import com.github.atomicblom.shearmadness.variations.CommonReference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@SuppressWarnings("MethodMayBeStatic")
@Mod(modid = CommonReference.MOD_ID, version = CommonReference.VERSION, guiFactory = Reference.MOD_GUI_FACTORY, dependencies = "required-after:chisel@[MC1.10.2-0.0.7.7,)", acceptedMinecraftVersions = "[1.9.4, 1.11)")
public class ShearMadnessMod
{
    public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(CommonReference.MOD_ID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        //Networking
        CHANNEL.registerMessage(CheckSheepChiseledRequestMessageHandler.class, CheckSheepChiseledRequestMessage.class, 0, Side.SERVER);
        CHANNEL.registerMessage(SheepChiseledMessageHandler.class, SheepChiseledMessage.class, 1, Side.CLIENT);

        //Capabilities
        CapabilityManager.INSTANCE.register(IChiseledSheepCapability.class, ChiseledSheepCapabilityStorage.instance, ChiseledSheepCapability::new);

        //Eventing
        MinecraftForge.EVENT_BUS.register(Proxies.forgeEventProxy);
        MinecraftForge.EVENT_BUS.register(Proxies.renderProxy);

        Proxies.blockProxy.registerBlocks();
        Proxies.audioProxy.registerSounds();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Proxies.renderProxy.registerRenderers();
        Proxies.forgeEventProxy.fireRegistryEvent();
    }
}
