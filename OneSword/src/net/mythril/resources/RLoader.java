package net.mythril.resources;
 
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class RLoader 
{
	Texture tex;

	public Texture loadTexFrom(String path) throws IOException
	{
		if(path == null) {
			tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("rsc/spr/default_texture.png"));
		} else {
			tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
		}
		
		return tex;
	}
}
