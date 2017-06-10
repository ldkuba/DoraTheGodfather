package com.dora.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.AbstractComponent;

public class GuiElement
{
	String name;
	
	public GuiElement(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void draw(GameContainer gc, Graphics g) {}
	
	public AbstractComponent getComponent()
	{
		return null;
	}
}
