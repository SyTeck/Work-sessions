package com.syteck.worksessions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.syteck.worksessions.commands.Commands;
import com.syteck.worksessions.events.Events;

public class Main extends JavaPlugin {

	private static Main instance;
	public static Main getInstance() {
		
		return instance;
		
	}
	
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		getCommand("ws").setExecutor(new Commands());
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {


		
		super.onDisable();
	}
}
