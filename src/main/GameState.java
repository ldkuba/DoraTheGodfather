package main;

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

import gui.Button;
import gui.GuiManager;
import gui.HotBar;
import gui.InventoryScreen;
import gui.MyTextField;
import gui.ScalingBar;
import item.Item;
import item.Item1;

public class GameState extends BasicGameState implements ComponentListener
{

	private GuiManager gameGuiManager;
	private ScalingBar healthBar;
	private HotBar itemBar;
	private InventoryScreen inventoryScreen;
	
	private boolean inMenu = false;
	
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
	}

	// init-method for initializing all resources
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		for(int i = 0; i < 3; i++)
			inventoryScreen.addItem(new Item1());
	}

	// render-method for all the things happening on-screen
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		//Clears the screen
		g.setColor(Color.black);
		g.fillRect(0, 0, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);

		//Draws GUI
		gameGuiManager.draw(gc, g);
		
		inventoryScreen.render();
	}

	// update-method with all the magic happening in it
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		
	}
	
	public HotBar getHotbar()
	{
		return this.itemBar;
	}

	public void keyPressed(int key, char c)
	{
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