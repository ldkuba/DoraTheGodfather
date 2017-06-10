package com.dora.world;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.dora.main.Globals;
import com.dora.object.*;

public class World {

	Image water;
	Image grass;
	Image dirt;
	Image tree;
	Image rock;

	ArrayList<Objects> objects;
	ArrayList<ArrayList<Tile>> terrainGrid;

	public World() {
		terrainGrid = new ArrayList<ArrayList<Tile>>(Globals.WORLD_SIZE_X);
		objects = new ArrayList<Objects>();
		
		try{
			water = new Image ("res/Textures/Water.png");
			grass = new Image ("res/Textures/Grass.png");
			dirt = new Image ("res/Textures/Dirt.png");
		}catch(SlickException e){
			
		}
		
		// generate terrain & Initial state of objects in the world.
		fill();
	}

	void fill() {
		// testfill
		/*
		 * for(int i=0;i<terrainGrid.size();i++){ for(int j=0;j<size;j++){
		 * terrainGrid.get(i).add(new Integer(i+j)%3); } }
		 */

		// first fill terrain:
		Random rand = new Random();
		int maxWater = 100;
		int waterCount = 0;
		int waterOffset = rand.nextInt(Globals.WORLD_SIZE_X);
		int waterCounter = 0;
		for (int i = 0; i < Globals.WORLD_SIZE_X; i++) {
			terrainGrid.add(new ArrayList<Tile>(Globals.WORLD_SIZE_Y));
			for (int j = 0; j < Globals.WORLD_SIZE_Y; j++) {
				if (waterOffset <= waterCounter) {
					int waterChance = 100;
					if (j > 0 && !terrainGrid.get(i).get(j - 1).isLand()) {
						waterChance += 46;
					}
					if (i > 0 && !terrainGrid.get(i - 1).get(j).isLand()) {
						waterChance += 46;
					}
					if (i > 0 && j < 0 * (Globals.WORLD_SIZE_Y - 1) && !terrainGrid.get(i - 1).get(j - 1).isLand()) {
						waterChance += 46;
					}
					if (i > 0 && j < (Globals.WORLD_SIZE_Y - 1) && !terrainGrid.get(i - 1).get(j + 1).isLand()) {
						waterChance += 46;
					}
					if (rand.nextInt(100) < waterChance - ((waterCount / maxWater) * 100)) {
						terrainGrid.get(i).add(new Tile(water, false));
						waterCount++;
					} else {
						terrainGrid.get(i).add(new Tile(grass, true));
					}
				} else {
					terrainGrid.get(i).add(new Tile(grass, true));
				}
				
			}
			waterCounter++;
		}
		
		int objectChance =5;
		for(int i=0; i<Globals.WORLD_SIZE_X; i++){
			for (int j=0; j<Globals.WORLD_SIZE_Y;j++){
				if(rand.nextInt(100)<=objectChance){
					int oType = rand.nextInt(2);
					if(oType==0){
						if(canPlaceObject(Rock.sizeX, Rock.sizeY, i, j)){
							//Rock rock = new Rock(i, j);
							addObject(new Rock(i, j), i , j);
						}
					}else if(oType==1){
						if(canPlaceObject(Chest.sizeX, Chest.sizeY, i, j)){
							//Chest chest = new Chest(i, j);
							addObject(new Chest(i, j), i , j);
						}
					}
				}
			}
		}

	}
	
	public boolean canPlaceObject(int sizeX,int sizeY,int x,int y){
		if (sizeX + x > Globals.WORLD_SIZE_X || sizeY + y > Globals.WORLD_SIZE_Y){
			return false;
		}
		for (int i =0; i<sizeX;i++){
			for(int j=0;j<sizeY;j++){
				if(!terrainGrid.get(x+i).get(y+j).canPlaceObject()){
					return false;
				}
			}
		}
		return true;
	}
	
	public void addObject(Objects obj, int x, int y){
		objects.add(obj);
		for (int i =0; i<obj.getSizeX();i++){
			for(int j=0;j<obj.getSizeY();j++){
				if(!terrainGrid.get(x+i).get(j+y).canPlaceObject()){
					System.err.println("what");
				}
				terrainGrid.get(x+i).get(j+y).setObject(obj, obj.getTextures().get(i).get(j));
			}
		}
	}

	public void draw(Integer i, Integer j, float x, float y) {

		if (i == 0) {
			grass.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		} else if (i == 1) {
			water.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		} else if (i == 2) {
			dirt.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		}
		
		if(j == 1){
			tree.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		} else if(j == 2){
			rock.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		}
	}

	public void display(float xOffset, float yOffset) {

		for (int i = 0; i < Globals.SCREEN_WIDTH / Globals.TILE_SIZE + 1; i++) {
			for (int j = 0; j < Globals.SCREEN_HEIGHT / Globals.TILE_SIZE + 1; j++) {
				if (i + (int) xOffset < Globals.WORLD_SIZE_X && i + (int) xOffset > 0 && j + (int) yOffset < Globals.WORLD_SIZE_Y && j + (int) yOffset > 0){
					terrainGrid.get(i + (int) xOffset).get(j + (int) yOffset).draw(i * Globals.TILE_SIZE + xOffset, j * Globals.TILE_SIZE + yOffset);
				}
				
					
			}
		}
	}
}
