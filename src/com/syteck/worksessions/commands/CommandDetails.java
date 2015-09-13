package com.syteck.worksessions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.syteck.worksessions.Session;
import com.syteck.worksessions.StorageManager;
import com.syteck.worksessions.User;
import com.syteck.worksessions.utils.Util;

public class CommandDetails implements Command {

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(verify(sender, args)) {
			
			Player player = (Player) sender;
			User user = StorageManager.getUser(player.getUniqueId());
			Session session = user.getSession();
			
			player.sendMessage(ChatColor.GOLD+"Name: "+ChatColor.YELLOW+session.getName() + " ("+session.getId()+")");
			player.sendMessage(ChatColor.GOLD+"Trusted: "+ChatColor.YELLOW+""+session.isTrusted());
			player.sendMessage(ChatColor.GOLD+"Closed: "+ChatColor.YELLOW+""+session.isClosed());
			player.sendMessage(ChatColor.GOLD+"Manager: "+ChatColor.YELLOW+""+Bukkit.getOfflinePlayer(session.getManager().getUUID()).getName());
			player.sendMessage("       ");
			player.sendMessage(ChatColor.GOLD+"There are currently "+ChatColor.YELLOW+session.getPlayerList().size()+ChatColor.GOLD+" players in the session.");
			
			if(Util.hasPermission(sender, "ws.assist")) {
				
				
				
			}
		}
	}

	@Override
	public boolean verify(CommandSender sender, String[] args) {

		if(!StorageManager.getUser(((Player)sender).getUniqueId()).hasSession()) {

			sender.sendMessage(ChatColor.RED+"You are not currently in a session.");

			return false;
		}

		return true;
	}

}
