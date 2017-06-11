package com.dora.character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.dora.item.EmptyItem;
import com.dora.item.Item;
import com.dora.main.GameState;
import com.dora.main.Globals;

public class Player extends Character
{	
	private Animation walkAnim;
	
	private Image spriteSheetImage;
	private final int PLAYER_IMAGE_SIZE_SS = 32;
	private final int PLAYER_IMAGE_SIZE = 45;
	
	private final float MAX_HEALTH = 100.0f;
	private final float DEFAULT_SPEED = 0.1f;
	private final int MAX_INV_SIZE = 48 + 10;
	
	private float angle;
	
	private GameState gameState;
	
	public Player(float xOffset, float yOffset, GameState gs)
	{
		this.gameState = gs;
		
		this.angle = 0;
		
		this.health = MAX_HEALTH;
		this.speed = DEFAULT_SPEED;
		
		this.moveDirection = MoveDirection.stationary;
		
		this.xPos = (int) (xOffset + Globals.SCREEN_WIDTH/2 - this.PLAYER_IMAGE_SIZE/2);
		this.yPos = (int) (yOffset + Globals.SCREEN_HEIGHT/2 - this.PLAYER_IMAGE_SIZE/2);
		
		this.inventory = new Item[MAX_INV_SIZE];
		for(int i = 0; i < MAX_INV_SIZE; i++)
		{
			inventory[i] = new EmptyItem();
		}
		
		try
		{
			spriteSheetImage = new Image("res/player/player1_spritesheet.png");
		}catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		SpriteSheet playerSpriteSheet = new SpriteSheet(spriteSheetImage, PLAYER_IMAGE_SIZE_SS, PLAYER_IMAGE_SIZE_SS);
		
		Image[] walkImages = {playerSpriteSheet.getSubImage(0, 0), playerSpriteSheet.getSubImage(1, 0)};
		walkAnim = new Animation(walkImages, 300);
	}
	
	public void updatePosition(float deltaX, float deltaY)
	{
		this.xPos -= deltaX;
		this.yPos -= deltaY;
		
		//System.out.print("  PlayerX: " + this.xPos + " PlayerY: " + this.yPos);
	}
	
	public boolean addItem(Item item)
	{
		if(gameState.getInventoryScreen().addItem(item))
		{
			for(int i = 0; i < this.MAX_INV_SIZE; i++)
			{
				if(this.inventory[i].getId().compareTo(Item.ItemIDs.empty) == 0)
				{
					this.inventory[i] = item;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public void removeItem(int index)
	{
		this.inventory[index] = new EmptyItem();
	}
	
	public void render(float deltaX, float deltaY)
	{
		float angle = 0;
		
		if(deltaX == 0)
		{
			if(deltaY >= 0)
			{
				angle = (float) (Math.PI/2.0f);
			}else
			{
				angle = (float) (-Math.PI/2.0f);
			}
		}else if(deltaX > 0)
		{
			angle = (float) Math.atan(deltaY/deltaX);
		}else
		{
			angle = (float) -Math.atan(-deltaY/deltaX);
			angle += Math.PI;
		}
		
		Image current = walkAnim.getCurrentFrame();
		current.setCenterOfRotation(this.PLAYER_IMAGE_SIZE/2, this.PLAYER_IMAGE_SIZE/2);
		current.setRotation(radToDeg(angle));
		current.draw(Globals.SCREEN_WIDTH/2 - this.PLAYER_IMAGE_SIZE/2, Globals.SCREEN_HEIGHT/2 - this.PLAYER_IMAGE_SIZE/2, PLAYER_IMAGE_SIZE, PLAYER_IMAGE_SIZE);
		
		this.angle = radToDeg(angle);
		
		//hack to get the animation to update
		walkAnim.draw(-this.PLAYER_IMAGE_SIZE, -this.PLAYER_IMAGE_SIZE);
		
		//System.out.println("Anim: " + walkAnim.getFrameCount());
	}
	
	public void setMovementDirection(MoveDirection dir)
	{
		moveDirection = dir;
		
		if(moveDirection.compareTo(MoveDirection.stationary) == 0)
		{
			walkAnim.stop();
		}else
		{
			walkAnim.start();
		}
	}

	public float getMoveSpeed()
	{
		return this.speed;
	}
	
	public int getSize()
	{
		return this.PLAYER_IMAGE_SIZE;
	}

	public void dealDamage(float dmg)
	{
		this.health -= dmg;
		this.gameState.getHealthBar().setCurrentValue(health);
	}
	
	public float getAngle()
	{
		return this.angle;
	}
}
