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
		p.getHealthLst().set(damageDealed, false);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		damageDealed += 1;
	}
}
