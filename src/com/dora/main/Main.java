package com.dora.main;

import java.awt.SplashScreen;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame
{
	// Class Constructor
	public Main(String appName)
	{
		super(appName);
	}

	// Initialize your game states (calls init method of each gamestate, and
	// set's the state ID)
	public void initStatesList(GameContainer gc) throws SlickException

	{
		// The first state added will be the one that is loaded first, when the
		// application is launched
		 this.addState(new GameState(gc, this));

	}

	// Main Method
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer app = new AppGameContainer(new Main("My Game v" + Globals.VERSION));
			app.setDisplayMode(Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT, Globals.FULLSCREEN);
			app.setTargetFrameRate(Globals.FPS);
			app.setShowFPS(true);
			app.start();
		}catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
}