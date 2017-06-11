package com.dora.character;

import com.dora.item.Item;

public class Character
{
	protected float health;
	protected int xPos, yPos; // absolute space not scren space coordinates
	protected float speed;
	
	protected Item[] inventory;
	
	public Item[] getInventory()
	{
		return this.inventory;
	}

	public int getX()
	{
		return xPos;
	}

	public int getY()
	{
		return yPos;
	}
}
