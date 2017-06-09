package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.dora.world.World;

import gui.Button;
import gui.GuiManager;
import gui.MyTextField;
import gui.ScalingBar;

public class GameState extends BasicGameState implements ComponentListener
{

	private GuiManager gameGuiManager;
	private ScalingBar healthBar;
	
	private float xOffset=0f;
	private float yOffset=0f;
	
	private World world;

	private GameContainer gc;
	private Main app;
	
	public GameState(GameContainer gc, Main app)
	{
		this.gc = gc;
		this.app = app;

		// GUI =====================================VVVVVVVVVVVVVV		
		gameGuiManager = new GuiManager(this);
		
		healthBar = new ScalingBar("HealthBar", Globals.SCREEN_WIDTH*0.05f, Globals.SCREEN_HEIGHT*0.9f, 150, 30, gc, app, 10.0f, Color.red);
		gameGuiManager.addComponent(healthBar);
		
		// END GUI ================================^^^^^^^^^^^^^^^
		
		//World:
		world = new World();
	
	}

	// init-method for initializing all resources
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{

	}

	// render-method for all the things happening on-screen
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		//Clears the screen
		g.setColor(Color.black);
		g.fillRect(0, 0, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);

		//draw world
		world.display(xOffset, yOffset);
		
		//Draws GUI
		gameGuiManager.draw(gc, g);
	}

	// update-method with all the magic happening in it
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		
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