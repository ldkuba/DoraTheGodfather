package com.dora.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Entity
{
	protected int xPos, yPos;
	protected float angle;
	
	protected entityTypes id;
	
	protected final int ENTITY_IMAGE_SIZE = 64;
	
	public enum entityTypes {
		bullet
	}
	
	public static Image[] entityImages;
	
	public Entity()
	{
		xPos = 0;
		yPos = 0;
	}

	public static void loadImages(String filePath, int squareSize, int number)
	{
		entityImages = new Image[number];
		
		Image sSImage = null;
		
		try
		{
			sSImage = new Image(filePath);
		}catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		SpriteSheet sS = new SpriteSheet(sSImage, squareSize, squareSize);
		
		for(int j = 0; j < Math.sqrt(number); j++)
		{
			for(int i = 0; i < Math.sqrt(number); i++)
			{
				entityImages[(int) (j*Math.sqrt(number) + i)] = sS.getSubImage(i, j);
			}
		}
	}
	
	public int getxPos()
	{
		return xPos;
	}

	public void setxPos(int xPos)
	{
		this.xPos = xPos;
	}

	public int getyPos()
	{
		return yPos;
	}

	public void setyPos(int yPos)
	{
		this.yPos = yPos;
	}
	
	public void onCollide() {}
	
	public void update(float tpf) {}
	
	public void render(int xOffset, int yOffset) {}
}
