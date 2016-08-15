package net.mythril.main;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

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
			render();
			
			if(Display.isCloseRequested())
			{
				e.getTex().release();
				p.getTex().release();
				
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
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		try {
			p.setTex("rsc/spr/placeholder.png");
			e.setTex("rsc/spr/dirt_top.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("OpenGL version: " + glGetString(GL_VERSION));
	}
	
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		
		pi.pollInput(p);
		
		p.render();
		
		e.render();
		
		
			
		glBindTexture(GL_TEXTURE_2D, 0);
		
		Display.sync(60);
		Display.update();
	}
	
	public static void main(String[] args)
	{
		new Main().start();
	}
}
