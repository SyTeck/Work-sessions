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
	private Config storage;

	private static HashMap<String, Session> sessions = new HashMap<String, Session>();
	public static HashMap<String, Session> getSessionList() {

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

		if(!users.containsKey(uuid)) {

			users.put(uuid, new User(uuid));

		}

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

	public void load() {

		for(String str: storage.getYaml().getKeys(false)) {

			String name = storage.getYaml().getString(str + ".name");
			boolean trusted = storage.getYaml().getBoolean(str + ".trusted"), closed = storage.getYaml().getBoolean(str + ".closed"), disabled = storage.getYaml().getBoolean(str + ".disabled");
			ArrayList<UUID> players = new ArrayList<UUID>(), invites = new ArrayList<UUID>();

			for(String pStr: storage.getYaml().getStringList(str + ".players")) {

				players.add(UUID.fromString(pStr));

			}

			for(String iStr: storage.getYaml().getStringList(str + ".invites")) {

				invites.add(UUID.fromString(iStr));

			}

			Session session = new Session(name, trusted, closed, disabled, players, invites);
			sessions.put(name, session);
		}

	}

	public void save() {

		for(Entry<UUID, User> entry: users.entrySet()) {

			saveUser(entry.getKey());

		}

		for(Entry<String, Session> entry: sessions.entrySet()) {

			String name = entry.getKey();
			Session session = entry.getValue();

			storage.getYaml().set(name + ".name", name);
			storage.getYaml().set(name + ".trusted", session.isTrusted());
			storage.getYaml().set(name + ".closed", session.isClosed());
			storage.getYaml().set(name + ".disabled", session.isDisabled());

			ArrayList<String> players = new ArrayList<String>();
			ArrayList<String> invites = new ArrayList<String>();

			for(UUID uuid: session.getPlayerList()) {

				players.add(uuid.toString());

			}

			for(UUID uuid: session.getInviteList()) {

				invites.add(uuid.toString());

			}

			storage.getYaml().set(name + ".invites", players);
			storage.getYaml().set(name + ".invites", invites);
		}

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
		storage = new Config(new File(main.getDataFolder(), "sessions.yml"));

		verify();
	}
}
