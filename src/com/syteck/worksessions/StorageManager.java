package com.syteck.worksessions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class StorageManager {

	private static File userPath;
	private static Config config;
	private static Config players;
	private static Config storage;

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

	public static void loadUser(UUID uuid) {

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
	public static User getUser(UUID uuid) {

		return users.get(uuid);

	}
	public static void unloadUser(UUID uuid) {

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
	public static void saveUser(UUID uuid) {

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

	public static void createSession(Session session) {

		sessions.put(session.getId(), session);

	}
	public static Session getSession(int id) {

		return sessions.get(id);

	}
	public static void deleteSession(int id) {

		sessions.remove(id);

	}

	public static void load() {

		YamlConfiguration yaml = storage.getYaml();

		for(String str: yaml.getKeys(false)) {

			int id = yaml.getInt(str + ".id");
			String name = yaml.getString(str + ".name");
			boolean trusted = storage.getYaml().getBoolean(str + ".trusted"), closed = storage.getYaml().getBoolean(str + ".closed"), disabled = storage.getYaml().getBoolean(str + ".disabled");
			ArrayList<UUID> players = new ArrayList<UUID>(), invites = new ArrayList<UUID>();
			Border border = new Border();

			if(yaml.contains(str + ".border")) {

				World world = Bukkit.getWorld(yaml.getString(str + ".border.world"));
				Location loc1 = new Location(world, yaml.getInt(str + ".border.x1"), yaml.getInt(str + ".border.y1"), yaml.getInt(str + ".border.z1")), loc2 = new Location(world, yaml.getInt(str + ".border.x2"), yaml.getInt(str + ".border.y2"), yaml.getInt(str + ".border.z2"));
				border = new Border(world, loc1, loc2);

			}

			Location spawn;

			World spawnW = Bukkit.getWorld(yaml.getString(str + ".spawn.world"));
			double spawnX = yaml.getDouble(str + ".spawn.x"), spawnY = yaml.getDouble(str + ".spawn.y"), spawnZ = yaml.getDouble(str + ".spawn.z");

			spawn = new Location(spawnW, spawnX, spawnY, spawnZ);

			for(String pStr: storage.getYaml().getStringList(str + ".players")) {

				players.add(UUID.fromString(pStr));

			}

			for(String iStr: storage.getYaml().getStringList(str + ".invites")) {

				invites.add(UUID.fromString(iStr));

			}

			Session session = new Session(id, name, border, spawn, trusted, closed, disabled, players, invites);
			sessions.put(id, session);
		}

	}

	public static void save() {

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
			Border border = session.getBorder();

			storage.getYaml().set(id + ".id", id);
			storage.getYaml().set(id + ".name", session.getName());
			storage.getYaml().set(id + ".trusted", session.isTrusted());
			storage.getYaml().set(id + ".closed", session.isClosed());
			storage.getYaml().set(id + ".disabled", session.isDisabled());

			storage.getYaml().set(id + ".spawn.world", session.getSpawn().getWorld().getName());
			storage.getYaml().set(id + ".spawn.x", session.getSpawn().getBlockX());
			storage.getYaml().set(id + ".spawn.y", session.getSpawn().getBlockY());
			storage.getYaml().set(id + ".spawn.z", session.getSpawn().getBlockZ());

			if(border.isFinished()) {

				storage.getYaml().set(id + ".border.world", border.getWorld().getName());

				storage.getYaml().set(id + ".border.x1", border.getLocation(1).getBlockX());
				storage.getYaml().set(id + ".border.y1", border.getLocation(1).getBlockY());
				storage.getYaml().set(id + ".border.z1", border.getLocation(1).getBlockZ());

				storage.getYaml().set(id + ".border.x2", border.getLocation(2).getBlockX());
				storage.getYaml().set(id + ".border.y2", border.getLocation(2).getBlockY());
				storage.getYaml().set(id + ".border.z2", border.getLocation(2).getBlockZ());

			}

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

	public static boolean verify() {

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
