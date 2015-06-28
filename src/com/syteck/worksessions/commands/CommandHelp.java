package com.syteck.worksessions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.syteck.worksessions.utils.Util;

public class CommandHelp implements Command {

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(verify(sender, args)) {

			sender.sendMessage(ChatColor.YELLOW+"Write " + ChatColor.GOLD + "/ws" + ChatColor.YELLOW + " before any of these commands.");
			sender.sendMessage(" ");
			sender.sendMessage(ChatColor.GOLD+"User commands:");
			sender.sendMessage(ChatColor.YELLOW+"join, leave, list, spawn, kit, help");
			
			if(Util.hasPermission(sender, "ws.assist")) {

				sender.sendMessage(" ");
				sender.sendMessage(ChatColor.GOLD+"Assistant commands:");
				sender.sendMessage(ChatColor.YELLOW+"trust, untrust, invite, uninvite, player, kick, tpall, details, ban, unban");

			}

			if(Util.hasPermission(sender, "ws.manage")) {

				sender.sendMessage(" ");
				sender.sendMessage(ChatColor.GOLD+"Manager commands:");
				sender.sendMessage(ChatColor.YELLOW+"start, stop, setspawn, setarea, setkit, manager, request, description, options");

			}

			if(Util.hasPermission(sender, "ws.admin")) {

				sender.sendMessage(" ");
				sender.sendMessage(ChatColor.GOLD+"Admin commands:");
				sender.sendMessage(ChatColor.YELLOW+"All commands and reload");

			}
		}
	}

	@Override
	public boolean verify(CommandSender sender, String[] args) {
		
		if(!Util.hasPermission(sender, "ws.user")) {
			
			sender.sendMessage(ChatColor.RED+"You do not have permission to do this.");
			
			return false;
		}

		return true;
	}

}
