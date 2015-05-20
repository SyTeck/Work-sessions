package com.syteck.worksessions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;

public class StorageManager {

	private File userPath;
	private Config config;
	private Config players;
	private Config storage;

	private static HashMap<Integer, Session> sessions = new HashMap<Integer, Session>();
	public static HashMap<Integer, Session> getSessionList() {

		return sessions;

	}

	private static HashMap<UUID, User> users = new HashMap<UUID, User>();
	public static HashMap<UUID, User> getUserList() {

		return users;

	}

	private static ArrayList<UUID> trusted = new ArrayList<UUID>();
	public static ArrayList<UUID> getTrustList() {

		return trusted;

	}

	private static ArrayList<UUID> banned = new ArrayList<UUID>();
	public static ArrayList<UUID> getBanList() {

		return banned;

	}

	public void loadUser(UUID uuid) {

		Config config = new Config(new File(userPath, uuid.toString()));

		if(config.exist()) {

			Session session = sessions.get(config.getYaml().getString("session"));
			boolean build = config.getYaml().getBoolean("build");
			boolean trusted = getTrustList().contains(uuid);
			boolean banned = getBanList().contains(uuid);

			if(session != null) {

				User user = new User(uuid, session, build, trusted, banned);

				users.put(uuid, user);

			} else {

				users.put(uuid, new User(uuid, false, trusted, banned));

			}

		} else {

			users.put(uuid, new User(uuid));

		}
	}
	public User getUser(UUID uuid) {

		return users.get(uuid);

	}
	public void unloadUser(UUID uuid) {

		Config config = new Config(new File(userPath, uuid.toString()));
		if(!config.exist()) config.create();

		User user = getUser(uuid);

		config.getYaml().set("uuid", user.getUUID().toString());
		config.getYaml().set("build", user.canBuild());

		if(user.getSession() != null) {

			config.getYaml().set("session", user.getSession().getName());

		} else {

			config.getYaml().set("session", "");

		}

		config.save();

		user.getSession().getPlayerList().remove(user);
		users.remove(user);
	}
	public void saveUser(UUID uuid) {

		Config config = new Config(new File(userPath, uuid.toString()));
		if(!config.exist()) config.create();

		User user = getUser(uuid);

		config.getYaml().set("uuid", user.getUUID().toString());
		config.getYaml().set("build", user.canBuild());

		if(user.getSession() != null) {

			config.getYaml().set("session", user.getSession().getName());

		} else {

			config.getYaml().set("session", "");

		}

		config.save();
	}

	public void createSession(Session session) {

		sessions.put(session.getId(), session);

	}
	public Session getSession(int id) {

		return sessions.get(id);

	}
	public void deleteSession(int id) {

		sessions.remove(id);

	}

	public void load() {

		for(String str: storage.getYaml().getKeys(false)) {

			int id = storage.getYaml().getInt(str + ".id");
			String name = storage.getYaml().getString(str + ".name");
			boolean trusted = storage.getYaml().getBoolean(str + ".trusted"), closed = storage.getYaml().getBoolean(str + ".closed"), disabled = storage.getYaml().getBoolean(str + ".disabled");
			ArrayList<UUID> players = new ArrayList<UUID>(), invites = new ArrayList<UUID>();

			for(String pStr: storage.getYaml().getStringList(str + ".players")) {

				players.add(UUID.fromString(pStr));

			}

			for(String iStr: storage.getYaml().getStringList(str + ".invites")) {

				invites.add(UUID.fromString(iStr));

			}

			Session session = new Session(id, name, trusted, closed, disabled, players, invites);
			sessions.put(id, session);
		}

	}

	public void save() {

		//Potential bug
		players.getYaml().set("trusted", getTrustList());
		players.getYaml().set("banned", getBanList());
		//Potential bug

		for(Entry<UUID, User> entry: users.entrySet()) {

			saveUser(entry.getKey());

		}

		for(Entry<Integer, Session> entry: sessions.entrySet()) {

			int id = entry.getKey();
			Session session = entry.getValue();

			storage.getYaml().set(id + ".id", id);
			storage.getYaml().set(id + ".name", session.getName());
			storage.getYaml().set(id + ".trusted", session.isTrusted());
			storage.getYaml().set(id + ".closed", session.isClosed());
			storage.getYaml().set(id + ".disabled", session.isDisabled());

			ArrayList<String> players = new ArrayList<String>();
			ArrayList<String> invites = new ArrayList<String>();

			for(UUID uuid: session.getPlayerList()) {

				players.add(uuid.toString());

			}

			for(UUID uuid: session.getInviteList()) {

				invites.add(uuid.toString());

			}

			storage.getYaml().set(id + ".invites", players);
			storage.getYaml().set(id + ".invites", invites);
		}

		players.save();
		storage.save();
	}

	public boolean verify() {

		Main main = Main.getInstance();
		boolean created = true;

		if(!config.exist()) {

			Bukkit.getLogger().log(Level.INFO, "Failed to find config.yml, creating new one.");
			main.saveDefaultConfig();

			if(!config.exist()) {

				Bukkit.getLogger().log(Level.INFO, "Failed to create config.yml, contact developer.");
				created = false;

			}
		}

		if(!players.exist()) {

			Bukkit.getLogger().log(Level.INFO, "Failed to find players.yml, creating new one.");

			if(!players.create()) {

				Bukkit.getLogger().log(Level.INFO, "Failed to create players.yml, contact developer.");
				created = false;

			}
		}

		if(!storage.exist()) {

			Bukkit.getLogger().log(Level.INFO, "Failed to find sessions.yml, creating new one.");

			if(!storage.create()) {

				Bukkit.getLogger().log(Level.INFO, "Failed to create sessions.yml, contact developer.");
				created = false;

			}
		}

		if(!userPath.exists()) {

			Bukkit.getLogger().log(Level.INFO, "Failed to find user folder, creating new one.");
			userPath.mkdirs();

			if(!userPath.exists()) {

				Bukkit.getLogger().log(Level.INFO, "Failed to create sessions.yml, contact developer.");
				created = false;

			}
		}

		return created;
	}

	public void setup() {

		Main main = Main.getInstance();

		userPath = new File(main.getDataFolder() + File.pathSeparator + "users");
		config = new Config(new File(main.getDataFolder(), "config.yml"));
		players = new Config(new File(main.getDataFolder(), "players.yml"));
		storage = new Config(new File(main.getDataFolder(), "sessions.yml"));

		verify();
	}
}
