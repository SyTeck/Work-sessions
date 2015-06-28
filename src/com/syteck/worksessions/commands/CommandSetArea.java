package com.syteck.worksessions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.syteck.worksessions.Session;
import com.syteck.worksessions.StorageManager;
import com.syteck.worksessions.User;
import com.syteck.worksessions.utils.Util;

public class CommandSetArea implements Command {

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(verify(sender, args)) {

			User user = StorageManager.getUser(((Player)sender).getUniqueId());

			sender.sendMessage(ChatColor.GOLD+"You can now select an area for your session.");
			sender.sendMessage(ChatColor.YELLOW+"To select area, use " + ChatColor.GOLD + "right" + ChatColor.YELLOW + " and " + ChatColor.GOLD + "left" + ChatColor.YELLOW + " click.");
			sender.sendMessage(ChatColor.YELLOW+"When done, type " + ChatColor.GOLD + "/ws setarea" + ChatColor.YELLOW + " once more.");

			user.setArea(true);
		}
	}

	@Override
	public boolean verify(CommandSender sender, String[] args) {

		if(!Util.hasPermission(sender, "ws.manage")) {

			sender.sendMessage(ChatColor.RED+"You do not have permission to do this.");

			return false;
		}

		if(!StorageManager.getUser(((Player)sender).getUniqueId()).hasSession()) {

			sender.sendMessage(ChatColor.RED+"You are not currently in a session.");

			return false;
		}

		if(StorageManager.getUser(((Player)sender).getUniqueId()).getArea()) {

			Session session = StorageManager.getUser(((Player)sender).getUniqueId()).getSession();

			if(session.getBorder().isFinished()) {

				sender.sendMessage(ChatColor.GREEN+"You have successfully selected an area.");
				StorageManager.getUser(((Player)sender).getUniqueId()).setArea(false);

			} else {

				sender.sendMessage(ChatColor.RED+"You have to finish your selection before finishing.");

			}
		}

		return true;
	}

}
