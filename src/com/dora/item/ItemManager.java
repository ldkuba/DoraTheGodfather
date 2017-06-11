package com.dora.item;

import java.util.ArrayList;
import java.util.Random;

import com.dora.main.GameState;
import com.dora.main.Globals;

public class ItemManager
{
	private ArrayList<Item> worldItems;
	private GameState gameState;
	
	private Random rand;
	
	private final int ITEM_WORLD_SIZE = 32;
	
	public ItemManager(GameState gs)
	{
		worldItems = new ArrayList<Item>();
		this.gameState = gs;
		
		rand = new Random();
		
	}
	
	public void update()
	{
		//check for pickups
		for(int i = 0; i < worldItems.size(); i++)
		{
			if(Math.abs(worldItems.get(i).getX() - gameState.getPlayer().getX()) < Globals.TILE_SIZE && Math.abs(worldItems.get(i).getY() - gameState.getPlayer().getY()) < Globals.TILE_SIZE)
			{
				if(worldItems.get(i).canPickUp())
				{
					worldItems.get(i).setX(0);
					worldItems.get(i).setY(0);
					gameState.getPlayer().addItem(worldItems.get(i));
					worldItems.remove(i);
				}
			}else
			{
				worldItems.get(i).setCanPickUp(true);
			}
		}
	}
	
	
	public void render(int xOffset, int yOffset)
	{
		//render dropped objects
		for(int i = 0; i < worldItems.size(); i++)
		{
			Item.itemImages[worldItems.get(i).getId().ordinal()].draw(worldItems.get(i).getX() + xOffset, worldItems.get(i).getY() + yOffset, this.ITEM_WORLD_SIZE, this.ITEM_WORLD_SIZE);
		}
	}
	
	public void addDroppedItem(Item item, int x, int y)
	{
		item.setX((int) (x + rand.nextInt(Globals.TILE_SIZE/2)*(rand.nextInt(3)-1)));
		item.setY((int) (y + rand.nextInt(Globals.TILE_SIZE/2)*(rand.nextInt(3)-1)));
		
		item.setCanPickUp(false);
		
		this.worldItems.add(item);
	}	
}
