package net.mythril.main;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import net.mythril.enemy.Enemy1;
import net.mythril.player.Player;
import net.mythril.player.PlayerInput;

public class Main 
{
	final static int WIDTH = 800;
	final static int HEIGHT = 600;
	
	Player p = new Player(100,300,64,64);
	Enemy1 e = new Enemy1(300,300,64,64);
	
	PlayerInput pi = new PlayerInput();
	
	public void start()
	{
		init();
		
		while(true)
		{
			glClear(GL_COLOR_BUFFER_BIT);
			
			render();
			
			Display.update();
			Display.sync(60);
			
			if(Display.isCloseRequested())
			{
				Display.destroy();
				System.exit(0);
			}
		}
	}
	
	public void init()
	{
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.setTitle("OneSword Pre-Alpha");
			
			Display.create();
		} catch (LWJGLException e) {
			System.out.println("Failed to create display.");
			e.printStackTrace();
		}
		
		glEnable(GL_TEXTURE_2D);
		glClearColor(0.0f,0.0f,0.0f,0.0f);
		
			//enables alpha blending
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			
			glViewport(0,0,WIDTH,HEIGHT);
		glMatrixMode(GL_MODELVIEW);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		
		try {
			p.setTex("rsc/spr/placeholder.png");
			e.setTex("rsc/spr/dirt_top.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render()
	{
		pi.pollInput(p);
		p.getTex().bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(p.getX(),p.getY());
			glTexCoord2f(1,0);
			glVertex2f(p.getX()+p.getTex().getImageWidth(),p.getY());
			glTexCoord2f(1,1);
			glVertex2f(p.getX()+p.getTex().getTextureWidth(),p.getY()+p.getTex().getTextureHeight());
			glTexCoord2f(0,1);
			glVertex2f(p.getX(),p.getY()+p.getTex().getImageHeight());
			
			p.getTex().release();
			e.getTex().bind();
			
			glTexCoord2f(0,0);
			glVertex2f(e.getX(),e.getY());
			glTexCoord2f(1,0);
			glVertex2f(e.getX()+e.getTex().getImageWidth(),e.getY());
			glTexCoord2f(1,1);
			glVertex2f(e.getX()+e.getTex().getTextureWidth(),e.getY()+e.getTex().getTextureHeight());
			glTexCoord2f(0,1);
			glVertex2f(e.getX(),e.getY()+e.getTex().getImageHeight());
			
			e.getTex().release();
		glEnd();
	}
	
	public static void main(String[] args)
	{
		new Main().start();
	}
}
