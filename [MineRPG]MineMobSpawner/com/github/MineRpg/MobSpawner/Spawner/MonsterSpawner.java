package com.github.MineRpg.MobSpawner.Spawner;

import java.util.Date;

import org.bukkit.Location;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.MineRpg.MobSpawner.EntityMineType;
import com.github.MineRpg.MobSpawner.MineMain;

public class MonsterSpawner implements CustomMobSpawner {

	private String name;
	private int ATK;
	private int DEF;
	private double SPE;
	private int Period;
	private long time;
	private ItemStack[] Drops;
	private EntityEquipment EE;
	private EntityMineType Type;
	private Location L;

	public MonsterSpawner(String name, int ATK, int DEF, double SPE, int Period, ItemStack[] Drops, EntityMineType Type,
			EntityEquipment EE, Location l) {
		this.name = name;
		this.ATK = ATK;
		this.DEF = DEF;
		this.SPE = SPE;
		this.Period = Period;
		this.time = new Date().getTime();
		this.Drops = Drops;
		this.Type = Type;
		this.EE = EE;
		this.L = l;
		MineMain j = JavaPlugin.getPlugin(MineMain.class);
		j.aSpawners.add(this);
	}

	@Override
	public int getATK() {

		return ATK;
	}

	@Override
	public int getDEF() {

		return DEF;
	}

	@Override
	public double getSPE() {

		return SPE;
	}

	@Override
	public void setATK(int i) {
		this.ATK = i;

	}

	@Override
	public void setDEF(int i) {
		this.DEF = i;

	}

	@Override
	public void setSPE(double d) {
		this.SPE = d;

	}

	@Override
	public String getName() {

		return name;
	}

	@Override
	public void setName(String s) {
		this.name = s;

	}

	@Override
	public long getTime() {

		return time;
	}

	@Override
	public void setTime(long l) {
		this.time = l;

	}

	@Override
	public int getPeriod() {

		return Period;
	}

	@Override
	public void setPeriod(int period) {
		this.Period = period;

	}

	@Override
	public EntityMineType getType() {

		return Type;
	}

	@Override
	public void getType(EntityMineType type) {
		this.Type = type;

	}

	@Override
	public ItemStack[] getDrops() {

		return Drops;
	}

	@Override
	public void setDrop(ItemStack[] i) {
		this.Drops = i;

	}

	@Override
	public EntityEquipment getEquipment() {

		return EE;
	}

	@Override
	public void setEquipment(EntityEquipment l) {
		this.EE = l;

	}

	private void spawnNewEntity() {

	}

	@Override
	public Location getLocation() {

		return L;
	}

	@Override
	public void setLocation(Location l) {

		this.L = l;
	}

}
