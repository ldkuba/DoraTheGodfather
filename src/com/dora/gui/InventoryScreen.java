package com.dora.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;

import com.dora.item.EmptyItem;
import com.dora.item.Item;
import com.dora.main.GameState;
import com.dora.main.Globals;
import com.dora.main.Main;
import com.dora.util.Vector2;

public class InventoryScreen implements MouseListener
{
	private GameContainer gc;
	private Main app;
	private GameState gameState;
	
	private GuiManager guiManager;
	private ItemGrid itemGrid;
	private ItemGrid hotbarGrid;
	private ItemGrid containerGrid;
	
	private boolean hasContainer = false;
	
	private Image noContainerImage;
	private int noContainerImageX;
	private int noContainerImageY;
	
	private Item pickedItem;
	
	private int gridSize = 64;
	private int horizontalItemSize = 6;
	private int horizontalHotbarSize = 10;
	private int verticalSize = 8;
	
	private int itemGridX;
	private int itemGridY;
	
	private int hotbarGridX;
	private int hotbarGridY;
	
	private int containerGridX;
	private int containerGridY;
	
	private boolean visible;
	
	public InventoryScreen(GuiManager guiManager, GameContainer gc, Main app, GameState gameState)
	{
		this.gc = gc;
		this.app = app;
		this.gameState = gameState;
		
		this.guiManager = guiManager;
		this.visible = false;
		
		this.pickedItem = new EmptyItem();
		
		this.itemGridX = Globals.SCREEN_WIDTH/2 - ((horizontalHotbarSize+4)/2)*gridSize; //horizontalHotbarSize +4 - because we want space for container inventory
		this.itemGridY = (int) (Globals.SCREEN_HEIGHT*0.15f);
				
		this.hotbarGridX =  Globals.SCREEN_WIDTH/2 - (horizontalHotbarSize/2)*gridSize;
		this.hotbarGridY = (int) (Globals.SCREEN_HEIGHT*0.8f);
		
		this.containerGridX = Globals.SCREEN_WIDTH/2 + gridSize; //horizontalHotbarSize +4 - because we want space for container inventory
		this.containerGridY = (int) (Globals.SCREEN_HEIGHT*0.15f);
		
		itemGrid = new ItemGrid("InventoryGrid", itemGridX, itemGridY, gridSize, gc, app, horizontalItemSize, verticalSize, "res/gui/slot.png");
		hotbarGrid = new ItemGrid("InventoryHotbarGrid", hotbarGridX, hotbarGridY, gridSize, gc, app, horizontalHotbarSize, 1, "res/gui/slot.png");
		containerGrid = new ItemGrid("ContainerGrid", containerGridX, containerGridY, gridSize, gc, app, horizontalItemSize, verticalSize, "res/gui/slot.png");
		
		try{
			noContainerImage = new Image("res/gui/noContainerInventory.png");
		}catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		noContainerImageX = containerGridX;
		noContainerImageY = containerGridY;
		
		gc.getInput().addMouseListener(this);
	}
	
	public void show()
	{
		this.visible = true;
		guiManager.addComponent(itemGrid);
		guiManager.addComponent(hotbarGrid);
		
	}
	
	public void hide()
	{
		this.visible = false;
		guiManager.removeComponent(itemGrid);
		guiManager.removeComponent(hotbarGrid);
		removeContainerInventory();
		
		if(pickedItem.getId().compareTo(Item.ItemIDs.empty) != 0)
		{
			//drop item:
			this.gameState.getItemManager().addDroppedItem(pickedItem, gameState.getPlayer().getX(), gameState.getPlayer().getY());
			
			pickedItem  = new EmptyItem();
		}
	}
	
	public void updatePlayerInventory(Item[] newItems)
	{
		this.itemGrid.fillWith(newItems);
	}
	
	public boolean isPlayerInventoryFull()
	{
		return itemGrid.isFull();
	}
	
	public boolean addItem(Item item) //on pickup
	{
		if(!itemGrid.isFull())
		{
			itemGrid.addItem(item);
		}else if(!hotbarGrid.isFull())
		{
			hotbarGrid.addItem(item);
			//update actual hotbar (TODO)
			
		}else
		{
			return false;
		}
		
		return true;
	}
	
	public void render()
	{
		if(visible)
		{
			Item.itemImages[this.pickedItem.getId().ordinal()].draw(gc.getInput().getMouseX() - this.gridSize/2, gc.getInput().getMouseY() - this.gridSize/2, this.gridSize, this.gridSize);
			
			if(!hasContainer)
			{
				this.noContainerImage.draw(noContainerImageX, noContainerImageY, horizontalItemSize*this.gridSize, this.verticalSize*this.gridSize);
			}
		}
	}
	
