package net.mythril.stage;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;

import net.mythril.player.Camera;
import net.mythril.resources.RLoader;

public class GUI 
{
	Camera cam;
	RLoader resourceloader = new RLoader();
	Texture guiTex;
	
	int guiX = 32, guiY = 32;
	public void init(Camera cam)
	{
		this.cam = cam;
		try {
			guiTex = resourceloader.loadTexFrom("rsc/spr/default_gui.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render()
	{
		guiTex.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(guiX, guiY);
			glTexCoord2f(1,0);
			glVertex2f(guiX + guiTex.getTextureWidth(), guiY);
			glTexCoord2f(1,1);
			glVertex2f(guiX + guiTex.getTextureWidth(), guiY + guiTex.getTextureHeight());
			glTexCoord2f(0,1);
			glVertex2f(guiX, guiY + guiTex.getTextureHeight());
		glEnd();
	}

	public int getGuiX() {
		return guiX;
	}

	public void setGuiX(int guiX) {
		this.guiX = guiX;
	}

	public int getGuiY() {
		return guiY;
	}

	public void setGuiY(int guiY) {
		this.guiY = guiY;
	}
}
