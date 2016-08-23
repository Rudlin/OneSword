package net.mythril.resources;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;

public class DefaultTex 
{
	Texture DefaultTex;
	RLoader resourceloader = new RLoader();
	public Texture load()
	{
		try {
			DefaultTex = resourceloader.loadTexFrom("rsc/spr/default_texture.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return DefaultTex;
	}
}
