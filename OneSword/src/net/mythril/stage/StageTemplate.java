package net.mythril.stage;

import org.newdawn.slick.opengl.Texture;

import net.mythril.resources.DefaultTex;

public class StageTemplate 
{
	DefaultTex dtex = new DefaultTex();
	public void initAll()
	{
		loadFloor();
	}
	
	public Texture defaultTexture(Texture tex)
	{
		if(tex == null)
		{
			tex = dtex.load();
		}
		
		return tex;
	}
	
	public void loadFloor() 
	{
		
	}

}
