package net.mythril.player;

import static org.lwjgl.input.Keyboard.*;

public class PlayerInput 
{
	public void pollInput(Player p)
	{
		
		if(isKeyDown(KEY_A))
		{
			p.setX(p.getX() - 1);
		}
		
		if(isKeyDown(KEY_D))
		{
			p.setX(p.getX() + 1);
		}
		
		//for single button presses
		while(next())
		{
			int k = getEventKey();
		}
	}
}
