package com.dora.entity;

import org.newdawn.slick.Image;

public class Bullet extends Entity
{
	private float speed;
	private final float DEFAULT_BULLET_SPEED = 0.3f;
	
	private boolean fired;
	
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
		tmpImage.setRotation(angle);
		tmpImage.draw(this.xPos + xOffset, this.yPos + yOffset, this.ENTITY_IMAGE_SIZE, this.ENTITY_IMAGE_SIZE);
	}
	
	public void update(float tpf)
	{
		
	}
	
	public void fire()
	{
		this.fired = true;
	}
}
