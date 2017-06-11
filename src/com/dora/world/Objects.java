package com.dora.world;

import java.util.ArrayList;

import org.newdawn.slick.Image;

public class Objects {
	private int sizeX;
	private int sizeY;
	
	private int xCoord;
	private int yCoord;
	private int angle;
	
	private boolean blocks;
	
	private ArrayList <ArrayList <Image>> textures;
	
	public Objects(int sizeX, int sizeY, int xCoord, int yCoord, boolean blocks){
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		this.xCoord=xCoord;
		this.yCoord=yCoord;
		this.blocks=blocks;
		textures= new ArrayList<ArrayList<Image>>();
	}
	
	public boolean blocksView(){
		return blocks;
	}
	
	public ArrayList<ArrayList<Image>> getTextures(){
		return textures;
	}
	
	public int getSizeX(){
		return sizeX;
	}
	public int getSizeY(){
		return sizeY;
	}
	public int getX(){
		return xCoord;
	}
	public int getY(){
		return yCoord;
	}
}
