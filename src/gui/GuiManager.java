package gui;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.ComponentListener;

public class GuiManager
{
	ArrayList<GuiElement> components;
	
	private ComponentListener listener;
	
	public GuiManager(ComponentListener listener)
	{
		components = new ArrayList<GuiElement>();
		this.listener = listener;
	}
	
	public ArrayList<GuiElement> getComponents()
	{
		return this.components;
	}
	
	public void addComponent(GuiElement component)
	{
		components.add(component);
		
		if(component.getComponent() != null)
		{
			component.getComponent().addListener(listener);
		}		
	}

	public void removeComponent(GuiElement component)
	{
		if(components.contains(component))
			components.remove(component);
	}

	public void draw(GameContainer gc, Graphics g)
	{
		for(int i = 0; i < components.size(); i++)
		{
			components.get(i).draw(gc, g);
		}
	}

	public GuiElement getComponent(String name)
	{
		for(int i = 0; i < components.size(); i++)
		{
			if(components.get(i).getName().equals(name))
			{
				return components.get(i);
			}
		}
		
		return null;
	}
}
