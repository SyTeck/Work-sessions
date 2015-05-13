package com.syteck.worksessions;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	private File file;
	private YamlConfiguration yaml;

	public Config(File file) {

		this.file = file;
		this.yaml = YamlConfiguration.loadConfiguration(file);

	}

	public File getFile() {

		return this.file;

	}
	public YamlConfiguration getYaml() {

		return this.yaml;

	}

	public void save() {

		try {
			
			yaml.save(file);

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public boolean create() {
		
		try {
			
			return (file.mkdirs() && file.createNewFile());
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
	}

	public boolean exist() {

		return file.exists();

	}

	public void reload() {
		
		save();
		yaml = YamlConfiguration.loadConfiguration(file);
		
	}
}