package com.dora.world;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class World {
	
	Image water;
	Image grass;
	Image dirt;
	
	ArrayList <ArrayList<Integer>> grid;
	int size =0;
	
	public World (int initSize){
		try{
			dirt = new Image ("Images/Dirt.png");
			water = new Image ("Images/Water.png");
			grass = new Image ("Images/Grass.png");
		}catch(SlickException e){
			System.err.println("Unable to load map textures!");;
		}
		grid = new ArrayList<ArrayList<Integer>>(initSize);
		for(int i=0; i<initSize; i++){
			grid.add(new ArrayList<Integer>(initSize));
		}
		size = initSize;
	}
	
	void fill(){
		for(int i=0;i<grid.size();i++){
			for(int j=0;j<size;j++){
				grid.get(i).add(new Integer(i%4));
			}
		}
	}
	
	public void draw(int i, int x, int y){
		switch (i) {
			case 0: grass.draw(x, y);
			case 1: water.draw(x, y);
			case 3: dirt.draw(x, y);
		}
	}
	
	public void display (int xOffset, int yOffset){
		for (int i = 0 ; i<grid.size();i++){
			for(int j = 0;j<grid.get(i).size();j++){
				draw(grid.get(i).get(j),i * xOffset, j*yOffset);
			}
		}
	}
}
