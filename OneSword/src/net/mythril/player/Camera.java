package net.mythril.player;
import static org.lwjgl.input.Keyboard.*;
public class Camera 
{
	int offsetX, offsetY, camSpd = 2;
	public void followPlayer(Player p)
	{
		if(isKeyDown(KEY_A) && p.getX() < 192)
		{
			//offsetX += camSpd;
			p.setX(190);
		}
		
		if(isKeyDown(KEY_D) && p.getX() > 544)
		{
			//offsetX -= camSpd;
			p.setX(546);
		}
		
		if(p.getY() > 400)
		{
			p.setY(402);
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
