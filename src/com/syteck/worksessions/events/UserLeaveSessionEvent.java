package com.syteck.worksessions.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.syteck.worksessions.User;

public class UserLeaveSessionEvent extends Event {

	User user;
	
	public UserLeaveSessionEvent(User user) {
		
		this.user = user;
		
	}
	
	public User getUser() {
		
		return this.user;
		
	}
	
	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}
}
