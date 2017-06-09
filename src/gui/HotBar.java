package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import item.Item;
import main.Main;

public class HotBar extends GuiElement
{

	private int x, y;
	private int height;
	
	private int slotWidth;
	private int slotNumber;
	
	private Image slotImage;
	private Item.ItemIDs[] itemImages;
	
	private GameContainer gc;
	private Main app;
	
	public HotBar(String name, float x, float y, int slotW, int h, GameContainer gc, Main app, int slotNumber, String slotImagePath)
	{
		super(name);
		this.x = (int) x;
		this.y = (int) y;
		this.height = h;
		this.slotWidth = slotW;
		this.gc = gc;
		this.app = app;
		
		this.slotNumber = slotNumber;
		
		try
		{
			slotImage = new Image(slotImagePath);
		}catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		itemImages = new Item.ItemIDs[slotNumber];
		for(int i = 0; i < slotNumber; i++)
		{
			itemImages[i] = Item.ItemIDs.empty;
		}		
	}
	
	public void draw(GameContainer gc, Graphics g)
	{
		for(int i = 0; i < slotNumber; i++)
		{
			slotImage.draw(x + i*slotWidth, y, slotWidth, height);
			Item.itemImages[itemImages[i].ordinal()].draw(x + i*slotWidth, y, slotWidth, height);
		}
	}
}
