package com.dora.entity;

import java.util.ArrayList;

import com.dora.main.GameState;

public class EntityManager
{
	private ArrayList<Entity> entities;
	
	private GameState gameState;
	
	public EntityManager(GameState gs)
	{
		this.gameState = gs;
		entities = new ArrayList<Entity>();		
	}
	
	public void update(float tpf)
	{
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).update(tpf);
		}
	}
	
	public void render(int xOffset, int yOffset)
	{
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).render(xOffset, yOffset);
		}
	}
	
	public void addEntity(Entity entity)
	{
		this.entities.add(entity);
	}
	
	public void removeEntity(Entity entity)
	{
		this.entities.remove(entity);
	}
}
