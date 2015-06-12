package com.syteck.worksessions;

import java.util.UUID;

public class User {

	private UUID uuid;
	private Session session;

	private boolean build, area, trusted, banned;

	public User(UUID uuid) {
		
		this.uuid = uuid;
		this.session = null;

		this.build = false;
		this.trusted = false;
		this.banned = false;
		this.area = false;
		
	}
	public User(UUID uuid, boolean build, boolean trusted, boolean banned) {

		this.uuid = uuid;
		this.session = null;

		this.build = build;
		this.trusted = trusted;
		this.banned = banned;
		this.area = false;
		
	}
	public User(UUID uuid, Session session, boolean build, boolean trusted, boolean banned) {

		this.uuid = uuid;
		this.session = session;

		this.build = build;
		this.trusted = trusted;
		this.banned = banned;
		this.area = false;

	}

	public UUID getUUID() {

		return this.uuid;

	}
	
	public void setBuild(boolean value) {
		
		this.build = value;
		
	}
	public boolean canBuild() {
		
		return this.build;
		
	}
	
	public void setArea(boolean value) {
		
		this.area = value;
		
	}
	
	public boolean getArea() {
		
		return this.area;
		
	}

	public void setTrusted(boolean value) {

		this.trusted = value;

	}
	public boolean isTrusted() {

		return this.trusted;

	}

	public void setBanned(boolean value) {

		this.banned = value;

	}
	public boolean isBanned() {

		return this.banned;

	}

	public void setSession(Session session) {

		this.session = session;

	}
	public Session getSession() {

		return this.session;

	}
	public boolean hasSession() {

		return session != null;

	}

}