	public void addContainerInventory(Item[] items)
	{
		this.hasContainer = true;
		this.guiManager.addComponent(containerGrid);	
		containerGrid.fillWith(items);
	}
	
	public void removeContainerInventory()
	{
		this.hasContainer = false;
		this.guiManager.removeComponent(containerGrid);
		containerGrid.removeAll();
	}

	public void inputEnded() {}

	public void inputStarted() {}

	public boolean isAcceptingInput()
	{
		return visible;
	}

	public void setInput(Input input) {}

	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {}

	public void mouseDragged(int oldX, int oldY, int newX, int newY) {}

	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {}

	public void mousePressed(int button, int x, int y) 
	{
		System.out.println("MOUSE PRESSED");
		
		if(button == Input.MOUSE_LEFT_BUTTON)
		{
			if(visible)
			{
				if(itemGrid.getCoordinatesFromScreenSpace(x, y) != null)
				{
					Vector2 gridPos = itemGrid.getCoordinatesFromScreenSpace(x, y);
					
					this.pickedItem = itemGrid.getItem(gridPos.getX(), gridPos.getY());
					
					if(pickedItem.getId().compareTo(Item.ItemIDs.empty) == 0)
					{
						return;
					}

					itemGrid.removeItem(gridPos.getX(), gridPos.getY());
					gameState.getPlayer().removeItem(gridPos.getX()*gridPos.getY());
					
				}else if(hotbarGrid.getCoordinatesFromScreenSpace(x, y) != null)
				{
					Vector2 gridPos = hotbarGrid.getCoordinatesFromScreenSpace(x, y);
					
					this.pickedItem = hotbarGrid.getItem(gridPos.getX(), gridPos.getY());
					
					if(pickedItem.getId().compareTo(Item.ItemIDs.empty) == 0)
					{
						return;
					}
					
					hotbarGrid.removeItem(gridPos.getX(), gridPos.getY());
					gameState.getPlayer().removeItem(horizontalItemSize*verticalSize + gridPos.getX()*gridPos.getY());
					
					this.gameState.getHotbar().removeItem(gridPos.getX());
				}else if(containerGrid.getCoordinatesFromScreenSpace(x, y) != null && hasContainer)
				{
					Vector2 gridPos = containerGrid.getCoordinatesFromScreenSpace(x, y);
					
					this.pickedItem = containerGrid.getItem(gridPos.getX(), gridPos.getY());
					
					if(pickedItem.getId().compareTo(Item.ItemIDs.empty) == 0)
					{
						return;
					}

					containerGrid.removeItem(gridPos.getX(), gridPos.getY());
				}//else nothing happens
			}
		}
	}

	public void mouseReleased(int button, int x, int y) 
	{
		if(button == Input.MOUSE_LEFT_BUTTON)
		{
			if(visible)
			{
				if(pickedItem.getId().compareTo(Item.ItemIDs.empty) == 0)
					return;
				
				if(itemGrid.getCoordinatesFromScreenSpace(x, y) != null)
				{
					Vector2 gridPos = itemGrid.getCoordinatesFromScreenSpace(x, y);
					
					this.pickedItem = itemGrid.addItem(this.pickedItem, gridPos.getX(), gridPos.getY());
					
				}else if(hotbarGrid.getCoordinatesFromScreenSpace(x, y) != null)
				{
					Vector2 gridPos = hotbarGrid.getCoordinatesFromScreenSpace(x, y);
					
					this.gameState.getHotbar().addItem(gridPos.getX(), pickedItem);
					
					this.pickedItem = hotbarGrid.addItem(this.pickedItem, gridPos.getX(), gridPos.getY());

				}else if(containerGrid.getCoordinatesFromScreenSpace(x, y) != null && hasContainer)
				{
					Vector2 gridPos = containerGrid.getCoordinatesFromScreenSpace(x, y);
					
					this.pickedItem = containerGrid.addItem(this.pickedItem, gridPos.getX(), gridPos.getY());
				}else
				{
					//dropItem
					this.gameState.getItemManager().addDroppedItem(pickedItem, gameState.getPlayer().getX(), gameState.getPlayer().getY());
					
					this.pickedItem = new EmptyItem();
					
				}
			}
		}
	}
	
	public void mouseWheelMoved(int arg0) {}
	
}
