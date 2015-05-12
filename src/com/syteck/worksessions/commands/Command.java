package com.syteck.worksessions.commands;

import org.bukkit.command.CommandSender;

public interface Command {

	public void execute(CommandSender sender, String[] args);
	public boolean verify(CommandSender sender, String[] args);
	
}
