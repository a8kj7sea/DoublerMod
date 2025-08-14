package me.a8kj.doubler.module;

import net.minecraftforge.common.MinecraftForge;

public abstract class ModuleBase implements Module {

	protected boolean isEnabled = false;

	public void onEnable() {
		MinecraftForge.EVENT_BUS.register(this);
		this.isEnabled = true;
	}

	public void onDisable() {
		MinecraftForge.EVENT_BUS.unregister(this);
		this.isEnabled = false;
	}

	public boolean isEnabled() {
		return this.isEnabled;
	}
}