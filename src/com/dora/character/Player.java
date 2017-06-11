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
	private Image defaultImage;
	private Animation leftWalkAnim;
	private Animation rightWalkAnim;
	private Animation upWalkAnim;
	private Animation downWalkAnim;
	
	private Image spriteSheetImage;
	private final int PLAYER_IMAGE_SIZE_SS = 32;
	private final int PLAYER_IMAGE_SIZE = 45;
	
	private final float MAX_HEALTH = 100.0f;
	private final float DEFAULT_SPEED = 0.1f;
	private final int MAX_INV_SIZE = 48 + 10;
	
	private MoveDirection playerMoveDirection;
	
	private GameState gameState;
	
	public enum MoveDirection {
		left, right, up, down, stationary
	}
	
	public Player(float xOffset, float yOffset, GameState gs)
	{
		this.gameState = gs;
		
		this.health = MAX_HEALTH;
		this.speed = DEFAULT_SPEED;
		
		this.playerMoveDirection = MoveDirection.stationary;
		
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
		defaultImage = playerSpriteSheet.getSubImage(0, 0);
		
		Image[] leftImages = {playerSpriteSheet.getSubImage(1, 0), playerSpriteSheet.getSubImage(2, 0)};
		leftWalkAnim = new Animation(leftImages, 300);
		
		Image[] rightImages = {playerSpriteSheet.getSubImage(1, 1), playerSpriteSheet.getSubImage(2, 1)};
		rightWalkAnim = new Animation(rightImages, 300);
		
		Image[] upImages = {playerSpriteSheet.getSubImage(1, 2), playerSpriteSheet.getSubImage(2, 2)};
		upWalkAnim = new Animation(upImages, 300);
		
		Image[] downImages = {playerSpriteSheet.getSubImage(1, 3), playerSpriteSheet.getSubImage(2, 3)};
		downWalkAnim = new Animation(downImages, 300);
	}
	
	public void updatePosition(float deltaX, float deltaY)
	{
		this.xPos -= deltaX;
		this.yPos -= deltaY;
		
		System.out.print("  PlayerX: " + this.xPos + " PlayerY: " + this.yPos);
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
	
	public void render()
	{
		if(playerMoveDirection.compareTo(MoveDirection.left) == 0)
		{
			leftWalkAnim.draw(Globals.SCREEN_WIDTH/2 - this.PLAYER_IMAGE_SIZE/2, Globals.SCREEN_HEIGHT/2 - this.PLAYER_IMAGE_SIZE/2, PLAYER_IMAGE_SIZE, PLAYER_IMAGE_SIZE);
		}else if(playerMoveDirection.compareTo(MoveDirection.right) == 0)
		{
			rightWalkAnim.draw(Globals.SCREEN_WIDTH/2 - this.PLAYER_IMAGE_SIZE/2, Globals.SCREEN_HEIGHT/2 - this.PLAYER_IMAGE_SIZE/2, PLAYER_IMAGE_SIZE, PLAYER_IMAGE_SIZE);
		}else if(playerMoveDirection.compareTo(MoveDirection.up) == 0)
		{
			upWalkAnim.draw(Globals.SCREEN_WIDTH/2 - this.PLAYER_IMAGE_SIZE/2, Globals.SCREEN_HEIGHT/2 - this.PLAYER_IMAGE_SIZE/2, PLAYER_IMAGE_SIZE, PLAYER_IMAGE_SIZE);
		}else if(playerMoveDirection.compareTo(MoveDirection.down) == 0)
		{
			downWalkAnim.draw(Globals.SCREEN_WIDTH/2 - this.PLAYER_IMAGE_SIZE/2, Globals.SCREEN_HEIGHT/2 - this.PLAYER_IMAGE_SIZE/2, PLAYER_IMAGE_SIZE, PLAYER_IMAGE_SIZE);
		}else if(playerMoveDirection.compareTo(MoveDirection.stationary) == 0)
		{
			defaultImage.draw(Globals.SCREEN_WIDTH/2 - this.PLAYER_IMAGE_SIZE/2, Globals.SCREEN_HEIGHT/2 - this.PLAYER_IMAGE_SIZE/2, PLAYER_IMAGE_SIZE, PLAYER_IMAGE_SIZE);
		}
	}
	
	public void setMovementDirection(MoveDirection dir)
	{
		playerMoveDirection = dir;
		
		if(playerMoveDirection.compareTo(MoveDirection.left) == 0)
		{
			rightWalkAnim.stop();
			upWalkAnim.stop();
			downWalkAnim.stop();
			leftWalkAnim.start();
		}else if(playerMoveDirection.compareTo(MoveDirection.right) == 0)
		{
			leftWalkAnim.stop();
			upWalkAnim.stop();
			downWalkAnim.stop();
			rightWalkAnim.start();
		}else if(playerMoveDirection.compareTo(MoveDirection.up) == 0)
		{
			rightWalkAnim.stop();
			leftWalkAnim.stop();
			downWalkAnim.stop();
			upWalkAnim.start();
		}else if(playerMoveDirection.compareTo(MoveDirection.down) == 0)
		{
			rightWalkAnim.stop();
			upWalkAnim.stop();
			leftWalkAnim.stop();
			downWalkAnim.start();
		}else if(playerMoveDirection.compareTo(MoveDirection.stationary) == 0)
		{
			rightWalkAnim.stop();
			upWalkAnim.stop();
			downWalkAnim.stop();
			leftWalkAnim.stop();
		}
	}


	public float getMoveSpeed()
	{
		return this.speed;
	}
}
