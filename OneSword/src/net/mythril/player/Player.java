package net.mythril.player;

import net.mythril.entity.Entity;

import static org.lwjgl.input.Keyboard.*;

public class Player extends Entity
{
	float jSpd = 10.0f;
	float walkSpd = 2.0f;
	boolean isJumping = false;
	
	int spd, acc, wgt;
	
	public float getjSpd() {
		return jSpd;
	}

	public boolean isJumping() {
		return isJumping;
	}

	Entity e;
	public Player(int x, int y, int width, int height) 
	{
		super(x, y, width, height);
	}
	
	public void pollInput()
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
		
		if(isKeyDown(KEY_A))
		{
			setX(getX() - walkSpd);
		}
		
		if(isKeyDown(KEY_D))
		{
			setX(getX() + walkSpd);
		}
		
		//for single button presses
		while(next())
		{
			int k = getEventKey();
		}
	}
	
	public void pCollide(Entity e)
	{
		this.e = e;
		
	  	if(isJumping) {
			if(getY() <= e.getY() - getHeight()) {
				setY(getY() - jSpd);
				
				jSpd -= 0.1f;
			} else if(jSpd == 0f) {
				isJumping = false;
			}
			
		} else if(!isJumping && getY() > 472) {
			jSpd = 0f;
			setY(472);
		}
	}
	
	public void checkStats()
	{
		
	}

	public void pollCombatInput() 
	{
		
	}
}
