package com.github.MineRpg.MobSpawner;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.MineRpg.MobSpawner.Spawner.CustomMobSpawner;
import com.github.MineRpg.MobSpawner.Spawner.MonsterSpawner;

public class MineMain extends JavaPlugin {
	public List<MonsterSpawner> aSpawners = new ArrayList<MonsterSpawner>();

	@Override
	public void onEnable() {
		String name = "自定义怪";
		int ATK = 1;
		int DEF = 1;
		double SPE = 1;
		int period = 1000;// 每多少毫秒循环
		ItemStack[] i = new ItemStack[0];
		EntityMineType type = EntityMineType.Bat;
		// InventoryHolder inventory = Bukkit.geti;
		// InventoryType i = InventoryType.ANVIL;
		// EntityEquipment e = Bukkit.createInventory();
		// MonsterSpawner cms = new MonsterSpawner();
	}
}
