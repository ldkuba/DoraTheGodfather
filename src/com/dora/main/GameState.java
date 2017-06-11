package com.dora.main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.dora.character.Player;
import com.dora.gui.Button;
import com.dora.gui.GuiManager;
import com.dora.gui.HotBar;
import com.dora.gui.InventoryScreen;
import com.dora.gui.MyTextField;
import com.dora.gui.ScalingBar;
import com.dora.item.Item;
import com.dora.item.Item1;
import com.dora.item.ItemManager;
import com.dora.world.World;


public class GameState extends BasicGameState implements ComponentListener
{
	//WORLD
	private int xOffset = 0;
	private int yOffset = 0;
	
	private World world;

	//PLAYER
	private Player player;
	
	//ITEMS
	private ItemManager itemManager;

	//GUI
	private HotBar itemBar;
	private InventoryScreen inventoryScreen;
	
	private boolean inMenu = false;
	private GuiManager gameGuiManager;
	private ScalingBar healthBar;

	//GENERAL
	private GameContainer gc;
	private Main app;
	
	public GameState(GameContainer gc, Main app)
	{
		this.gc = gc;
		this.app = app;

		//Initialize Item Images Array (This has to come before: GUI, )
		Item.loadImages("res/items0.png", 64, 4);
		
		// GUI =====================================VVVVVVVVVVVVVV		
		gameGuiManager = new GuiManager(this);
		
		healthBar = new ScalingBar("HealthBar", Globals.SCREEN_WIDTH*0.05f, Globals.SCREEN_HEIGHT*0.9f, 150, 30, gc, app, 10.0f, Color.red);
		gameGuiManager.addComponent(healthBar);
		
		itemBar = new HotBar("ItemBar", Globals.SCREEN_WIDTH*0.3f, Globals.SCREEN_HEIGHT*0.9f, 64, 64, gc, app, 10, "res/gui/slot.png", "res/gui/hotbarSelector.png");
		gameGuiManager.addComponent(itemBar);
		
		inventoryScreen = new InventoryScreen(gameGuiManager, gc, app, this);
		
		// END GUI ================================^^^^^^^^^^^^^^^
		
		//PLAYER ==================================VVVVVVVVVVVVVVVVV
		
		player = new Player(xOffset, yOffset, this);
		
		// END PLAYER =============================^^^^^^^^^^^^^^^^
		
		//World:
		world = new World();
		
		itemManager = new ItemManager(this);
	
	}

