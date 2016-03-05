package com.Nodac_lzc_woshi85.MineRpg;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
	public int getDEF(String p)
	{
		Player pl = Bukkit.getPlayer(p);
		if(pl!=null&&pl.isOnline())
		{
			int Int = pl.getMetadata("防").get(0).asInt();
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
}
