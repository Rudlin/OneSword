package net.mythril.main;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import net.mythril.player.Player;

public class Main 
{
	final static int WIDTH = 800;
	final static int HEIGHT = 600;
	
	Player p = new Player(100,100,64,64);
	
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
		glMatrixMode(GL_MODELVIEW);
		
		try {
			p.setTex("rsc/spr/placeholder.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render()
	{
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
		glEnd();
	}
	
	public static void main(String[] args)
	{
		new Main().start();
	}
}
