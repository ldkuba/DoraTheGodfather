package com.dora.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.AbstractComponent;

import com.dora.main.Main;

public class ScalingBar extends GuiElement
{
	private int x, y;
	private int height;
	
	private int maxWidth;
	private float maxValue;
	
	private float currentValue;
	
	private Color color;
	
	private GameContainer gc;
	private Main app;
	
	public ScalingBar(String name, float x, float y, int w, int h, GameContainer gc, Main app, float max, Color color)
	{
		super(name);
		this.maxValue = max;
		this.x = (int) x;
		this.y = (int) y;
		this.height = h;
		this.maxWidth = w;
		this.color = color;
		this.gc = gc;
		this.app = app;
		
		this.currentValue = max;
	}
	
	public void setCurrentValue(float newValue)
	{
		if(newValue < 0 || newValue > maxValue)
			return;
		
		this.currentValue = newValue;
	}
	
	public void draw(GameContainer gc, Graphics g)
	{
		g.setColor(color);
		g.fillRect(x, y, maxWidth*(currentValue/maxValue), height);
		g.setColor(Color.white);
	}
}
