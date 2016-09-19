package net.mythril.player;

import net.mythril.enemy.Boss;
import net.mythril.entity.Entity;
import net.mythril.resources.RLoader;

import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;
TODO: Establish what happens when player defeats a boss
public class Player extends Entity
{
	float jSpd = 10.0f;
	float walkSpd = 2.0f;
	boolean isJumping = false;
	
	int spd = 1, acc = 1, wgt = 1;
	
	 Texture statTex;
	 
	 RLoader resourceloader = new RLoader();
	 
	 int ox = 72, oy = 112;
	 
	 int damageDealed = 0;
	 
	 Random rand = new Random();
	 
	 boolean blargh, isSelecting;
	
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
	
	public void loadStats()
	{
		try {
			statTex = resourceloader.loadTexFrom("rsc/spr/health.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void renderStats()
	{
		statTex.bind(); 
		
		glBegin(GL_QUADS);
			for(int i = 0; i < spd; i++)
			{
				glTexCoord2f(0,0);
				glVertex2f(ox, oy + (i*48));
				glTexCoord2f(1,0);
				glVertex2f(ox + statTex.getTextureWidth() + 16, oy + (i*48));
				glTexCoord2f(1,1);
				glVertex2f(ox + statTex.getTextureWidth() + 16, oy + statTex.getTextureHeight() - 8 + (i*48));
				glTexCoord2f(0,1);
				glVertex2f(ox, oy + statTex.getTextureHeight() - 8 + (i*48));
			}
			
			for(int i = 0; i < acc; i++)
			{
				glTexCoord2f(0,0);
				glVertex2f(ox + 80, oy + (i*48));
				glTexCoord2f(1,0);
				glVertex2f(ox + statTex.getTextureWidth() + 16 + 80, oy + (i*48));
				glTexCoord2f(1,1);
				glVertex2f(ox + statTex.getTextureWidth() + 16 + 80, oy + statTex.getTextureHeight() - 8 + (i*48));
				glTexCoord2f(0,1);
				glVertex2f(ox + 80, oy + statTex.getTextureHeight() - 8 + (i*48));
			}
			
			for(int i = 0; i < wgt; i++)
			{
				glTexCoord2f(0,0);
				glVertex2f(ox + 166, oy + (i*48));
				glTexCoord2f(1,0);
				glVertex2f(ox + statTex.getTextureWidth() + 16 + 166, oy + (i*48));
				glTexCoord2f(1,1);
				glVertex2f(ox + statTex.getTextureWidth() + 16 + 166, oy + statTex.getTextureHeight() - 8 + (i*48));
				glTexCoord2f(0,1);
				glVertex2f(ox + 166, oy + statTex.getTextureHeight() - 8 + (i*48));
			}
		glEnd();
	}

	public void pollCombatInput(Boss b) 
	{
		if(isKeyDown(KEY_W))
		{
			if(damageDealed < 9)
			{
				b.getHealthLst().set(damageDealed, false);
				damageDealed += 1;
				
				blargh = rand.nextBoolean();
				
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(blargh) {
					b.damagePlayer();
				}
			}
		}
	}

	public boolean isSelecting() {
		return isSelecting;
	}

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
	}

	public int getAcc() {
		return acc;
	}

	public void setAcc(int acc) {
		this.acc = acc;
	}

	public int getWgt() {
		return wgt;
	}

	public void setWgt(int wgt) {
		this.wgt = wgt;
	}
}
