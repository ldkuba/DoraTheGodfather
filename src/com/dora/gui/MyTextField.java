package com.dora.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.TextField;

import com.dora.main.Main;

import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;

public class MyTextField extends GuiElement
{
	int x, y;
	
	int width, height;
	
	String text;
	
	GameContainer gc;
	
	Input input;
	
	TextField textField;
	
	boolean active = false;

	public MyTextField(String name, float x, float y, int w, int h, GameContainer gc, Main app)
	{
		super(name);
		
		this.x = (int) x;
		this.y = (int) y;
		this.width = w;
		this.height = h;
		this.gc = gc;
		
		textField = new TextField(gc, gc.getDefaultFont(), this.x, this.y, w, h);
		
		active = true;
		
	}
	
	public AbstractComponent getComponent()
	{
		return this.textField;
	}
	
	public void draw(GameContainer gc, Graphics g)
	{
		textField.render(gc, g);
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
		textField.setAcceptingInput(active);
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
