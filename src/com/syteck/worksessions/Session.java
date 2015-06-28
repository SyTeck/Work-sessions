package com.syteck.worksessions;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Session {

	private int id;
	private String name;
	private Border border;
	private Location spawn;

	//CLOSED = PRIVATE, TRUSTED = TRUSTED, DISABLED = CLOSED;
	private User manager;
	private boolean trusted, closed, disabled, teamspeak;
	private ArrayList<UUID> players, invited;

	public Session(int id, String name, Border border, Location spawn, boolean trusted, boolean closed, boolean disabled, boolean teamspeak, ArrayList<UUID> players, ArrayList<UUID> invited) {

		this.id = id;
		this.name = name;
		this.border = border;

		this.trusted = trusted;
		this.closed = closed;
		this.disabled = disabled;

		this.players = players;
		this.invited = invited;
		
	}

	public int getId() {
		
		return this.id;
		
	}
	
	public String getName() {

		return this.name;

	}
	
	public void setBorder(Border border) {
		
		this.border = border;
		
	}
	
	public Border getBorder() {
		
		return this.border;
		
	}
	
	public void setSpawn(Location spawn) {
		
		this.spawn = spawn;
		
	}
	
	public Location getSpawn() {
		
		return this.spawn;
		
	}
	
	public void setManager(User user) {
		
		this.manager = user;
		
	}
	
	public boolean hasManager() {
		
		return this.manager != null;
		
	}
	
	public User getManager() {
		
		return this.manager;
		
	}

	public void setTrusted(boolean value) {

		this.trusted = value;

	}
	public boolean isTrusted() {

		return this.trusted;

	}

	public void setClosed(boolean value) {

		this.closed = value;

	}
	public boolean isClosed() {

		return this.closed;

	}

	public void setDisabled(boolean value) {

		this.disabled = value;

	}
	public boolean isDisabled() {

		return this.disabled;

	}
	
	public void setTeamspeak(boolean value) {
		
		this.teamspeak = value;
		
	}
	public boolean getTeamspeak() {
		
		return this.teamspeak;
		
	}
	
	public void broadcast(String str) {
		
		for(UUID uuid: players) {
			
			Bukkit.getPlayer(uuid).sendMessage(str);
			
		}
	}

	public ArrayList<UUID> getPlayerList() {

		return this.players;

	}
	public ArrayList<UUID> getInviteList() {

		return this.invited;

	}


}