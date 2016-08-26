package net.mythril.stage;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import net.mythril.enemy.Boss;
import net.mythril.player.Player;
import net.mythril.resources.RLoader;

public class Stage 
{
	RLoader resourceloader = new RLoader();
	ArrayList<Texture> flrLst = new ArrayList<>();
	Texture flr, walls, bg;
	Player p = new Player(160, 408, 64, 64);
	Boss b = new Boss(500, 344, 128, 128);
	public void loadAll()
	{
		loadFloor(null);
		loadWalls(null);
		loadBackground("rsc/spr/default_texture_background.png");
		loadPlayer("rsc/spr/placeholder.png");
		loadBoss("rsc/spr/default_texture_128.png");
	}
	
	public void renderAll()
	{
		renderBackground();
		renderEntities();
		renderFloor();
		renderWalls();
	}
	
	public void releaseAll()
	{
		flr.release();
		walls.release();
		bg.release();
		p.getTex().release();
		b.getTex().release();
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
	
	public void loadPlayer(String dir)
	{
		try {
			p.setTex(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadBoss(String dir)
	{
		try {
			b.setTex(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void renderFloor()
	{
		flr.bind();
		for(int i = 0; i < 22; i++)
		{
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
		walls.bind();
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
		bg.bind();
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < 13; i++)
			{
				glBegin(GL_QUADS);
					glTexCoord2f(0,0);
					glVertex2f(64*i,64*j);
					glTexCoord2f(1,0);
					glVertex2f((64*i) + 64,64*j);
					glTexCoord2f(1,1);
					glVertex2f((64*i)+64,(64*j) + 64);
					glTexCoord2f(0,1);
					glVertex2f(64*i,(64*j) + 64);
				glEnd();
			}
		}
	}
	
	public void renderEntities()
	{
		p.render();
		b.render();
	}

}
