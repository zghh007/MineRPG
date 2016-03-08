package com.Nodac_lzc_woshi85.MineRpg.ScoreBoard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class MineScoreBoardManager 
{
	public MineScoreBoardManager MineScoreBoardManager()
	{
		return this;
	}
	
	public void setScoreBoard(Player p)
	{
		if(p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)!=null)
		{
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		}
		if(p.getScoreboard()==null)
		{
			p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		}
		Scoreboard sb = p.getScoreboard();
		Objective obj = sb.getObjective(DisplaySlot.SIDEBAR);
		obj.setDisplayName("&7&l&m [&b&l&m         &r&e&lwelcome to Our Server&b&l&m         ]&7&l&m ");
		
	}
}
