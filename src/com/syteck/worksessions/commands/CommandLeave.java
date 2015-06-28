package com.syteck.worksessions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.syteck.worksessions.Session;
import com.syteck.worksessions.StorageManager;
import com.syteck.worksessions.User;
import com.syteck.worksessions.events.UserLeaveSessionEvent;
import com.syteck.worksessions.utils.Util;

public class CommandLeave implements Command {

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(verify(sender, args)) {
			
			Player player = (Player) sender;
			User user = StorageManager.getUser(player.getUniqueId());
			Session session = user.getSession();

			if(session.getManager().equals(user)) {
				
				session.setDisabled(true);
				
				for(Player p: Bukkit.getOnlinePlayers()) {
					
					if(Util.hasPermission(p, "ws.manage")) {
						
						p.sendMessage(ChatColor.GOLD+"Session "+ChatColor.YELLOW+session.getId()+ChatColor.GOLD+" is currently in need of a new manager.");
						
					}
				}
			}
			
			Bukkit.getPluginManager().callEvent(new UserLeaveSessionEvent(user));
			//player.sendMessage(ChatColor.GOLD+"You left the session.");
			
			session.getPlayerList().remove(player.getUniqueId());
			user.setBuild(false);
			user.setSession(null);
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

		return false;
	}

}
