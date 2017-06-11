package com.dora.character;

import com.dora.character.Character.MoveDirection;
import com.dora.main.GameState;
import com.dora.main.Globals;

public class NPC extends Character
{
	public enum NPCType {
		hunter, brute, boss
	}
	
	protected GameState gameState;
	
	private int imageSize;
	
	protected NPCType type;
	
	public NPC(GameState gs, NPCType type, int x, int y)
	{
		super();
		
		this.xPos = x;
		this.yPos = y;
		
		this.type = type;
		this.gameState = gs;
		
		this.moveDirection = MoveDirection.stationary;
	}
	
	public boolean checkMoveX(int deltaX, int xPos, int yPos, int size)
	{	
		if(deltaX > 0)
		{
			int tileX = (xPos + deltaX + size) / Globals.TILE_SIZE;
			int tileY = yPos / Globals.TILE_SIZE;
			
			if(tileX < 0 || tileX >= Globals.WORLD_SIZE_X)
				return false;
			
			if(yPos > (tileY*Globals.TILE_SIZE))
			{
				if(!gameState.getWorld().isPassable(tileX, tileY) || !gameState.getWorld().isPassable(tileX, tileY+1))
				{
					return false;
				}
			}else
			{
				if(!gameState.getWorld().isPassable(tileX, tileY))
				{
					return false;
				}
			}
		}else if(deltaX < 0)
		{
			int tileX = (xPos - deltaX) / Globals.TILE_SIZE;
			int tileY = yPos / Globals.TILE_SIZE;
			
			if(tileX < 0 || tileX >= Globals.WORLD_SIZE_X)
				return false;
			
			if(yPos > tileY*Globals.TILE_SIZE)
			{
				if(!gameState.getWorld().isPassable(tileX, tileY) || !gameState.getWorld().isPassable(tileX, tileY+1))
				{
					return false;
				}
			}else
			{
				if(!gameState.getWorld().isPassable(tileX, tileY))
				{
					return false;
				}
			}
		}
			
		return true;
	}
	
	public boolean checkMoveY(int deltaY, int xPos, int yPos, int size)
	{
		if(deltaY > 0)
		{
			int tileX = (xPos) / Globals.TILE_SIZE;
			int tileY = (yPos + deltaY + size) / Globals.TILE_SIZE;
			
			if(tileY < 0 || tileY >= Globals.WORLD_SIZE_X)
				return false;
			
			if(xPos > tileX*Globals.TILE_SIZE)
			{
				if(!gameState.getWorld().isPassable(tileX, tileY) || !gameState.getWorld().isPassable(tileX+1, tileY))
				{
					return false;
				}
			}else
			{
				if(!gameState.getWorld().isPassable(tileX, tileY))
				{
					return false;
				}
			}
		}else if(deltaY < 0)
		{
			int tileX = xPos / Globals.TILE_SIZE;
			int tileY = (yPos - deltaY) / Globals.TILE_SIZE;		
			
			if(tileY < 0 || tileY >= Globals.WORLD_SIZE_X)
				return false;
			
			if(xPos > tileX*Globals.TILE_SIZE)
			{
				if(!gameState.getWorld().isPassable(tileX, tileY) || !gameState.getWorld().isPassable(tileX+1, tileY))
				{
					return false;
				}
			}else
			{
				if(!gameState.getWorld().isPassable(tileX, tileY))
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public int getSize()
	{
		return this.imageSize;
	}
	
	public void dealDamage(float dmg)
	{
		this.health -= dmg;
		
		if(health <= 0)
		{			
			for(int i = 0; i < this.inventory.length; i++)
			{
				this.gameState.getItemManager().addDroppedItem(inventory[i], this.xPos, this.yPos);
			}
			
			gameState.getNPCManager().removeNPC(this);
		}
	}
	
	public void update(float tpf) {}
	
	public void render(int xOffset, int yOffset) {}
}
