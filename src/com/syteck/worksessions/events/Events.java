package com.syteck.worksessions.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {

		Player player = event.getPlayer();

	}

	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {

		Player player = event.getPlayer();

	}

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {

		Player player = event.getPlayer();

		if(player.hasPermission("ws.exceed")) return;

	}

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {

		Player player = event.getPlayer();

		if(player.hasPermission("ws.exceed")) return;

	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {

		Player player = event.getPlayer();

	}

}
