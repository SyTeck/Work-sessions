package com.syteck.worksessions.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.syteck.worksessions.Session;
import com.syteck.worksessions.StorageManager;
import com.syteck.worksessions.utils.Util;

public class CommandSpawn implements Command {

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(verify(sender, args)) {
			
			Session session = StorageManager.getUser(((Player)sender).getUniqueId()).getSession();
			Location spawn = session.getSpawn();
			
			Player player = (Player) sender;
			
			player.sendMessage(ChatColor.GOLD+"You are now teleporting...");
			player.teleport(spawn);
		}
	}

	@Override
	public boolean verify(CommandSender sender, String[] args) {

		if(!Util.hasPermission(sender, "ws.user")) {

			sender.sendMessage(ChatColor.RED+"You do not have permission to do this.");

			return false;
		}

		if(!StorageManager.getUser(((Player)sender).getUniqueId()).hasSession()) {
			
			sender.sendMessage(ChatColor.RED+"You are not currently in a session.");

			return false;
		}

		return true;
	}

}
