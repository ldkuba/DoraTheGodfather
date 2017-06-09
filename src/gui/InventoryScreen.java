package gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

import item.EmptyItem;
import item.Item;
import main.GameState;
import main.Globals;
import main.Main;
import util.Vector2;

public class InventoryScreen implements MouseListener
{
	private GameContainer gc;
	private Main app;
	private GameState gameState;
	
	private GuiManager guiManager;
	private ItemGrid itemGrid;
	private ItemGrid hotbarGrid;
	
	private Item pickedItem;
	
	private int gridSize = 64;
	private int horizontalSize = 10;
	private int verticalSize = 8;
	
	private int itemGridX;
	private int itemGridY;
	
	private int hotbarGridX;
	private int hotbarGridY;
	
	private boolean visible;
	
	public InventoryScreen(GuiManager guiManager, GameContainer gc, Main app, GameState gameState)
	{
		this.gc = gc;
		this.app = app;
		this.gameState = gameState;
		
		this.guiManager = guiManager;
		this.visible = false;
		
		this.pickedItem = new EmptyItem();
		
		this.itemGridX = Globals.SCREEN_WIDTH/2 - (horizontalSize/2)*gridSize;
		this.itemGridY = (int) (Globals.SCREEN_HEIGHT*0.15f);
				
		this.hotbarGridX =  Globals.SCREEN_WIDTH/2 - (horizontalSize/2)*gridSize;
		this.hotbarGridY = (int) (Globals.SCREEN_HEIGHT*0.8f);
		
		itemGrid = new ItemGrid("InventoryGrid", itemGridX, itemGridY, gridSize, gc, app, horizontalSize, verticalSize, "res/gui/slot.png");
		hotbarGrid = new ItemGrid("InventoryHotbarGrid", hotbarGridX, hotbarGridY, gridSize, gc, app, 10, 1, "res/gui/slot.png");
		
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
		}
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
					
				}else if(hotbarGrid.getCoordinatesFromScreenSpace(x, y) != null)
				{
					Vector2 gridPos = hotbarGrid.getCoordinatesFromScreenSpace(x, y);
					
					this.pickedItem = hotbarGrid.getItem(gridPos.getX(), gridPos.getY());
					
					if(pickedItem.getId().compareTo(Item.ItemIDs.empty) == 0)
					{
						return;
					}
					
					hotbarGrid.removeItem(gridPos.getX(), gridPos.getY());
					
					this.gameState.getHotbar().removeItem(gridPos.getX());
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

				}else
				{
					//dropItem (TODO)
					
					this.pickedItem = new EmptyItem();
					
				}
			}
		}
	}
	
	public void mouseWheelMoved(int arg0) {}
	
}
