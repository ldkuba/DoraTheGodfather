package com.dora.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.dora.item.EmptyItem;
import com.dora.item.Item;
import com.dora.main.Main;
import com.dora.util.Vector2;

public class ItemGrid extends GuiElement
{
	private int x, y;
	private int slotSize;
	private int verticalSlotNumber, horizontalSlotNumber;
	
	private Image slotImage;
	private Item[][] items;
	
	private GameContainer gc;
	private Main app;
	
	public ItemGrid(String name, float x, float y, int slotSize, GameContainer gc, Main app, int horizontalSlotNumber, int verticalSlotNumber, String slotImagePath)
	{
		super(name);
		this.x = (int) x;
		this.y = (int) y;
		this.slotSize = slotSize;
		this.gc = gc;
		this.app = app;
		
		this.horizontalSlotNumber = horizontalSlotNumber;
		this.verticalSlotNumber = verticalSlotNumber;
		
		
		
		try
		{
			slotImage = new Image(slotImagePath);
		}catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		//Init empty grid
		items = new Item[horizontalSlotNumber][verticalSlotNumber];
		for(int j = 0; j < verticalSlotNumber; j++)
		{
			for(int i = 0; i < horizontalSlotNumber; i++)
			{
				items[i][j] = new EmptyItem();
			}
		}
	}
	
	public void draw(GameContainer gc, Graphics g)
	{
		for(int j = 0; j < verticalSlotNumber; j++)
		{
			for(int i = 0; i < horizontalSlotNumber; i++)
			{
				slotImage.draw(x + i*slotSize, y + j*slotSize, slotSize, slotSize);
				Item.itemImages[items[i][j].getId().ordinal()].draw(x + i*slotSize, y + j*slotSize, slotSize, slotSize);
			}
		}
	}
	
	public void addItem(Item item)
	{
		if(isFull())
			return;
		
		for(int j = 0; j < verticalSlotNumber; j++)
		{
			for(int i = 0; i < horizontalSlotNumber; i++)
			{
				if(items[i][j].getId().compareTo(Item.ItemIDs.empty) == 0)
				{
					items[i][j] = item;
					return;
				}
			}
		}
	}
	
	public Item addItem(Item item, int x, int y)
	{		
		Item tmp = items[x][y];	
		items[x][y] = item;
		return tmp;
	}
	
	public void removeItem(int x, int y)
	{
		if(x > horizontalSlotNumber || x < 0 || y > verticalSlotNumber || y < 0)
			return;

		items[x][y] = new EmptyItem();
	}
	
	public Item getItem(int x, int y)
	{
		if(x < 0 || x > this.horizontalSlotNumber || y < 0 || y > this.verticalSlotNumber)
			return null;
		
		return items[x][y];
	}
	
	public boolean isFull()
	{

		for(int j = 0; j < verticalSlotNumber; j++)
		{
			for(int i = 0; i < horizontalSlotNumber; i++)
			{
				if(items[i][j].getId().compareTo(Item.ItemIDs.empty) == 0)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public Vector2 getCoordinatesFromScreenSpace(int x, int y)
	{
		if(x < this.x || x > this.x + this.horizontalSlotNumber*this.slotSize || y < this.y || y > this.y + this.verticalSlotNumber*this.slotSize)
			return null;
		
		int translatedX = x - this.x;
		int translatedY = y - this.y;
		
		int gridX = translatedX/this.slotSize;
		int gridY = translatedY/this.slotSize;
		
		return new Vector2(gridX, gridY);
	}

	public void fillWith(Item[] newItems)
	{
		for(int j = 0; j < verticalSlotNumber; j++)
		{
			for(int i = 0; i < horizontalSlotNumber; i++)
			{
				if(i*j < newItems.length)
				{
					items[i][j] = newItems[i*j];
				}else
				{
					items[i][j] = new EmptyItem();
				}
			}
		}
	}

	public void removeAll()
	{
		for(int j = 0; j < verticalSlotNumber; j++)
		{
			for(int i = 0; i < horizontalSlotNumber; i++)
			{
				items[i][j] = new EmptyItem();
			}
		}
	}
}
