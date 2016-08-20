package net.mythril.resources;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class RLoader 
{
	public Texture loadTexFrom(String path) throws IOException
	{
		return TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
	}
}
