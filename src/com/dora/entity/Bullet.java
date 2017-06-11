package com.dora.entity;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;

import com.dora.character.NPC;
import com.dora.character.Player;
import com.dora.main.GameState;
import com.dora.world.Objects;

public class Bullet extends Entity
{
	private float speed;
	private final float DEFAULT_BULLET_SPEED = 0.7f;
	
	private boolean fired;
	
	private final float BULLET_DAMAGE = 7.0f;
	
	public Bullet()
	{
		this.id = Entity.entityTypes.bullet;
		fired = false;
		
		this.speed = DEFAULT_BULLET_SPEED;
	}
	
	public void render(int xOffset, int yOffset)
	{
		Image tmpImage = Entity.entityImages[id.ordinal()].copy();
		tmpImage.setCenterOfRotation(this.ENTITY_IMAGE_SIZE/2, this.ENTITY_IMAGE_SIZE/2);
		tmpImage.setRotation(radToDeg(angle));
		tmpImage.draw(this.xPos + xOffset, this.yPos + yOffset, this.ENTITY_IMAGE_SIZE, this.ENTITY_IMAGE_SIZE);
	}
	
	public void update(float tpf, GameState gameState)
	{
		int deltaX = 0;
		int deltaY = 0;
		
		float deltaYFloat = (float) (Math.sin(angle)*speed*tpf);
		float deltaXFloat = (float) (Math.cos(angle)*speed*tpf);
		
		deltaX = (int) deltaXFloat;
		deltaY = (int) deltaYFloat;
		
		NPC colNPC = collideWithNPCs(gameState.getNPCManager(), deltaX, deltaY);			
				
		Objects colObject = collideWithObjects(gameState.getWorld(), deltaX, deltaY);
		
		Player player = collideWithPlayer(gameState.getPlayer(), deltaX, deltaY);
		
		float minDistance = 0;
		int minIndex = -1;
		
		if(colNPC != null)
		{
			minDistance = distanceToNPC(colNPC);
			minIndex = 0;
		}
		
		if(colObject != null)
		{
			if(minDistance > distanceToObject(colObject))
			{
				minDistance = distanceToObject(colObject);
				minIndex = 1;
			}
		}
		
		if(player != null)
		{
			if(minDistance > distanceToPlayer(player))
			{
				minIndex = 2;
			}
		}
		
		if(minIndex == 0)
		{
			colNPC.dealDamage(this.BULLET_DAMAGE);
			gameState.getEntityManager().removeEntity(this);
			return;
			
		}else if(minIndex == 1)
		{
			gameState.getEntityManager().removeEntity(this);
			return;
		}else if(minIndex == 2)
		{
			player.dealDamage(this.BULLET_DAMAGE);
			gameState.getEntityManager().removeEntity(this);
			return;
		}
		
		this.xPos += deltaX;
		this.yPos += deltaY;
	}
	
	private float radToDeg(float rad)
	{
		return (float) ((rad*180.0f) / Math.PI);
	}
	
	public void fire()
	{
		this.fired = true;
	}
}
