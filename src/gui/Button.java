package gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;

import main.Main;

public class Button extends GuiElement
{
	Image normal, mouseOver, pressed;
	SpriteSheet sprites;
		
	int x, y;
	
	int width, height;
	
	String text;
	
	GameContainer gc;
	
	Input input;
	
	MouseOverArea area;
	
	boolean active = false;
	
	protected Main app;
	
	boolean justPressed;

	
	public Button(String name, float x, float y, int w, int h, String filepath, GameContainer gc, Main app)
	{
		super(name);
		
		this.x = (int) x;
		this.y = (int) y;
		this.width = w;
		this.height = h;
		this.gc = gc;
		
		this.app = app;
		
		try
		{
			sprites = new SpriteSheet(filepath,  w, h);
		}catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		normal = sprites.getSubImage(0, 0);
		mouseOver = sprites.getSubImage(0, 1);
		pressed = sprites.getSubImage(0, 2);
		
		area = new MouseOverArea(gc, normal, this.x, this.y, w, h);
		area.setMouseOverImage(mouseOver);
		area.setMouseDownImage(pressed);
		
		active = true;
	}
	
	public AbstractComponent getComponent()
	{
		return this.area;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void draw(GameContainer gc, Graphics g)
	{
		area.render(gc, g);
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
		area.setAcceptingInput(active);
	}
	
	public boolean isActive()
	{
		return this.active;
	}
	
	public void setJustPressed(boolean justPressed)
	{
		this.justPressed = justPressed;
	}
	
	public boolean getJustPressed()
	{
		return this.justPressed;
	}
	
	public void run()
	{
		
	}
}
