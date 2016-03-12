package com.github.MineRpg.MineRpg;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerStateManager 
{
	public PlayerStateManager PlayerStateManager()
	{
		return this;
	}
	public int getINT(String p)
	{
		Player pl = Bukkit.getPlayer(p);
		if(pl!=null&&pl.isOnline())
		{
			int Int = pl.getMetadata("智").get(0).asInt();
			return Int;
		}
		else
		{
		return 0;
		}
	}
	public int getATK(String p)
	{
		Player pl = Bukkit.getPlayer(p);
		if(pl!=null&&pl.isOnline())
		{
			int Int = pl.getMetadata("攻").get(0).asInt();
			return Int;
		}
		else
		{
		return 0;
		}
	}
	public double getDEF(String p)
	{
		Player pl = Bukkit.getPlayer(p);
		if(pl!=null&&pl.isOnline())
		{
			double Int = pl.getMetadata("防").get(0).asInt();
			return Int;
		}
		else
		{
		return 0;
		}
	}
	public int getSPE(String p)
	{
		Player pl = Bukkit.getPlayer(p);
		if(pl!=null&&pl.isOnline())
		{
			int Int = pl.getMetadata("敏").get(0).asInt();
			return Int;
		}
		else
		{
		return 0;
		}
	}
	public int getLUCK(String p)
	{
		Player pl = Bukkit.getPlayer(p);
		if(pl!=null&&pl.isOnline())
		{
			int Int = pl.getMetadata("幸").get(0).asInt();
			return Int;
		}
		else
		{
		return 0;
		}
	}
	public int getLevel(String p)
	{
		JavaPlugin j = JavaPlugin.getPlugin(MineCore.class);
		String name = "State_"+p;
		if(j.getConfig().contains(name))
		{
			int Int = j.getConfig().getInt(name);
			return Int;
		}
		else
		{
		return 0;
		}
	}
	public int getExp(String p)
	{
		JavaPlugin j = JavaPlugin.getPlugin(MineCore.class);
		String name = "Exp_"+p;
		if(j.getConfig().contains(name))
		{
			int Int = j.getConfig().getInt(name);
			return Int;
		}
		else
		{
		j.getConfig().set(name, 0);
		return 0;
		}
	}
	public void setExp(String p,int exp)
	{
		JavaPlugin j = JavaPlugin.getPlugin(MineCore.class);
		String name = "Exp_"+p;
		j.getConfig().set(name, exp);

	}
	public void setLevel(String p,int level)
	{
		JavaPlugin j = JavaPlugin.getPlugin(MineCore.class);
		String name = "State_"+p;
		j.getConfig().set(name,level);
	}
	public SkillFailedCause refreshPlayer(String p)
	{
		if(Bukkit.getPlayer(p)==null||!Bukkit.getPlayer(p).isOnline())
		{
			return SkillFailedCause.PlayerNotFound;
		}
		JavaPlugin j = JavaPlugin.getPlugin(MineCore.class);
		Player pl = Bukkit.getPlayer(p);
		String name = "State_"+p;
		//获取基础等级、属性
		int level = j.getConfig().getInt(name);
		int 攻=5+level;
		double 防=1+0.1*level;
		int 智=5+level;
		int 敏=5+level;
		int 幸=0;
		//调用玩家背包
		PlayerInventory pi = pl.getInventory();
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
		FixedMetadataValue atk = new FixedMetadataValue(j,攻);
		FixedMetadataValue def = new FixedMetadataValue(j,防);
		FixedMetadataValue inT = new FixedMetadataValue(j,智);
		FixedMetadataValue spe = new FixedMetadataValue(j,敏);
		FixedMetadataValue luck = new FixedMetadataValue(j,幸);
		pl.removeMetadata("攻",j);
		pl.removeMetadata("防",j);
		pl.removeMetadata("智",j);
		pl.removeMetadata("敏",j);
		pl.removeMetadata("幸",j);
		pl.setMetadata("攻", atk);
		pl.setMetadata("防", def);
		pl.setMetadata("智", inT);
		pl.setMetadata("敏", spe);
		pl.setMetadata("幸", luck);
		pl.setWalkSpeed(敏/5);
		return SkillFailedCause.Successful;
	}
}
