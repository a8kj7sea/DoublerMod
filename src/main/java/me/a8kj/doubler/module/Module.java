package me.a8kj.doubler.module;

public interface Module {

	void onEnable();

	void onDisable();

	String getName();

	String getDescription();
}
