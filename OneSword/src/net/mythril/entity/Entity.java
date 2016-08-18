package net.mythril.entity;

import static org.lwjgl.opengl.GL11.*;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Entity 
{
	float x,y,width,height;
	
	protected Texture genericTex;
	
	boolean displayGenTex = true;
	
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
		return genericTex;
	}

	public void setTex(String dir) throws IOException {
		genericTex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(dir));
		displayGenTex = false;
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
}
