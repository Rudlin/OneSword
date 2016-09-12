package net.mythril.enemy;

import net.mythril.entity.Entity;
import net.mythril.player.Player;

public class Boss extends Entity
{
	Player p;
	
	int damageDealed = 0;
	public Boss(int x, int y, int width, int height, Player p) 
	{
		super(x, y, width, height);
		this.p = p;
	}
	
	public void damagePlayer()
	{
		p.getHealthLst().remove(p.getHealthLst().size() - damageDealed);
		damageDealed += 1;
	}
}
