package com.syteck.worksessions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.syteck.worksessions.commands.Commands;
import com.syteck.worksessions.events.Events;

public class Main extends JavaPlugin {

	
	
	@Override
	public void onEnable() {
		
		getCommand("ws").setExecutor(new Commands());
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {


		
		super.onDisable();
	}
}
