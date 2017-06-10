package com.dora.world;

import org.newdawn.slick.Image;

import com.dora.main.Globals;

public class Tile {
	private boolean passable;
	private boolean vBlock;
	private Image terrain;
	private Image object;
	private Objects oRef;
	private boolean land;
	private boolean placeObject;
	
	Tile(Image terrain, boolean land){
		this.terrain = terrain;
		this.land = land;
		vBlock = false;
		this.passable = land;
		this.placeObject=land;
	}
	
	public void setObject(Objects object, Image tex){
		this.oRef=object;
		this.object=tex;
		passable = false;
		vBlock = object.blocksView();
		this.placeObject=false;
	}
	
	public void removeObject(){
		this.passable = true;
		this.placeObject=true;
		this.vBlock=false;
		this.object=null;
		this.oRef=null;
	}
	
	public boolean canPlaceObject(){
		return placeObject;
	}
	
	public boolean isLand(){
		return land;
	}
	
	public void draw (float x, float y){
		terrain.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		if(object != null){
			object.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		}
	}
}
