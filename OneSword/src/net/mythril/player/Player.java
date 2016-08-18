package net.mythril.player;

import net.mythril.entity.Entity;

import static org.lwjgl.input.Keyboard.*;

public class Player extends Entity
{
	float jSpd = 10.0f;
	boolean isJumping = false;
	public Player(int x, int y, int width, int height) 
	{
		super(x, y, width, height);
	}
	
	public void pollInput(Entity e)
	{
		if(!isJumping)
		{
			if(isKeyDown(KEY_W))
			{
				jSpd = 5.0f;
				if(getY() == e.getY() - getHeight()) 
				{
					isJumping = true;
				}
			}
		}
		
		if(isJumping)
		{
			if(getY() > e.getY() - getHeight()) {
				isJumping = false;
				setY(e.getY() - getHeight());
			} else if(getY() <= e.getY() - getHeight()) {
				setY(getY() - jSpd);
				
				jSpd -= 0.1f;
			}
		}
		
		if(isKeyDown(KEY_A))
		{
			setX(getX() - 1);
		}
		
		if(isKeyDown(KEY_D))
		{
			setX(getX() + 1);
		}
		
		//for single button presses
		while(next())
		{
			int k = getEventKey();
		}
	}

}