	// init-method for initializing all resources
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		for(int i = 0; i < 10; i++)
			player.addItem(new Item1());
	}

	// render-method for all the things happening on-screen
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		//Clears the screen
		g.setColor(Color.black);
		g.fillRect(0, 0, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);

		//draw world
		world.display(xOffset, yOffset);
		
		//draws player	================================//
		player.render();								//
														// THESE BOTH WILL BE IN WORLD.DISPLAY LATER
		//draws items									//
		itemManager.render(xOffset, yOffset);//=========//
		
		//Draws GUI
		gameGuiManager.draw(gc, g);
		
		inventoryScreen.render();
	}

	// update-method with all the magic happening in it
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		float deltaX = 0;
		float deltaY = 0;
		
		if(gc.getInput().isKeyDown(Input.KEY_A))
		{
			deltaX = player.getMoveSpeed() * delta;
		}
		
		if(gc.getInput().isKeyDown(Input.KEY_D))
		{
			deltaX = -(player.getMoveSpeed() * delta);
		}
		
		if(gc.getInput().isKeyDown(Input.KEY_W))
		{
			deltaY = player.getMoveSpeed() * delta;
		}
		
		if(gc.getInput().isKeyDown(Input.KEY_S))
		{
			deltaY = -(player.getMoveSpeed() * delta);
		}
		
		//normalisation
		if(yOffset != 0 && xOffset != 0)
		{
			deltaX *= Math.sqrt(2);
			deltaY *= Math.sqrt(2);
		}
		
		int INTdeltaX = (int) deltaX;
		int INTdeltaY = (int) deltaY;
		
		xOffset += INTdeltaX;
		yOffset += INTdeltaY;
		
		player.updatePosition(INTdeltaX, INTdeltaY);
		
		// FLOATING ITEMS
		itemManager.update();
		
		//System.out.println(" XOffset: " + (xOffset + Globals.SCREEN_WIDTH/2 - Globals.TILE_SIZE/2) + " YOffset: " + (yOffset + Globals.SCREEN_HEIGHT/2 - Globals.TILE_SIZE/2));
		
	}
	
	public HotBar getHotbar()
	{
		return this.itemBar;
	}
	
	public boolean canMove(Player.MoveDirection direction)
	{
		//check for collisions
		return true;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public InventoryScreen getInventoryScreen()
	{
		return this.inventoryScreen;
	}
	
	public ItemManager getItemManager()
	{
		return this.itemManager;
	}

	public void keyPressed(int key, char c)
	{		
		if(key == Input.KEY_A)
		{
			player.setMovementDirection(Player.MoveDirection.left);
		}else if(key == Input.KEY_D)
		{
			player.setMovementDirection(Player.MoveDirection.right);
		}else if(key == Input.KEY_W)
		{
			player.setMovementDirection(Player.MoveDirection.up);
		}else if(key == Input.KEY_S)
		{
			player.setMovementDirection(Player.MoveDirection.down);
		}
		
		if(key == Input.KEY_1)
		{
			itemBar.selectItem(0);
		}else if(key == Input.KEY_2)
		{
			itemBar.selectItem(1);
		}else if(key == Input.KEY_3)
		{
			itemBar.selectItem(2);
		}else if(key == Input.KEY_4)
		{
			itemBar.selectItem(3);
		}else if(key == Input.KEY_5)
		{
			itemBar.selectItem(4);
		}else if(key == Input.KEY_6)
		{
			itemBar.selectItem(5);
		}else if(key == Input.KEY_7)
		{
			itemBar.selectItem(6);
		}else if(key == Input.KEY_8)
		{
			itemBar.selectItem(7);
		}else if(key == Input.KEY_9)
		{
			itemBar.selectItem(8);
		}else if(key == Input.KEY_0)
		{
			itemBar.selectItem(9);
		}
		
		if(key == Input.KEY_ESCAPE)
		{
			gc.exit();
		}
		
		if(key == Input.KEY_I)
		{
			if(inMenu)
			{
				inventoryScreen.hide();
				inMenu = false;
			}else
			{
				inventoryScreen.show();
				inMenu = true;
			}
		}
	}
	
	public void keyReleased(int key, char c)
	{
		if(key == Input.KEY_A || key == Input.KEY_D || key == Input.KEY_W || key == Input.KEY_S)
		{
			if(!gc.getInput().isKeyDown(Input.KEY_A) && !gc.getInput().isKeyDown(Input.KEY_D) && !gc.getInput().isKeyDown(Input.KEY_W) && !gc.getInput().isKeyDown(Input.KEY_S))
			{
				player.setMovementDirection(Player.MoveDirection.stationary);
			}
		}
	}
	
	// Intenal GUI (this is fucked up but it works)
	public void componentActivated(AbstractComponent source)
	{
		// System.out.println("Component activated");

		for (int i = 0; i < gameGuiManager.getComponents().size(); i++)
		{
			if(gameGuiManager.getComponents().get(i) instanceof Button)
			{
				
				Button b = (Button) (gameGuiManager.getComponents().get(i));
				if(source == b.getComponent())
				{
					b.run();
				}
			}else if(gameGuiManager.getComponents().get(i) instanceof MyTextField)
			{
				MyTextField t = (MyTextField) (gameGuiManager.getComponents().get(i));
				if(source == t.getComponent())
				{
					TextField field = (TextField) t.getComponent();
					t.setText(field.getText());
				}
			}
		}
	}

	// Returning 'ID' from class 'MainMenu'
	@Override
	public int getID()
	{
		return Globals.GAME;
	}
}