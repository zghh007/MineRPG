package com.github.MineRpg.MineRpg;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

public class ConfigManager implements Listener {
	private MineCore j;

	private FileConfiguration File;
	private File f;

	public ConfigManager(String name) {
//		CreateConfig(name);
		
	}
	
	
	File CreateConfig(String name){
		return new File(j.getDataFolder(), name+".yml");
	}

	public ConfigManager(MineCore mineCore) {
		this.j = mineCore;
		return;
	}

	public boolean save() {
		try {
			File.save(f);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void put(String s, String obj) {
		File.set(s, obj);
	}

	public void put(String s, double obj) {
		File.set(s, obj);
	}

	public void put(String s, List<?> obj) {
		File.set(s, obj);
	}
}
