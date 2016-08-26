package net.mythril.main;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import static org.lwjgl.input.Keyboard.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import net.mythril.entity.EntityLoader;
import net.mythril.player.Camera;
import net.mythril.player.Player;
import net.mythril.resources.Config;
import net.mythril.stage.GUI;
import net.mythril.stage.Stage;

public class Main 
{
	Config c = new Config();
	public int WIDTH, HEIGHT;
	
	Player p = new Player(320,36,64,64);
	EntityLoader eLoader = new EntityLoader();
	
	//determines what part of the game is being played
	boolean isSelecting = false;
	
	Camera cam = new Camera();
	
	GUI gui = new GUI();
	
	Stage stage = new Stage();
	
	public void start()
	{
		init();
		
		while(true)
		{
			render();
			
			if(Display.isCloseRequested())
			{
				for(int i = 0; i < eLoader.getPlatLst().size(); i++)
				{
					eLoader.getEntity(i).getTex().release();
				}
				p.getTex().release();
				stage.releaseAll();
				Display.destroy();
				System.exit(0);
			}
		}
	}
	
	public void init()
	{
		c.load();
		
		WIDTH = c.getWidth();
		HEIGHT = c.getHeight();
		
		//Loads entities
		eLoader.load();
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.setTitle("OneSword Pre-Alpha");
			Display.setVSyncEnabled(c.isSyncEnabled());
			
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
		
		gui.init(cam);
		
		try {
			//Set entity textures//
			p.setTex("rsc/spr/placeholder.png");
			
			for(int i = 0; i < eLoader.getPlatLst().size(); i++)
			{
				eLoader.getEntity(i).setTex("rsc/spr/dirt_top.png");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		stage.loadAll();
		
		System.out.println("OpenGL version: " + glGetString(GL_VERSION));
	}
	
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT); //clears the screen
		
		if(isSelecting) {
			p.pollInput();
			
			for(int i = 0; i < eLoader.getPlatLst().size(); i++)
			{
				p.pCollide(eLoader.getEntity(i));
			}
			
			
			for(int i = 0; i < eLoader.getPlatLst().size(); i++)
			{
				p.collide(eLoader.getEntity(i));
			}
			
			cam.followPlayer(p);
			
			p.render();
			
			for(int i = 0; i < eLoader.getPlatLst().size(); i++)
			{
				eLoader.getEntity(i).translate(cam, p);
				eLoader.getEntity(i).render();
			}
			
			gui.translate(cam);
		} else {
			p.pollCombatInput();
			
			stage.renderAll();
		}
		
		if(isKeyDown(KEY_SPACE))
		{
			gui.render();
		}
		
		if(!isKeyDown(KEY_P)) {
			Display.sync(60); //sets game fps to 60
		} else {
			Display.sync(15);
		}
		Display.update(); //Updates screen. Duh.
	}
	
	public static void main(String[] args)
	{
		new Main().start();
	}
}
