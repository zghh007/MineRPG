package com.Nodac_lzc_woshi85.MineRpg;

import java.util.HashMap;

public class DamageManager 
{
	static HashMap<String,Integer> nextDamage=new HashMap<String,Integer>();
	static HashMap<String,Integer> nextDamageTime=new HashMap<String,Integer>();
	public SkillFailedCause setPlayerNextDamage(String p,int value,int mana)
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
	public SkillFailedCause setPlayerNextDamageTime(String p,int value,int mana)
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
