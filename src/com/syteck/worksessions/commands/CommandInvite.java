package com.syteck.worksessions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.syteck.worksessions.Session;
import com.syteck.worksessions.StorageManager;
import com.syteck.worksessions.utils.Util;

public class CommandInvite implements Command {

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {

		if(verify(sender, args)) {
			
			Session session = StorageManager.getUser(((Player)sender).getUniqueId()).getSession();
			OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
			
			session.getInviteList().add(player.getUniqueId());
			sender.sendMessage(ChatColor.GREEN+"Invited "+player.getName()+" to your session.");
			
			if(player.isOnline()) {
				
				player.getPlayer().sendMessage(ChatColor.RED+"You were invited to session "+session.getName()+" ("+session.getId()+")");
				
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

			sender.sendMessage(ChatColor.RED+"Usage: /ws invite (name)");

			return false;
		}

		if(!StorageManager.getUser(((Player)sender).getUniqueId()).hasSession()) {

			sender.sendMessage(ChatColor.RED+"You are not currently in a session.");

			return false;
		}
		
		if(StorageManager.getUser(Bukkit.getOfflinePlayer(args[1]).getUniqueId()) == null) {

			sender.sendMessage(ChatColor.RED+"This player does not exist.");

			return false;
		}
		
		if(StorageManager.getUser(((Player)sender).getUniqueId()).getSession().getInviteList().contains(Bukkit.getOfflinePlayer(args[1]).getUniqueId())) {
			
			sender.sendMessage(ChatColor.GREEN+"This player is already invited to your session.");
			
			return false;
		}
		
		return true;
	}

}
