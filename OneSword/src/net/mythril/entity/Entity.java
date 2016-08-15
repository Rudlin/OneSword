package net.mythril.entity;

import static org.lwjgl.opengl.GL11.*;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Entity 
{
	int x,y,width,height;
	
	protected Texture genericTex;
	
	boolean displayGenTex = true;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
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
}
