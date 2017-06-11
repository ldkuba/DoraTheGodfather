package com.dora.item;

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
		
	}
}
