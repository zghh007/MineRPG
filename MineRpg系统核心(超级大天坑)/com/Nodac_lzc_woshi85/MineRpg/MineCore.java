package com.Nodac_lzc_woshi85.MineRpg;

import java.io.File;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
//玩家Config：P_玩家名
//①:等级
//②:额外属性List:{1攻2防3智4敏5幸}
//③:职业1~8/9散人
public class MineCore extends JavaPlugin implements Listener 
{
	@Override
	public void onEnable()
	{
		File f = new File(getDataFolder(),"config.yml");
		if(!f.exists())
			/*==>*/{saveConfig();}
		
	}
	
	@EventHandler
	public void evt(PlayerJoinEvent evt)
	{
		Player p = evt.getPlayer();
		String name = "State_"+p.getName();
		if(getConfig().contains(name))
		{
			int level = getConfig().getInt(name);
			int 攻=5+level;
			int 防=5+level;
			int 智=5+level;
			int 敏=5+level;
			int 幸=0;
			PlayerInventory pi = p.getInventory();
			ItemMeta cloth = pi.getChestplate().getItemMeta();
			ItemMeta hat = pi.getHelmet().getItemMeta();
			ItemMeta leg = pi.getLeggings().getItemMeta();
			ItemMeta boot = pi.getBoots().getItemMeta();
			ItemMeta Weapon = pi.getItemInHand().getItemMeta();
			ItemMeta[] items = {cloth,hat,leg,boot,Weapon};
			for(ItemMeta i:items)
			{
				for(String s:i.getLore())
				{
					char[] c = s.toCharArray();
					if(c.length>=5)
					{
						if(s.startsWith("攻击 +"))
						{
							s.replace("攻击 +","");
							int in = Integer.valueOf(s);
							攻+=in;
						}
						if(s.startsWith("防御 +"))
						{
							s.replace("防御 +","");
							int in = Integer.valueOf(s);
							防+=in;
						}
						if(s.startsWith("智慧 +"))
						{
							s.replace("智慧 +","");
							int in = Integer.valueOf(s);
							智+=in;
						}
						if(s.startsWith("敏捷 +"))
						{
							s.replace("敏捷 +","");
							int in = Integer.valueOf(s);
							敏+=in;
						}
						if(s.startsWith("幸运 +"))
						{
							s.replace("幸运 +","");
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
		}
		else
		{
			FixedMetadataValue atk = new FixedMetadataValue(this,5);
			FixedMetadataValue def = new FixedMetadataValue(this,5);
			FixedMetadataValue inT = new FixedMetadataValue(this,5);
			FixedMetadataValue spe = new FixedMetadataValue(this,5);
			FixedMetadataValue luck = new FixedMetadataValue(this,5);
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
			{//五属性未添加
				int time = DamageManager.nextDamageTime.get(name);
				double damage = evt.getDamage()*time;
				evt.setDamage(damage);
				DamageManager.nextDamage.remove(name);
				DamageManager.nextDamageTime.remove(name);
			}
		}
	}
}
