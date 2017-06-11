package com.dora.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.dora.item.EmptyItem;
import com.dora.item.Item;
import com.dora.main.GameState;
import com.dora.main.Main;

public class HotBar extends GuiElement
{

	private int x, y;
	private int height;
	
	private int slotWidth;
	private int slotNumber;
	
	private Image slotImage;
	private Image selectorImage;
	private int selectedId;
	private Item[] items;
	
	private GameContainer gc;
	private Main app;
	
	private GameState gameState;
	
	public HotBar(String name, float x, float y, int slotW, int h, GameContainer gc, Main app, int slotNumber, String slotImagePath, String selectorImagePath, GameState gs)
	{
		super(name);
		this.x = (int) x;
		this.y = (int) y;
		this.height = h;
		this.slotWidth = slotW;
		this.gc = gc;
		this.app = app;
		
		this.gameState = gs; 
		
		this.selectedId = 0;
		this.slotNumber = slotNumber;
		
		try
		{
			slotImage = new Image(slotImagePath);
		}catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			this.selectorImage = new Image(selectorImagePath);
		}catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		items = new Item[slotNumber];
		for(int i = 0; i < slotNumber; i++)
		{
			items[i] = new EmptyItem(); //- EmptySlot
		}		
	}
	
	public void addItem(int x, Item item)
	{
		this.items[x] = item;
	}
	
	public void removeItem(int x)
	{
		this.items[x] = new EmptyItem();
	}
	
	public void draw(GameContainer gc, Graphics g)
	{
		for(int i = 0; i < slotNumber; i++)
		{
			slotImage.draw(x + i*slotWidth, y, slotWidth, height);
			Item.itemImages[items[i].getId().ordinal()].draw(x + i*slotWidth, y, slotWidth, height);
			
			if(selectedId == i)
			{
				selectorImage.draw(x + i*slotWidth, y);
			}
		}
	}
	
	public void selectItem(int newId)
	{
		this.gameState.setCursor(GameState.cursors.standard);
		this.selectedId = newId;
		items[selectedId].onEquip();
	}
}
