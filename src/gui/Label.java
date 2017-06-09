package gui;

import java.awt.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;

import main.Main;

public class Label extends GuiElement
{
	int x, y;
	
	int width, height;
	
	String text;
		
	GameContainer gc;
	
	Input input;
	
	Font font;
	TrueTypeFont ttf;
	
	boolean active = false;

	public Label(String name, float x, float y, int w, int h, GameContainer gc, Main app, Font font)
	{
		super(name);
		
		this.x = (int) x;
		this.y = (int) y;
		this.width = w;
		this.height = h;
		this.gc = gc;
		this.font = font;		
		
		ttf = new TrueTypeFont(font, true);
		
		text = "";
		
		active = true;
	}
	
	public void draw(GameContainer gc, Graphics g)
	{
		ttf.drawString(x, y, text);
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	public boolean isActive()
	{
		return this.active;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return this.text;
	}
}
