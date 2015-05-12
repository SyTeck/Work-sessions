package com.syteck.worksessions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			
			
			
		} else {
			
			if(args.length < 1) {
				
				new CommandHelp().execute(sender, args);
				
			} else {
				
				String argument = args[0];
				
				if(argument.equalsIgnoreCase("start")) {
					
					
					
				} else if(argument.equalsIgnoreCase("stop")) {
					
					
					
				} else if(argument.equalsIgnoreCase("trust")) {
					
					
					
				} else if(argument.equalsIgnoreCase("untrust")) {
					
					
					
				} else if(argument.equalsIgnoreCase("invite")) {
					
					
					
				} else if(argument.equalsIgnoreCase("uninvite")) {
					
					
					
				} else if(argument.equalsIgnoreCase("player")) {
					
					
					
				} else if(argument.equalsIgnoreCase("kick")) {
					
					
					
				} else if(argument.equalsIgnoreCase("tpall")) {
					
					
					
				} else if(argument.equalsIgnoreCase("setspawn")) {
					
					
					
				} else if(argument.equalsIgnoreCase("setarea")) {
					
					
					
				} else if(argument.equalsIgnoreCase("setkit")) {
					
					
					
				} else if(argument.equalsIgnoreCase("manager")) {
					
					
					
				} else if(argument.equalsIgnoreCase("request")) {
					
					
					
				} else if(argument.equalsIgnoreCase("description")) {
					
					
					
				} else if(argument.equalsIgnoreCase("options")) {
					
					
					
				} else if(argument.equalsIgnoreCase("spawn")) {
					
					
					
				} else if(argument.equalsIgnoreCase("kit")) {
					
					
					
				} else if(argument.equalsIgnoreCase("details")) {
					
					
					
				} else if(argument.equalsIgnoreCase("ban")) {
					
					
					
				} else if(argument.equalsIgnoreCase("unban")) {
					
					
					
				} else if(argument.equalsIgnoreCase("help")) {
					
					
					
				} else if(argument.equalsIgnoreCase("list")) {
					
					
					
				} else if(argument.equalsIgnoreCase("join")) {
					
					
					
				} else if(argument.equalsIgnoreCase("leave")) {
					
					
					
				} else if(argument.equalsIgnoreCase("reload")) {
					
					
					
				} else {
					
					new CommandHelp().execute(sender, args);
					
				}	
			}
		}
		
		return true;
	}

}
