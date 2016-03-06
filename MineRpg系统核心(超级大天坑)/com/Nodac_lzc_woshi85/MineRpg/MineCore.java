package com.Nodac_lzc_woshi85.MineRpg;

import java.io.File;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
//玩家Config：P_玩家名
//①:等级
//②:额外属性List:{1攻2防3智4敏5幸}
//③:职业1~8/9散人
public class MineCore extends JavaPlugin implements Listener 
{
	@Override
	public void onEnable()
	{
		//没有文件就存文件
		File f = new File(getDataFolder(),"config.yml");
		if(!f.exists())
			/*==>*/{saveConfig();}
		DamageManager dm = new DamageManager();
		
	}
	
	@EventHandler
	public void evt(PlayerJoinEvent evt)
	{
		//取玩家名字
		Player p = evt.getPlayer();
		String name = "State_"+p.getName();
		if(getConfig().contains(name))
		{
			//获取基础等级、属性
			int level = getConfig().getInt(name);
			int 攻=5+level;
			double 防=1+0.1*level;
			int 智=5+level;
			int 敏=5+level;
			int 幸=0;
			//调用玩家背包
			PlayerInventory pi = p.getInventory();
			ItemMeta cloth = pi.getChestplate().getItemMeta();
			ItemMeta hat = pi.getHelmet().getItemMeta();
			ItemMeta leg = pi.getLeggings().getItemMeta();
			ItemMeta boot = pi.getBoots().getItemMeta();
			ItemMeta Weapon = pi.getItemInHand().getItemMeta();
			ItemMeta[] items = {cloth,hat,leg,boot,Weapon};
			//获得玩家属性总值
			for(ItemMeta i:items)
			{
				for(String s:i.getLore())
				{
					char[] c = s.toCharArray();
					if(c.length>=5)
					{
						if(s.startsWith("§2攻击 +"))
						{
							s.replace("§2攻击 +","");
							int in = Integer.valueOf(s);
							攻+=in;
						}
						if(s.startsWith("§2防御 +"))
						{
							s.replace("§2防御 +","");
							int in = Integer.valueOf(s);
							防+=in;
						}
						if(s.startsWith("§2智慧 +"))
						{
							s.replace("§2智慧 +","");
							int in = Integer.valueOf(s);
							智+=in;
						}
						if(s.startsWith("§2敏捷 +"))
						{
							s.replace("§2敏捷 +","");
							int in = Integer.valueOf(s);
							敏+=in;
						}
						if(s.startsWith("§2幸运 +"))
						{
							s.replace("§2幸运 +","");
							int in = Integer.valueOf(s);
							幸+=in;
						}
					}
				}
			}
			FixedMetadataValue atk = new FixedMetadataValue(this,攻);
			FixedMetadataValue def = new FixedMetadataValue(this,防);
			FixedMetadataValue inT = new FixedMetadataValue(this,智);
			FixedMetadataValue spe = new FixedMetadataValue(this,敏);
			FixedMetadataValue luck = new FixedMetadataValue(this,幸);
			p.setMetadata("攻", atk);
			p.setMetadata("防", def);
			p.setMetadata("智", inT);
			p.setMetadata("敏", spe);
			p.setMetadata("幸", luck);
			p.setWalkSpeed(敏/5);
		}
		else
		{   //创建新RPG玩家
			FixedMetadataValue atk = new FixedMetadataValue(this,5);
			FixedMetadataValue def = new FixedMetadataValue(this,1);
			FixedMetadataValue inT = new FixedMetadataValue(this,5);
			FixedMetadataValue spe = new FixedMetadataValue(this,5);
			FixedMetadataValue luck = new FixedMetadataValue(this,0);
			p.setMetadata("攻", atk);
			p.setMetadata("防", def);
			p.setMetadata("智", inT);
			p.setMetadata("敏", spe);
			p.setMetadata("幸", luck);
			getConfig().set(name, 1);
		}
	}
	@EventHandler
	public void evt2(EntityDamageByEntityEvent evt)
	{
		//当玩家攻击其他生物，设置扣血量
		if(evt.getDamager().getType()==EntityType.PLAYER)
		{
			Player p = (Player)evt.getDamager();
			String name = p.getName();
			if(DamageManager.nextDamage.containsKey(name))
			{
				evt.setDamage(DamageManager.nextDamage.get(name));
				DamageManager.nextDamage.remove(name);
				DamageManager.nextDamageTime.remove(name);
			}
			if(DamageManager.nextDamageTime.containsKey(name))
			{
				int time = DamageManager.nextDamageTime.get(name);
				PlayerStateManager ps = new PlayerStateManager();
				double atk = ps.getATK(p.getName());
				double d = atk/5;
				double damage = d*time/100;
				evt.setDamage(damage);
				DamageManager.nextDamage.remove(name);
				DamageManager.nextDamageTime.remove(name);
			}
		}
		////////////////////////////////////////////////////////////////
		//当玩家被其他生物攻击
		if(evt.getEntity().getType()==EntityType.PLAYER)
		{
			Player p = (Player)evt.getEntity();
			PlayerStateManager ps = new PlayerStateManager();
			double def = ps.getDEF(p.getName());
			double d = evt.getDamage()*(1-(def/100));
			if(d<0)d=0;
			evt.setDamage(d);
		}
	}
	public /*static*/ PlayerStateManager getPlayerStateManager()
	{

		return new PlayerStateManager();
	}
	public static DamageManager getDamageManager()
	{
		
		
		return new DamageManager();

	}
	@EventHandler
	public void evt3(InventoryEvent evt)
	{
		if(evt.getView().getType()==InventoryType.PLAYER)
		{
			PlayerInventory i = ((PlayerInventory)evt.getInventory());
			ChestInventory ci = ((ChestInventory)evt.getInventory());
		}
		
	}

}
