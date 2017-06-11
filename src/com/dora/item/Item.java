package com.dora.item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Item
{
	public static enum ItemIDs 
	{
		empty, one, gun
	}
	
	public static Image[] itemImages;
	
	protected ItemIDs id;
	
	private boolean canPickUp;
	
	protected int xPos, yPos; //absolute positions (when dropped)
	
	public Item() 
	{
		xPos = 0;
		yPos = 0;
		
		canPickUp = false;
	}
	
	public int getX()
	{
		return xPos;
	}
	
	public void setX(int xPos)
	{
		this.xPos = xPos;
	}

	public int getY()
	{
		return yPos;
	}

	public void setY(int yPos)
	{
		this.yPos = yPos;
	}

	public static void loadImages(String filePath, int squareSize, int number)
	{
		itemImages = new Image[number];
		
		Image sSImage = null;
		
		try
		{
			sSImage = new Image(filePath);
		}catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		SpriteSheet sS = new SpriteSheet(sSImage, squareSize, squareSize);
		
		for(int j = 0; j < Math.sqrt(number); j++)
		{
			for(int i = 0; i < Math.sqrt(number); i++)
			{
				itemImages[(int) (j*Math.sqrt(number) + i)] = sS.getSubImage(i, j);
			}
		}
	}
	
	public boolean canPickUp()
	{
		return canPickUp;
	}

	public void setCanPickUp(boolean canPickUp)
	{
		this.canPickUp = canPickUp;
	}
	
	public void onEquip() {}
	
	public void onLeftClick() {}

	public ItemIDs getId()
	{
		return this.id;
	}
}
