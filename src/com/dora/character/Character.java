package com.dora.character;

import com.dora.character.Character.MoveDirection;
import com.dora.item.Item;

public class Character
{
	protected float health;
	protected int xPos, yPos; // absolute space not scren space coordinates
	protected float speed;
	
	protected MoveDirection moveDirection;
	
	public enum MoveDirection {
		left, right, up, down, stationary
	}
	
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
	
	protected float radToDeg(float rad)
	{
		return (float) ((rad*180)/Math.PI);
	}
}
