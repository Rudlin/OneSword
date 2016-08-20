package net.mythril.player;
import static org.lwjgl.input.Keyboard.*;
public class Camera 
{
	int offsetX, offsetY, camSpd = 2;
	public void followPlayer(Player p)
	{
		if(isKeyDown(KEY_A) && p.getX() < -offsetX + 192)
		{
			offsetX += camSpd;
		}
		
		if(isKeyDown(KEY_D) && p.getX() > -offsetX + 544)
		{
			offsetX -= camSpd;
		}
	}
	public int getCamSpd() {
		return camSpd;
	}
	public int getOffsetX() {
		return offsetX;
	}
	public int getOffsetY() {
		return offsetY;
	}
}
