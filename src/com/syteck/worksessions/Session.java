package com.syteck.worksessions;

import java.util.ArrayList;
import java.util.UUID;

public class Session {

	private int id;
	private String name;

	//CLOSED = PRIVATE, TRUSTED = TRUSTED, DISABLED = CLOSED;
	private boolean trusted, closed, disabled;
	private ArrayList<UUID> players, invited;

	public Session(int id, String name) {
		
		this.id = id;
		this.name = name;

		this.trusted = false;
		this.closed = false;
		this.disabled = false;

		players = new ArrayList<UUID>();
		invited = new ArrayList<UUID>();
		
	}
	public Session(int id, String name, boolean trusted, boolean closed, boolean disabled) {

		this.id = id;
		this.name = name;

		this.trusted = trusted;
		this.closed = closed;
		this.disabled = disabled;

		players = new ArrayList<UUID>();
		invited = new ArrayList<UUID>();

	}
	public Session(int id, String name, boolean trusted, boolean closed, boolean disabled, ArrayList<UUID> players, ArrayList<UUID> invited) {

		this.id = id;
		this.name = name;

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

	public ArrayList<UUID> getPlayerList() {

		return this.players;

	}
	public ArrayList<UUID> getInviteList() {

		return this.invited;

	}


}