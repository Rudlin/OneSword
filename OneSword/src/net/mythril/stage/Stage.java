package net.mythril.stage;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import net.mythril.resources.RLoader;

public class Stage 
{
	RLoader resourceloader = new RLoader();
	ArrayList<Texture> flrLst = new ArrayList<>();
	Texture flr, walls, bg;
	public void loadAll()
	{
		loadFloor(null);
		loadWalls(null);
		loadBackground(null);
	}
	
	public void renderAll()
	{
		renderBackground();
		renderFloor();
		renderWalls();
	}
	
	public void loadFloor(String dir) 
	{
		try {
			flr = resourceloader.loadTexFrom(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadWalls(String dir)
	{
		try {
			walls = resourceloader.loadTexFrom(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadBackground(String dir)
	{
		try {
			bg = resourceloader.loadTexFrom(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void renderFloor()
	{
		for(int i = 0; i < 22; i++)
		{
			flr.bind();
			
			if(i < 11) {
				glBegin(GL_QUADS);
					glTexCoord2f(0,0);
					glVertex2f((64 * i) + 64, 472);
					glTexCoord2f(1,0);
					glVertex2f((64 * i) + flr.getTextureWidth() + 64, 472);
					glTexCoord2f(1,1);
					glVertex2f((64 * i) + flr.getTextureWidth() + 64, 472 + flr.getTextureHeight());
					glTexCoord2f(0,1);
					glVertex2f((64 * i) + 64, 472 + flr.getTextureHeight());
				glEnd();
			} else {
				glBegin(GL_QUADS);
					glTexCoord2f(0,0);
					glVertex2f((64 * (i - 11)) + 64,536);
					glTexCoord2f(1,0);
					glVertex2f((64 * (i - 11)) + flr.getTextureWidth() + 64,536);
					glTexCoord2f(1,1);
					glVertex2f((64 * (i - 11)) + flr.getTextureWidth() + 64,536 + flr.getTextureHeight());
					glTexCoord2f(0,1);
					glVertex2f(64 * (i - 11) + 64,536 + flr.getTextureHeight());
				glEnd();
			}
		}
	}
	
	public void renderWalls()
	{
		for(int i = 0; i < 21; i++)
		{
			if(i < 11) {
				glBegin(GL_QUADS);
					glTexCoord2f(0,0);
					glVertex2f(0,(64 * i));
					glTexCoord2f(1,0);
					glVertex2f(64,(64 * i));
					glTexCoord2f(1,1);
					glVertex2f(64,(64 * i) + 64);
					glTexCoord2f(0,1);
					glVertex2f(0,(64 * i) + 64);
				glEnd();
			} else {
				glBegin(GL_QUADS);
					glTexCoord2f(0,0);
					glVertex2f(736,(64 * (i - 11)));
					glTexCoord2f(1,0);
					glVertex2f(800,(64 * (i - 11)));
					glTexCoord2f(1,1);
					glVertex2f(800,(64 * (i - 11)) + 64);
					glTexCoord2f(0,1);
					glVertex2f(736,(64 * (i - 11)) + 64);
				glEnd();
			}
		}
	}
	
	public void renderBackground()
	{
		for(int i = 0; i < 130; i++)
		{
			if((i/13) <= 1.0f)
			{
				
			}
		}
	}

}
