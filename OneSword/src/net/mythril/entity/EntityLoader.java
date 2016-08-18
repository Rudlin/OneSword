package net.mythril.entity;

import java.util.ArrayList;

import net.mythril.enemy.Enemy;

public class EntityLoader 
{
	ArrayList<Entity> enLst = new ArrayList<>();
	Entity e;
	
	public void load()
	{
		enLst.add(new Enemy(300,300,64,64));
	}
	
	public Entity getEntity(int i)
	{
		return enLst.get(i);
	}

	public ArrayList<Entity> getEnemyLst() {
		return enLst;
	}

	public void setEnLst(ArrayList<Entity> enLst) {
		this.enLst = enLst;
	}
}
