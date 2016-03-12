package com.github.MineRpg.MineRpg;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {
	private MineCore j = JavaPlugin.getPlugin(MineCore.class);
	private File f;
	private YamlConfiguration y;

	public ConfigManager(String name) {
		f = new File(j.getDataFolder(), name + ".yml");
		if (!f.exists()) {
			try {
				f.createNewFile();

			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		YamlConfiguration y = YamlConfiguration.loadConfiguration(f);

	}

	@Deprecated
	public YamlConfiguration getConfig() {
		return y;
	}

	public boolean save() {
		try {
			y.save(f);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void put(String s, String obj) {
		y.set(s, obj);
	}

	public void put(String s, double obj) {
		y.set(s, obj);
	}

	public void put(String s, List obj) {
		y.set(s, obj);
	}
}
