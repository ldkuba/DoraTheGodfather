package com.dora.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.dora.character.NPC;
import com.dora.character.NPCManager;
import com.dora.character.Player;
import com.dora.main.GameState;
import com.dora.main.Globals;
import com.dora.world.Objects;
import com.dora.world.World;

public class Entity
{
	protected int xPos, yPos;
	protected float angle;
	
	protected entityTypes id;
	
	private int lifetime;
	
	protected final int ENTITY_IMAGE_SIZE = 64;
	
	public enum entityTypes {
		bullet
	}
	
	public static Image[] entityImages;
	
	public Entity()
	{
		xPos = 0;
		yPos = 0;
		
		this.angle = 0;
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
	
	public void setAngle(float angle)
	{
		this.angle = angle;
	}
	
	// TODO
	public NPC collideWithNPCs(NPCManager npcManager, int deltaX, int deltaY)
	{
		return null;
	}
	
	// TODO
	public Objects collideWithObjects(World world, int deltaX, int deltaY)
	{
		return null;
	}
	
	//TODO
	public Player collideWithPlayer(Player player, int deltaX, int deltaY)
	{
		
		return null;
	}
	
	public float distanceToNPC(NPC npc)
	{
		float distance = 0;
		
		distance = (float) Math.sqrt((npc.getX()-this.xPos)*(npc.getX()-this.xPos) + (npc.getY()-this.yPos)*(npc.getY()-this.yPos));
		
		return distance;
	}
	
	public float distanceToObject(Objects object)
	{
		float distance = 0;
		
		distance = (float) Math.sqrt(((object.getX()*Globals.TILE_SIZE)-this.xPos)*((object.getX()*Globals.TILE_SIZE)-this.xPos) + ((object.getY()*Globals.TILE_SIZE)-this.yPos)*((object.getY()*Globals.TILE_SIZE)-this.yPos));
		
		return distance;
	}
	
	public float distanceToPlayer(Player player)
	{
		float distance = 0;
		
		distance = (float) Math.sqrt((player.getX()-this.xPos)*(player.getX()-this.xPos) + (player.getY()-this.yPos)*(player.getY()-this.yPos));
		
		return distance;
	}
	
	public void update(float tpf, GameState gameState) {}
	
	public void render(int xOffset, int yOffset) {}
}
