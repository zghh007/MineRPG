package com.github.MineRpg.MineRpg;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DamageManager 
{
	public DamageManager DamageManager()
	{
		return this;
	}
	HashMap<String,Integer> nextDamage=new HashMap<String,Integer>();
	HashMap<String,Integer> nextDamageTime=new HashMap<String,Integer>();
	public SkillFailedCause setPlayerNextDamage(String p,int value)
	{
		Player pl = Bukkit.getPlayer("p");
		if(pl!=null&&pl.isOnline()==true)
		{
			if(nextDamageTime.containsKey(p)|nextDamage.containsKey(p))
			{
				return SkillFailedCause.HasUsed;	
			}
			else
			{
				nextDamage.put(p, value);
				return SkillFailedCause.Successful;
			}
		}
		else
		{
			return SkillFailedCause.PlayerNotFound;
		}
	}
	public SkillFailedCause setPlayerNextDamageTime(String p,int value)
	{
		if(nextDamageTime.containsKey(p)|nextDamage.containsKey(p))
		{
			return SkillFailedCause.HasUsed;	
		}
		else
		{
			nextDamageTime.put(p, value);
			return SkillFailedCause.Successful;
		}
	}
}
