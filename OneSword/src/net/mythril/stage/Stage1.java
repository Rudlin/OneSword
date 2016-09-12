package net.mythril.stage;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL21.*;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;

public class Stage1 extends Stage
{
	Texture stairs;
	public Stage1()
	{
		
	}

	@Override
	public void loadAll() 
	{
		loadFloor("rsc/spr/platform.png");
		loadWalls("rsc/spr/pillar_mid.png");
		loadBackground("rsc/spr/default_texture_background.png");
		loadPlayer("rsc/spr/placeholder.png");
		loadBoss("rsc/spr/default_texture_128.png");
		loadStairs("rsc/spr/stairs.png");
		loadText();
		p.loadHealth();
	}
	
	public void loadStairs(String dir)
	{
		try {
			stairs = resourceloader.loadTexFrom(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void renderStairs()
	{
		stairs.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(0,536);
			glTexCoord2f(1,0);
			glVertex2f(64,536);
			glTexCoord2f(1,1);
			glVertex2f(64,600);
			glTexCoord2f(0,1);
			glVertex2f(0,600);
			
			/*glTexCoord2f(0,0);
			glVertex2f(736,536);
			glTexCoord2f(1,0);
			glVertex2f(800,536);
			glTexCoord2f(1,1);
			glVertex2f(800,600);
			glTexCoord2f(0,1);
			glVertex2f(736,600);*/
		glEnd();
	}

	@Override
	public void renderAll() 
	{
		renderBackground();
		renderEntities();
		renderFloor();
		renderStairs();
		renderWalls();
		
		if(isDamagingPlayer)
		{
			if(!p.getHealthLst().isEmpty())
			{
				b.damagePlayer();
			}
		}
	}
}
