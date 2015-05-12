package com.syteck.worksessions.commands;

import org.bukkit.command.CommandSender;

public class CommandHelp implements Command {

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(verify(sender, args)) {
			
			
			
		}
	}

	@Override
	public boolean verify(CommandSender sender, String[] args) {
		
		return sender.hasPermission("ws.user");
		
	}

}
