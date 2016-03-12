package com.Nodak_lzc_woshi85.MineRpg.ScoreBoard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.Nodak_lzc_woshi85.MineRpg.MineCore;

public class MineScoreBoardManager 
{
	static MineCore J = JavaPlugin.getPlugin(MineCore.class);
	public MineScoreBoardManager()
	{
		return ;
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
		Objective obj = sb.registerNewObjective("MineState", "dummy");
		obj.setDisplayName("&7&l&m [&b&l&m         &r&e&lwelcome to Our Server&b&l&m         ]&7&l&m ");
		int ATK = J.getPlayerStateManager().getATK(p.getName());
		obj.getScore("攻属性: "+ATK).setScore(0);
		
	}
}
