package com.syteck.worksessions.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.syteck.worksessions.Session;
import com.syteck.worksessions.StorageManager;
import com.syteck.worksessions.utils.Util;

public class CommandTpall implements Command {

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(verify(sender, args)) {
			
			Player player = (Player) sender;
			Session session = StorageManager.getUser(player.getUniqueId()).getSession();
			
			for(UUID uuid: session.getPlayerList()) {
				
				Player target = Bukkit.getPlayer(uuid);
				
				target.teleport(player.getLocation());
				target.sendMessage(ChatColor.GREEN+"You have been teleported to your manager.");
			}
			
			player.sendMessage(ChatColor.GREEN+"Teleported all session members to you.");
		}
	}

	@Override
	public boolean verify(CommandSender sender, String[] args) {

		if(!Util.hasPermission(sender, "ws.assist")) {

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
