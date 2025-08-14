package me.a8kj.doubler;

import java.util.ArrayList;
import java.util.List;

import me.a8kj.doubler.module.Module;
import me.a8kj.doubler.module.impl.AutoClickerModule;
import me.a8kj.doubler.module.impl.HUDModule;
import me.a8kj.doubler.util.CPSCounter;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "${modid}", version = "${mod_version}", acceptedMinecraftVersions = "[1.8.9]")
public class DoublerMod {

    private final List<Module> modules = new ArrayList<Module>();
    private final CPSCounter cpsCounter = new CPSCounter();
    @EventHandler
    public void init(FMLInitializationEvent event) {
        modules.add(new AutoClickerModule(cpsCounter));
        modules.add(new HUDModule(cpsCounter));
        for (Module module : modules) {
            module.onEnable();
        }
    }
}
