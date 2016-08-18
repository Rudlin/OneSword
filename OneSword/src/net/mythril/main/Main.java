package net.mythril.main;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import net.mythril.entity.Entity;
import net.mythril.entity.EntityLoader;
import net.mythril.player.Player;

public class Main 
{
	final static int WIDTH = 800;
	final static int HEIGHT = 600;
	
	Player p = new Player(100,236,64,64);
	EntityLoader eLoader = new EntityLoader();
	Entity ent;
	
	public void start()
	{
		init();
		
		while(true)
		{
			render();
			
			if(Display.isCloseRequested())
			{
				ent.getTex().release();
				p.getTex().release();
				
				Display.destroy();
				System.exit(0);
			}
		}
	}
	
	public void init()
	{
		//Loads entities
		eLoader.load();
		
		for(int i = 0; i < eLoader.getEnemyLst().size(); i++)
		{
			ent = eLoader.getEntity(i);
		}
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
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity(); //Clears projection matrix
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1); //sets OpenGL context
		glMatrixMode(GL_MODELVIEW);
		
		try {
			//Set entity textures//
			p.setTex("rsc/spr/placeholder.png");
			ent.setTex("rsc/spr/dirt_top.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("OpenGL version: " + glGetString(GL_VERSION));
	}
	
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT); //clears the screen
		
		p.pollInput(ent);
		
		p.collide(ent);
		
		p.render();
		
		ent.render();
		
		Display.sync(60); //sets game fps to 60
		Display.update(); //Updates screen. Duh.
	}
	
	public static void main(String[] args)
	{
		new Main().start();
	}
}
