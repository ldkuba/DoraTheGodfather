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
	private float[] alphas;

	Tile(Image terrain, boolean land) {
		this.terrain = terrain.copy();
		this.land = land;
		vBlock = false;
		this.passable = land;
		this.placeObject = land;
		this.alphas = new float[4];
	}

	Tile(Image terrain, boolean land, boolean passable) {
		this.terrain = terrain;
		this.land = land;
		vBlock = false;
		this.passable = passable;
		this.placeObject = passable;
		this.alphas = new float[4];
	}

	public void setObject(Objects object, Image tex) {
		this.oRef = object;
		this.object = tex;
		passable = false;
		vBlock = object.blocksView();
		this.placeObject = false;
	}

	public void changePassable(boolean passable) {
		this.passable = passable;
	}

	public void changeVBlock(boolean block) {
		this.vBlock = block;
	}

	public void setAlphas(float[] alphas) {
		int val = 1;
		
		for (int i = 0; i < this.alphas.length; i++) {
			this.alphas[i] = alphas[i];
		}

		//System.out.println(this.alphas[0]);
		//this.terrain.setColor(Image.TOP_LEFT, 0, 0, 0, this.alphas[0]);
		this.terrain.setColor(Image.TOP_RIGHT, 255-this.alphas[1], 255-this.alphas[1], 255-this.alphas[1], 255-this.alphas[1]);
		this.terrain.setColor(Image.BOTTOM_LEFT, 255-this.alphas[2], 255-this.alphas[2], 255-this.alphas[2], 255-this.alphas[2]);
		this.terrain.setColor(Image.BOTTOM_RIGHT, 255-this.alphas[3], 255-this.alphas[3], 255-this.alphas[3],255-this.alphas[3]);
		
		this.terrain.setColor(Image.TOP_LEFT, 255-this.alphas[0], 255-this.alphas[0], 255-this.alphas[0], 255-this.alphas[0]);

		if (this.object != null) {
			this.object.setColor(Image.TOP_LEFT, 255-this.alphas[0], 255-this.alphas[0], 255-this.alphas[0], 255-this.alphas[0]);
			this.object.setColor(Image.TOP_RIGHT, 255-this.alphas[1], 255-this.alphas[1], 255-this.alphas[1], 255-this.alphas[1]);
			this.object.setColor(Image.BOTTOM_LEFT, 255-this.alphas[2], 255-this.alphas[2], 255-this.alphas[2], 255-this.alphas[2]);
			this.object.setColor(Image.BOTTOM_RIGHT, 255-this.alphas[3], 255-this.alphas[3], 255-this.alphas[3], 255-this.alphas[3]);
		}
	}

	public void removeObject() {
		this.passable = true;
		this.placeObject = true;
		this.vBlock = false;
		this.object = null;
		this.oRef = null;
	}

	public boolean blocksView() {
		return vBlock;
	}

	public boolean isPassable() {
		return passable;
	}

	public Objects getObjectReference() {
		return oRef;
	}

	public boolean canPlaceObject() {
		return placeObject;
	}

	public boolean isLand() {
		return land;
	}

	public void draw(float x, float y) {
		terrain.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		if (object != null) {
			object.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		}
	}
}
