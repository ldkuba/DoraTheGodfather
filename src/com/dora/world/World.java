package com.dora.world;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.Globals;

public class World {
	
	Image water;
	Image grass;
	Image dirt;
	
	ArrayList <ArrayList<Integer>> terrainGrid;
	ArrayList <ArrayList<Integer>> objectGrid;
	
	public World (){
		try{
			dirt = new Image ("Images/Dirt.png");
			water = new Image ("Images/Water.png");
			grass = new Image ("Images/Grass.png");
		}catch(SlickException e){
			System.err.println("Unable to load map textures!");;
		}
		terrainGrid = new ArrayList<ArrayList<Integer>>(Globals.WORLD_SIZE_X);
		objectGrid = new ArrayList<ArrayList<Integer>>(Globals.WORLD_SIZE_X);
		
		//generate terrain & Initial state of objects in the world.
		fill();
	}
	
	void fill(){
		//testfill
		/*for(int i=0;i<terrainGrid.size();i++){
			for(int j=0;j<size;j++){
				terrainGrid.get(i).add(new Integer(i+j)%3);
			}
		}*/
		
		
		
		//first fill terrain:
		Random rand = new Random();
		int maxWater=500000;
		int waterCount=0;
		for(int i=0; i<Globals.WORLD_SIZE_X; i++){
			terrainGrid.add(new ArrayList<Integer>(Globals.WORLD_SIZE_Y));

			for (int j=0;j<Globals.WORLD_SIZE_Y;j++){
				int waterChance = 1;
				if (j>0&&terrainGrid.get(i).get(j-1)==1){
					waterChance+=33;
				}
				if(i>0&&terrainGrid.get(i-1).get(j)==1){
					waterChance+=33;
				}
				if(i>0&&j<Globals.WORLD_SIZE_Y-1&&terrainGrid.get(i-1).get(j+1)==1){
					waterChance+=33;
				}
				if(rand.nextInt(100)<waterChance-((waterCount / maxWater) * 100)){
					terrainGrid.get(i).add(1);
					waterCount++;
				}else{
					terrainGrid.get(i).add(0);
				}
			}
		}
		
		//for
		
		
	}
	
	public void draw(Integer i, float x, float y){
		
		if(i==0){
			grass.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		}else if(i==1){
			 water.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		}else if(i==2){
			 dirt.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE) ;
		}
	}
	
	public void display (float xOffset, float yOffset){
		
		for (int i = 0 ; i<Globals.SCREEN_WIDTH/Globals.TILE_SIZE+1;i++){
			for(int j = 0;j<Globals.SCREEN_HEIGHT/Globals.TILE_SIZE+1;j++){
				if(i+(int)xOffset<Globals.WORLD_SIZE_X && i + (int)xOffset>0 && i+(int)yOffset<Globals.WORLD_SIZE_Y && i + (int)yOffset>0)
				draw(terrainGrid.get(i+(int)xOffset).get(j+(int)yOffset),i * Globals.TILE_SIZE + xOffset, j*Globals.TILE_SIZE + yOffset);
			}
		}
	}
}
