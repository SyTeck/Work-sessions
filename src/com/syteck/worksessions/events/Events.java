package com.syteck.worksessions.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.syteck.worksessions.Border;
import com.syteck.worksessions.StorageManager;
import com.syteck.worksessions.User;

public class Events implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		StorageManager.loadUser(player.getUniqueId());
	}

	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {

		Player player = event.getPlayer();

		StorageManager.saveUser(player.getUniqueId());
		StorageManager.unloadUser(player.getUniqueId());
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
		User user = StorageManager.getUser(player.getUniqueId());

		if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {

			if(user.getArea()) {

				Border border = user.getSession().getBorder();
				Location loc = event.getClickedBlock().getLocation();
				Location loc1 = border.getLocation(1), loc2 = border.getLocation(2);

				if(loc1 != null && loc2 != null) {

					double minX = Math.min(loc1.getX(), loc2.getX()), minY = Math.min(loc1.getY(), loc2.getY()), minZ = Math.min(loc1.getZ(), loc2.getZ()), 
							maxX = Math.max(loc1.getX(), loc2.getX()), maxY = Math.max(loc1.getY(), loc2.getY()), maxZ = Math.max(loc1.getZ(), loc2.getZ());

					border.setLocation(1, new Location(loc.getWorld(), minX, minY, minZ));
					border.setLocation(2, new Location(loc.getWorld(), maxX, maxY, maxZ));

				} else {

					border.setLocation(1, loc);

				}

				player.sendMessage(ChatColor.GOLD + "You have set the " + ChatColor.YELLOW + "first" + ChatColor.GOLD + " location.");

			}

		} else if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

			if(user.getArea()) {

				Border border = user.getSession().getBorder();
				Location loc = event.getClickedBlock().getLocation();
				Location loc1 = border.getLocation(1), loc2 = border.getLocation(2);

				if(loc1 != null && loc2 != null) {

					double minX = Math.min(loc1.getX(), loc2.getX()), minY = Math.min(loc1.getY(), loc2.getY()), minZ = Math.min(loc1.getZ(), loc2.getZ()), 
							maxX = Math.max(loc1.getX(), loc2.getX()), maxY = Math.max(loc1.getY(), loc2.getY()), maxZ = Math.max(loc1.getZ(), loc2.getZ());

					border.setLocation(1, new Location(loc.getWorld(), minX, minY, minZ));
					border.setLocation(2, new Location(loc.getWorld(), maxX, maxY, maxZ));

				} else {

					border.setLocation(2, loc);

				}

				player.sendMessage(ChatColor.GOLD + "You have set the " + ChatColor.YELLOW + "second" + ChatColor.GOLD + " location.");

			}
		}
	}
}
