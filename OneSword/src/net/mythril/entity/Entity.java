package net.mythril.entity;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.input.Keyboard.*;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import net.mythril.player.Camera;
import net.mythril.player.Player;
import net.mythril.resources.RLoader;

public class Entity 
{
	float x,y,width,height;
	
	protected Texture genericTex;
	
	RLoader resourceloader = new RLoader();
	
	Texture health;
	
	ArrayList<Boolean> healthLst = new ArrayList<>();
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Entity(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
	 	this.height = height;
	}

	public Texture getTex() {
		try {
			if(genericTex == null) 
			{
				genericTex = resourceloader.loadTexFrom(null);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return genericTex;
	}

	public void setTex(String dir) throws IOException {
		genericTex = resourceloader.loadTexFrom(dir);
	}
	
	public void render()
	{
		getTex().bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(getX(),getY());
			glTexCoord2f(1,0);
			glVertex2f(getX()+getTex().getImageWidth(),getY());
			glTexCoord2f(1,1);
			glVertex2f(getX()+getTex().getTextureWidth(),getY()+getTex().getTextureHeight());
			glTexCoord2f(0,1);
			glVertex2f(getX(),getY()+getTex().getImageHeight());
		glEnd();
	}
	
	public void collide(Entity e)
	{
		//collision of this entity on this entity's left side
		if(getX() <= e.getX() + e.getWidth() && getY() >= e.getY() && getY() + getWidth() <= e.getY() + e.getWidth() && getX() > e.getX())
		{
			setX(e.getX() + e.getWidth());
		}
		
		//collision of this entity on this entity's right side
		else if(getX() + getWidth() >= e.getX() && getY() >= e.getY() && getY() + getWidth() <= e.getY() + e.getWidth() && getX() < e.getX() + getWidth())
		{
			setX(e.getX() - e.getWidth());
		}
	}
	
	public void translate(Camera cam, Player p)
	{
		if(isKeyDown(KEY_A) && p.getX() < -cam.getOffsetX() + 192)
		{
			setX(getX() + cam.getCamSpd());
		}
		
		if(isKeyDown(KEY_D) && p.getX() > -cam.getOffsetX() + 544)
		{
			setX(getX() - cam.getCamSpd());
		}
		
		if(p.isJumping()) {
			setY(getY() + p.getjSpd());
		} else {
			setY(getY() - p.getjSpd());
		}
	}
	
	public void loadHealth()
	{
		try {
			health = resourceloader.loadTexFrom("rsc/spr/health.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < 9; i++)
		{
			healthLst.add(true);
		}
	}
	
	public void renderHealth()
	{
		health.bind();
		
		glBegin(GL_QUADS);
			for(int i = 0; i < 9; i++)
			{
				if(healthLst.get(i))
				{
					glTexCoord2f(0,0);
					glVertex2f(getX() + (16*i) - 32,getY() - 24);
					glTexCoord2f(1,0);
					glVertex2f(getX() + (16*i) - 20,getY() - 24);
					glTexCoord2f(1,1);
					glVertex2f(getX() + (16*i) - 20,getY() - 8);
					glTexCoord2f(0,1);
					glVertex2f(getX() + (16*i) - 32,getY() - 8);
				}
			}
		glEnd();
	}

	public ArrayList<Boolean> getHealthLst() {
		return healthLst;
	}
}
