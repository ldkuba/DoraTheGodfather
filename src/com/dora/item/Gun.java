package com.dora.item;

import com.dora.entity.Bullet;
import com.dora.main.GameState;

public class Gun extends Item
{
	private GameState gameState;
	
	public Gun(GameState gs)
	{
		super();
		this.id = Item.ItemIDs.gun;
		this.gameState = gs;
	}
	
	public void onEquip()
	{
		gameState.setCursor(GameState.cursors.aim);
	}
	
	public void onLeftClick()
	{
		Bullet bullet = new Bullet();
		
		float angle = gameState.getPlayer().getAngle();
		
		float bulletSpawnDistance = gameState.getPlayer().getSize()*1.5f;
		
		bullet.setxPos((int) (gameState.getPlayer().getX() + bulletSpawnDistance*Math.cos(angle)));
		bullet.setyPos((int) (gameState.getPlayer().getY() - bulletSpawnDistance*Math.sin(angle)));
		bullet.setAngle(angle);
		
		gameState.getEntityManager().addEntity(bullet);
		
		bullet.fire();
		
		System.out.println("Firing");
	}
}
