package com.syteck.worksessions;

import java.util.ArrayList;

public class Session {

	private String name;

	//CLOSED = PRIVATE, TRUSTED = TRUSTED, DISABLED = CLOSED;
	private boolean trusted, closed, disabled;
	private ArrayList<User> players, invited;

	public Session(String name, boolean trusted, boolean closed, boolean disabled) {

		this.name = name;

		this.trusted = trusted;
		this.closed = closed;
		this.disabled = disabled;

		players = new ArrayList<User>();
		invited = new ArrayList<User>();

	}
	public Session(String name, boolean trusted, boolean closed, boolean disabled, ArrayList<User> players, ArrayList<User> invited) {

		this.name = name;

		this.trusted = trusted;
		this.closed = closed;
		this.disabled = disabled;

		this.players = players;
		this.invited = invited;

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

	public ArrayList<User> getPlayerList() {

		return this.players;

	}
	public ArrayList<User> getInviteList() {

		return this.invited;

	}


}