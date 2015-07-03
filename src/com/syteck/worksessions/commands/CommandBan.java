package com.syteck.worksessions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import com.syteck.worksessions.StorageManager;
import com.syteck.worksessions.User;
import com.syteck.worksessions.events.UserLeaveSessionEvent;
import com.syteck.worksessions.utils.Util;

public class CommandBan implements Command {

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {

		if(verify(sender, args)) {
			
			OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
			User user = StorageManager.getUser(player.getUniqueId());
			
			user.getSession().getPlayerList().remove(player.getUniqueId());
			Bukkit.getPluginManager().callEvent(new UserLeaveSessionEvent(user));
			
			user.setSession(null);
			user.setBuild(false);
			user.setBanned(true);
			StorageManager.getBanList().add(player.getUniqueId());
			StorageManager.saveUser(player.getUniqueId());
			
			sender.sendMessage(ChatColor.GREEN+"You have successfully banned " + player.getName() + ".");
			
			if(player.isOnline()) {
				
				player.getPlayer().sendMessage(ChatColor.RED+"You have been banned from joining work sessions.");
				
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

			sender.sendMessage(ChatColor.RED+"Usage: /ws ban (name)");

			return false;
		}

		if(StorageManager.getUser(Bukkit.getOfflinePlayer(args[1]).getUniqueId()) == null) {

			sender.sendMessage(ChatColor.RED+"This player does not exist.");

			return false;
		}

		if(StorageManager.getUser(Bukkit.getOfflinePlayer(args[1]).getUniqueId()).isBanned()) {

			sender.sendMessage(ChatColor.RED+"This player is already banned.");

			return false;
		}

		return true;
	}

}
