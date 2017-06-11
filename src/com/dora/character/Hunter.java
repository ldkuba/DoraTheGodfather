package com.dora.character;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.dora.item.EmptyItem;
import com.dora.item.Item;
import com.dora.item.Item1;
import com.dora.main.GameState;
import com.dora.main.Globals;

public class Hunter extends NPC
{
	private final int MAX_INVENTORY_SIZE = 10;
	private final float MAX_HEALTH = 20.0f;
	private final float DEFAULT_SPEED = 0.15f;
	
	private final int HUNTER_IMAGE_SIZE_SS = 32;
	private final int HUNTER_IMAGE_SIZE = 45;
	
	private final int BLIND_FOLLOW_RANGE = 10;
	
	private final float ATTACK_DAMAGE = 5.0f;
	
	private int attackDistance;
	
	private float attackRate = 500.0f;
	private float attackClock = 0.0f;
	
	private Animation walkAnim;
	private Animation attackAnim;
	
	public Hunter(GameState gs, NPCType type, int x, int y)
	{
		super(gs, type, x, y);
		
		this.health = MAX_HEALTH;
		this.speed = DEFAULT_SPEED;
		
		this.attackDistance = HUNTER_IMAGE_SIZE;
		
		this.inventory = new Item[MAX_INVENTORY_SIZE];
		for(int i = 0; i < MAX_INVENTORY_SIZE; i++)
		{
			inventory[i] = new EmptyItem();
		}
		
		//setup standard inventory`
		inventory[0] = new Item1();
		inventory[1] = new Item1();
		
		Image ssImage = null;
		
		try{
			ssImage = new Image("res/npc/hunter.png");
		}catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		SpriteSheet ss = new SpriteSheet(ssImage, HUNTER_IMAGE_SIZE_SS, HUNTER_IMAGE_SIZE_SS);
		
		Image[] walkImages = {ss.getSubImage(0, 0), ss.getSubImage(1, 0)};
		walkAnim = new Animation(walkImages, 150);
		
		Image[] attackImages = {ss.getSubImage(0, 1), ss.getSubImage(1, 1)};
		attackAnim = new Animation(attackImages, 300);
		
		walkAnim.stop();
		attackAnim.stop();
	}
	
	public void update(float tpf)
	{
		int playerX = this.gameState.getPlayer().getX();
		int playerY = this.gameState.getPlayer().getY();
		
		float distanceToPlayer = (float) Math.sqrt((playerX - this.xPos)*(playerX - this.xPos) + (playerY - this.yPos)*(playerY - this.yPos));
		
		//System.out.println("Distance to Player: " + distanceToPlayer);
		
		//if(distanceToPlayer < BLIND_FOLLOW_RANGE*Globals.TILE_SIZE)
		//{
			if(distanceToPlayer < this.attackDistance)
			{
				//ATTACK PLAYER
				if(attackAnim.isStopped())
				{
					System.out.println("ATTACKING");
					
					attackAnim.start();
					walkAnim.stop();
				}
				
				attackClock += tpf;
				
				if(attackClock >= attackRate)
				{
					attackClock = 0.0f;
					gameState.getPlayer().dealDamage(this.ATTACK_DAMAGE);
				}
				
//			}else
//			{
//				//A*STAR
//				if(walkAnim.isStopped())
//				{
//					System.out.println("WALKING");
//					
//					walkAnim.start();
//					attackAnim.stop();
//				}
//			}
		}else
		{
			//FOLLOW PLAYER
			float deltaX = playerX - this.xPos;
			float deltaY = playerY - this.yPos;
			
			Vector2f deltaToPlayer = new Vector2f(deltaX, deltaY);
			deltaToPlayer.normalise().scale(speed*tpf);
			
			int INTdeltaX = (int) deltaToPlayer.x;
			int INTdeltaY = (int) deltaToPlayer.y;
			
			if(!checkMoveX(INTdeltaX, this.xPos, this.yPos, this.HUNTER_IMAGE_SIZE))
			{
				INTdeltaX = 0;
			}
			
			if(!checkMoveY(INTdeltaY, this.xPos, this.yPos, this.HUNTER_IMAGE_SIZE))
			{
				INTdeltaY = 0;
			}
			
			this.xPos += INTdeltaX;
			this.yPos += INTdeltaY;
			
			if(walkAnim.isStopped())
			{
				walkAnim.start();
				attackAnim.stop();
			}
			
			//System.out.println("Following");
		}
	}
	
	public void setInventory(Item[] items)
	{
		for(int i = 0; i < this.MAX_INVENTORY_SIZE; i++)
		{
			if(i >= items.length)
			{
				this.inventory[i] = new EmptyItem();
			}else
			{
				this.inventory[i] = items[i];
			}
		}
	}
	
	public void render(int xOffset, int yOffset)
	{
		float angle = 0;
		
		float deltaX = this.gameState.getPlayer().getX() - this.xPos;
		float deltaY = this.gameState.getPlayer().getY() - this.yPos;
		
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
		
		if(!walkAnim.isStopped())
		{
			Image current = walkAnim.getCurrentFrame();
			current.setCenterOfRotation(this.HUNTER_IMAGE_SIZE/2, this.HUNTER_IMAGE_SIZE/2);
			current.setRotation(radToDeg(angle));
			current.draw(this.xPos + xOffset, this.yPos + yOffset, HUNTER_IMAGE_SIZE, HUNTER_IMAGE_SIZE);
			
			//System.out.println("X: " + xPos + " Y: " + yPos);
			
			//hack to get the animation to update
			walkAnim.draw(-this.HUNTER_IMAGE_SIZE, -this.HUNTER_IMAGE_SIZE);
		}else
		{
			Image current = attackAnim.getCurrentFrame();
			current.setCenterOfRotation(this.HUNTER_IMAGE_SIZE/2, this.HUNTER_IMAGE_SIZE/2);
			current.setRotation(radToDeg(angle));
			current.draw(this.xPos + xOffset, this.yPos + yOffset, HUNTER_IMAGE_SIZE, HUNTER_IMAGE_SIZE);
			
			//hack to get the animation to update
			attackAnim.draw(-this.HUNTER_IMAGE_SIZE, -this.HUNTER_IMAGE_SIZE);
		}
	}
	
	public int getSize()
	{
		return this.HUNTER_IMAGE_SIZE;
	}
}
