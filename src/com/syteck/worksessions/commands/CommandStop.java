package com.syteck.worksessions.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.syteck.worksessions.Session;
import com.syteck.worksessions.StorageManager;
import com.syteck.worksessions.User;
import com.syteck.worksessions.utils.Util;

public class CommandStop implements Command {

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(verify(sender, args)) {

			Player player = (Player) sender;
			int id = StorageManager.getUser(player.getUniqueId()).getSession().getId();

			if(args.length >= 2) {
				
				id = Integer.parseInt(args[1]);
				
			}
			
			if(StorageManager.getSessionList().containsKey(id)) {

				Session session = StorageManager.getSession(id);

				for(UUID uuid: session.getPlayerList()) {

					Player target = Bukkit.getPlayer(uuid);
					User user = StorageManager.getUser(uuid);
					
					user.setBuild(false);
					user.setSession(null);
					target.sendMessage(ChatColor.RED+"The work session you were in has closed.");
				}

				StorageManager.deleteSession(id);
				sender.sendMessage(ChatColor.RED+"You have deleted a work session.");

			} else {

				sender.sendMessage(ChatColor.RED+"This work session does not exist.");

			}
		}
	}

	@Override
	public boolean verify(CommandSender sender, String[] args) {

		if(!sender.hasPermission("ws.manage") && !sender.hasPermission("ws.admin")) {

			sender.sendMessage(ChatColor.RED+"You do not have permission to do this.");

			return false;
		}

		if(!StorageManager.getUser(((Player)sender).getUniqueId()).hasSession()) {

			sender.sendMessage(ChatColor.RED+"You are not in a session.");

			return false;
		}

		if(!Util.isInt(args[1]) && args.length >= 2) {

			sender.sendMessage(ChatColor.RED+"You entered an unvalid id.");

			return false;
		}

		return true;
	}

}
