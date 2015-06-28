package com.syteck.worksessions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.syteck.worksessions.Session;
import com.syteck.worksessions.StorageManager;
import com.syteck.worksessions.User;
import com.syteck.worksessions.events.UserJoinSessionEvent;
import com.syteck.worksessions.utils.Util;

public class CommandJoin implements Command {

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(verify(sender, args)) {

			int id = Integer.parseInt(args[1]);

			if(StorageManager.getSession(id) != null) {

				Player player = (Player) sender;
				Session session = StorageManager.getSession(id);
				User user = StorageManager.getUser(player.getUniqueId());

				boolean success = true;

				if(session.isDisabled()) {

					if(!Util.hasPermission(sender, "ws.manage")) {

						sender.sendMessage(ChatColor.RED+"This session is currently disabled.");

						success = false;
					}

				} else if(session.isClosed()) {

					if(!session.getInviteList().contains(player.getUniqueId()) && !Util.hasPermission(sender, "ws.assist")) {

						sender.sendMessage(ChatColor.RED+"This session is currently closed.");

						success = false;
					}

				} else if(session.isTrusted()) {

					if(!user.isTrusted() && !Util.hasPermission(sender, "ws.assist")) {

						sender.sendMessage(ChatColor.RED+"This session only accept trusted users.");

						success = false;
					}
				}

				if(success) {

					Bukkit.getPluginManager().callEvent(new UserJoinSessionEvent(user));

					user.setSession(session);
					session.getPlayerList().add(player.getUniqueId());

					player.sendMessage(ChatColor.GOLD+"You have joined a session and are being teleported.");
					player.teleport(session.getSpawn());

					if(Util.hasPermission(sender, "ws.assist") || StorageManager.getTrustList().contains(player.getUniqueId())) {

						user.setBuild(true);

					}

					if(session.getTeamspeak()) {

						player.sendMessage(ChatColor.GOLD+"This session requires you to use TeamSpeak!");

					}

				} else {

					session.broadcast(ChatColor.GOLD+"Player "+ChatColor.YELLOW+player.getName()+ChatColor.GOLD+" tried to joined the session.");

				}

			} else {

				sender.sendMessage(ChatColor.RED+"This work session does not exist.");

			}
		}
	}

	@Override
	public boolean verify(CommandSender sender, String[] args) {

		if(!Util.hasPermission(sender, "ws.user")) {

			sender.sendMessage(ChatColor.RED+"You do not have permission to do this.");

			return false;
		}

		if(args.length < 2) {

			sender.sendMessage(ChatColor.RED+"Usage: /ws join (id)");

			return false;
		}

		if(!Util.isInt(args[1])) {

			sender.sendMessage(ChatColor.RED+"You entered an unvalid id.");

			return false;
		}
		
		if(StorageManager.getUser(((Player)sender).getUniqueId()).isBanned()) {
			
			sender.sendMessage(ChatColor.RED+"You are banned and can not join a session.");

			return false;
		}

		if(StorageManager.getUser(((Player)sender).getUniqueId()).hasSession()) {

			sender.sendMessage(ChatColor.RED+"You are already in a session.");

			return false;
		}

		return true;
	}

}
