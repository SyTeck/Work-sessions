package com.syteck.worksessions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("This plugin is only compatible with players.");
			
		} else {
			
			if(args.length < 1) {
				
				new CommandHelp().execute(sender, args);
				
			} else {
				
				String argument = args[0];
				
				if(argument.equalsIgnoreCase("start")) {
					
					new CommandStart().execute(sender, args);;
					
				} else if(argument.equalsIgnoreCase("stop")) {
					
					new CommandStop().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("trust")) {
					
					new CommandTrust().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("untrust")) {
					
					new CommandUntrust().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("invite")) {
					
					new CommandInvite().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("uninvite")) {
					
					new CommandUninvite().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("player")) {
					
					new CommandPlayer().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("kick")) {
					
					new CommandKick().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("tpall")) {
					
					new CommandTpall().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("setspawn")) {
					
					new CommandSetSpawn().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("setarea")) {
					
					new CommandSetArea().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("setkit")) {
					
					sender.sendMessage(ChatColor.RED+"This feature is not available yet.");
					
				} else if(argument.equalsIgnoreCase("manager")) {
					
					new CommandManager().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("request")) {
					
					new CommandRequest().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("description")) {
					
					new CommandDescription().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("options")) {
					
					new CommandOptions().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("spawn")) {
					
					new CommandSpawn().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("kit")) {
					
					sender.sendMessage(ChatColor.RED+"This feature is not available yet.");
					
				} else if(argument.equalsIgnoreCase("details")) {
					
					new CommandDetails().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("ban")) {
					
					new CommandBan().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("unban")) {
					
					new CommandUnban().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("help")) {
					
					new CommandHelp().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("list")) {
					
					new CommandList().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("join")) {
					
					new CommandJoin().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("leave")) {
					
					new CommandLeave().execute(sender, args);
					
				} else if(argument.equalsIgnoreCase("reload")) {
					
					new CommandReload().execute(sender, args);
					
				} else {
					
					new CommandHelp().execute(sender, args);
					
				}	
			}
		}
		
		return true;
	}

}
