package net.mythril.entity;

import java.util.ArrayList;

import net.mythril.entity.stageselector.Platform;

public class EntityLoader 
{
	ArrayList<Platform> platLst = new ArrayList<>();
	Entity e;
	
	public void load()
	{
		platLst.add(new Platform(300,300,64,64));
		platLst.add(new Platform(400,300,64,64));
	}
	
	public Entity getEntity(int i)
	{
		return platLst.get(i);
	}

	public ArrayList<Platform> getPlatLst() {
		return platLst;
	}

	public void setEnLst(ArrayList<Platform> platLst) {
		this.platLst = platLst;
	}
}
