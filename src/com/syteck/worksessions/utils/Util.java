package com.syteck.worksessions.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Util {

	public static boolean isInt(String str) {

		try {

			Integer.parseInt(str);
			return true;

		} catch(NumberFormatException e) {

			return false;

		}
	}

	public static boolean hasPermission(CommandSender sender, String permission) {

		if(sender.isOp()) return true;
		if(sender.hasPermission("ws.admin")) return true;

		if(permission.equalsIgnoreCase("ws.user")) {

			if(sender.hasPermission("ws.user") || sender.hasPermission("ws.assist") || sender.hasPermission("ws.manage")) return true;

		}

		if(permission.equalsIgnoreCase("ws.assist")) {

			if(sender.hasPermission("ws.assist") || sender.hasPermission("ws.manage")) return true;

		}

		if(permission.equalsIgnoreCase("ws.manage")) {

			if(sender.hasPermission("ws.manage")) return true;

		}
		
		if(permission.equalsIgnoreCase("ws.exceed")) {

			if(sender.hasPermission("ws.exceed") || sender.hasPermission("ws.admin")) return true;

		}
		
		if(permission.equalsIgnoreCase("ws.admin")) {

			if(sender.hasPermission("ws.admin")) return true;

		}
		
		return false;
	}
	
	public static boolean hasPermission(Player sender, String permission) {

		if(sender.isOp()) return true;
		if(sender.hasPermission("ws.admin")) return true;

		if(permission.equalsIgnoreCase("ws.user")) {

			if(sender.hasPermission("ws.user") || sender.hasPermission("ws.assist") || sender.hasPermission("ws.manage")) return true;

		}

		if(permission.equalsIgnoreCase("ws.assist")) {

			if(sender.hasPermission("ws.assist") || sender.hasPermission("ws.manage")) return true;

		}

		if(permission.equalsIgnoreCase("ws.manage")) {

			if(sender.hasPermission("ws.manage")) return true;

		}
		
		if(permission.equalsIgnoreCase("ws.exceed")) {

			if(sender.hasPermission("ws.exceed") || sender.hasPermission("ws.admin")) return true;

		}
		
		if(permission.equalsIgnoreCase("ws.admin")) {

			if(sender.hasPermission("ws.admin")) return true;

		}
		
		return false;
	}
	
	public static String statusAsString(boolean trusted, boolean closed) {
		
		if(trusted) return ChatColor.YELLOW + "trusted";
		if(closed) return ChatColor.RED + "closed";
		
		return "failed to get status";
	}

}
