package com.syteck.worksessions.commands;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.syteck.worksessions.Border;
import com.syteck.worksessions.Session;
import com.syteck.worksessions.StorageManager;
import com.syteck.worksessions.User;
import com.syteck.worksessions.utils.Util;

public class CommandStart implements Command {

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(verify(sender, args)) {
			
			String name = args [1];
			int id = 0;
			
			for(int i = 1; i < 999; i++) {
				
				if(!StorageManager.getSessionList().containsKey(i)) {
					
					id = i;
				
					break;
				}
			}
			
			ArrayList<UUID> players = new ArrayList<UUID>(), invited = new ArrayList<UUID>();
			User user = StorageManager.getUser(((Player)sender).getUniqueId());
			Session session = new Session(id, name, new Border(), ((Player)sender).getLocation(), false, false, false, false, players, invited);
			
			session.setDisabled(true);
			session.setManager(user);
			user.setSession(session);
			
			StorageManager.createSession(session);
			sender.sendMessage(ChatColor.GOLD+"You have created a new work session.");
			sender.sendMessage(ChatColor.GOLD+"Use the following commands to manage the session:");
			sender.sendMessage(ChatColor.YELLOW+"/ws setarea");
			sender.sendMessage(ChatColor.YELLOW+"/ws setkit");
			sender.sendMessage(ChatColor.YELLOW+"/ws setspawn");
			sender.sendMessage(ChatColor.YELLOW+"/ws description");
			sender.sendMessage(ChatColor.YELLOW+"/ws options");
			
		}
	}

	@Override
	public boolean verify(CommandSender sender, String[] args) {
		
		if(!Util.hasPermission(sender, "ws.manage")) {
			
			sender.sendMessage(ChatColor.RED+"You do not have permission to do this.");
			
			return false;
		}
		
		if(args.length < 2) {
			
			sender.sendMessage(ChatColor.RED+"Usage: /ws start (name)");
			
			return false;
		}
		
		if(StorageManager.getUser(((Player)sender).getUniqueId()).hasSession()) {
			
			sender.sendMessage(ChatColor.RED+"You are already in a session.");
			
			return false;
		}
		
		return true;
	}

}
