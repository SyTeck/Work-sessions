package com.syteck.worksessions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import com.syteck.worksessions.StorageManager;
import com.syteck.worksessions.User;
import com.syteck.worksessions.events.UserLeaveSessionEvent;
import com.syteck.worksessions.utils.Util;

public class CommandKick implements Command {

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(verify(sender, args)) {
			
			OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
			User user = StorageManager.getUser(player.getUniqueId());
			
			Bukkit.getPluginManager().callEvent(new UserLeaveSessionEvent(user));
			
			user.setSession(null);
			user.setBuild(false);
			
			if(player.isOnline()) {
				
				player.getPlayer().sendMessage(ChatColor.RED+"You were kicked from your session.");
				
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean verify(CommandSender sender, String[] args) {
		
		if(!Util.hasPermission(sender, "ws.assist")) {

			sender.sendMessage(ChatColor.RED+"You do not have permission to do this.");

			return false;
		}

		if(args.length < 2) {

			sender.sendMessage(ChatColor.RED+"Usage: /ws kick (name)");

			return false;
		}
		
		if(StorageManager.getUser(Bukkit.getPlayerExact(args[1]).getUniqueId()) == null) {

			sender.sendMessage(ChatColor.RED+"This player does not exist.");

			return false;
		}
		
		return true;
	}

}
