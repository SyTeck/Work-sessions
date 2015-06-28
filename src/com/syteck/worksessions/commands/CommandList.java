package com.syteck.worksessions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.syteck.worksessions.Session;
import com.syteck.worksessions.StorageManager;
import com.syteck.worksessions.utils.Util;

public class CommandList implements Command {

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(verify(sender, args)) {
			
			sender.sendMessage(ChatColor.GOLD+"Listing sessions:");
			
			for(Session session: StorageManager.getSessionList().values()) {
				
				if(session.isDisabled()) continue;
				
				int id = session.getId();
				
				sender.sendMessage(ChatColor.GOLD + String.valueOf(id) +": " + ChatColor.YELLOW + " "+session.getName()+" - "+Util.statusAsString(session.isTrusted(), session.isClosed()));
			
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
