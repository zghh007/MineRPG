package com.github.MineRpg.MobSpawner.Spawner;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.MineRpg.MobSpawner.EntityMineType;

public interface CustomMobSpawner {
	public Location getLocation();

	public void setLocation(Location l);

	public int getATK();

	public int getDEF();

	public double getSPE();

	public void setATK(int i);

	public void setDEF(int i);

	public void setSPE(double d);

	public String getName();

	public void setName(String s);

	public long getTime();

	public void setTime(long l);

	public int getPeriod();

	public void setPeriod(int period);

	public EntityMineType getType();

	public void getType(EntityMineType type);

	public ItemStack[] getDrops();

	public void setDrop(ItemStack[] i);

	public EntityEquipment getEquipment();

	public void setEquipment(EntityEquipment l);
}
