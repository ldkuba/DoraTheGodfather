package com.dora.world;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.dora.main.Globals;
import com.dora.object.*;

import jdk.nashorn.internal.objects.Global;

public class World
{

	Image water;
	Image grass;
	Image dirt;
	Image tree;
	Image rock;
	Image mountainTopTR;
	Image	mountainTopTL;
	Image	mountainTopBL;
	Image	mountainTopBR;
	
	ArrayList<Objects> objects;
	ArrayList<ArrayList<Tile>> terrainGrid;

	public World()
	{
		terrainGrid = new ArrayList<ArrayList<Tile>>(Globals.WORLD_SIZE_X);
		objects = new ArrayList<Objects>();

		try
		{
			water = new Image("res/Textures/Water.png");
			grass = new Image("res/Textures/Grass.png");
			dirt = new Image("res/Textures/Dirt.png");
			mountainTopTR =new Image("res/Textures/MountainTopTR.png");
			mountainTopTL = new Image("res/Textures/MountainTopTL.png");
			mountainTopBL =new Image("res/Textures/MountainTopBL.png");
			mountainTopBR =new Image("res/Textures/MountainTopBR.png");
		}catch (SlickException e)
		{
			e.printStackTrace();
		}
		// generate terrain & Initial state of objects in the world.
		fill();
	}

	void fill()
	{
		
		for(int i=0;i<Globals.WORLD_SIZE_X;i++){
			terrainGrid.add(new ArrayList<Tile>(Globals.WORLD_SIZE_Y));
			for(int j=0; j<Globals.WORLD_SIZE_Y;j++){
				/*if(i%2==0&&j==0){
					terrainGrid.get(i).add(new Tile(mountainTopTL, true, false));
					System.out.println("hi");
				}else if(i%2==1&&j==0){
					terrainGrid.get(i).add(new Tile(mountainTopTR, true, false));
				}else if(i%2==0&&j==1){
					terrainGrid.get(i).add(new Tile(mountainTopBL, true, false));
				}else if(i%2==1&&j==1){
					terrainGrid.get(i).add(new Tile(mountainTopBR, true, false));
				}
				
				else if(i==0&&j%2==1&&j>1){
					mountainTopTL.setRotation(270);
					terrainGrid.get(i).add(new Tile(mountainTopTL, true, false));
				}else if(i==0&&j%2==0){
					mountainTopTR.setRotation(270);
					terrainGrid.get(i).add(new Tile(mountainTopTR, true, false));
				}else if(i==1&&j%2==1){
					mountainTopBL.setRotation(270);
					terrainGrid.get(i).add(new Tile(mountainTopBL, true, false));
				}else if(i==1&&j%2==0){
					mountainTopBR.setRotation(270);
					terrainGrid.get(i).add(new Tile(mountainTopBR, true, false));
				}
				
				else if(i%2==1&&j==Globals.WORLD_SIZE_Y-1){
					mountainTopTL.setRotation(180);
					terrainGrid.get(i).add(new Tile(mountainTopTL, true, false));
				}else if(i%2==0&&j==Globals.WORLD_SIZE_Y-1){
					mountainTopTR.setRotation(180);
					terrainGrid.get(i).add(new Tile(mountainTopTR, true, false));
				}else if(i%2==1&&j==Globals.WORLD_SIZE_Y-2){
					mountainTopBL.setRotation(180);
					terrainGrid.get(i).add(new Tile(mountainTopBL, true, false));
				}else if(i%2==0&&j==Globals.WORLD_SIZE_Y-2){
					mountainTopBR.setRotation(180);
					terrainGrid.get(i).add(new Tile(mountainTopBR, true, false));
				}
				
				else if(i==Globals.WORLD_SIZE_X-1&&j%2==0){
					mountainTopTL.setRotation(90);
					terrainGrid.get(i).add(new Tile(mountainTopTL, true, false));
				}else if(i==Globals.WORLD_SIZE_X-1&&j%2==0){
					mountainTopTR.setRotation(90);
					terrainGrid.get(i).add(new Tile(mountainTopTR, true, false));
				}else if(i==Globals.WORLD_SIZE_X-2&&j%2==1){
					mountainTopBL.setRotation(90);
					terrainGrid.get(i).add(new Tile(mountainTopBL, true, false));
				}else if(i==Globals.WORLD_SIZE_X-2&&j%2==1){
					mountainTopBR.setRotation(90);
					terrainGrid.get(i).add(new Tile(mountainTopBR, true, false));
				}else{*/
					terrainGrid.get(i).add(null);
			
				//}
			}
		}
		
		// testfill
		/*
		 * for(int i=0;i<terrainGrid.size();i++){ for(int j=0;j<size;j++){
		 * terrainGrid.get(i).add(new Integer(i+j)%3); } }
		 */

		// first fill terrain:
		Random rand = new Random();
		int maxWater = 100;
		int waterCount = 0;
		//int waterOffset = rand.nextInt(Globals.WORLD_SIZE_X);
		//int waterCounter = 0;

		ArrayList<Integer> randomX = new ArrayList<Integer> (Globals.WORLD_SIZE_X);	//random sampling without replacement
		ArrayList<Integer> randomY = new ArrayList<Integer> (Globals.WORLD_SIZE_Y);
		
		for(int i=0;i<Globals.WORLD_SIZE_X;i++){
				randomX.add(i);
		}
		
		
		for (int i = 0; i < Globals.WORLD_SIZE_X; i++)
		{
			int r =rand.nextInt(randomX.size());
			//System.out.println(randomX.size());
			int x = randomX.get(r).intValue();
			randomX.remove(r);
			for(int ii=0;ii<Globals.WORLD_SIZE_Y;ii++){
				randomY.add(ii);
			}
			for (int j = 0; j < Globals.WORLD_SIZE_Y; j++){
					//System.out.println(j+ " "+ randomY.size());
					int rr =rand.nextInt(randomY.size());
					int y = randomY.get(rr).intValue();
					randomY.remove(rr);
				
					
					System.out.println(j+ " "+ randomY.size()+ " "+ x + " " +y);
				
				if(x%2==0&&y==0){ //first fill static objects - world border ect.
					terrainGrid.get(x).set(y, new Tile(mountainTopTL.copy(), true, false));
					System.out.println("hi");
				}else if(x%2==1&&y==0){
					terrainGrid.get(x).set(y, new Tile(mountainTopTR.copy(), true, false));
				}else if(x%2==0&&y==1){
					terrainGrid.get(x).set(y, new Tile(mountainTopBL.copy(), true, false));
				}else if(x%2==1&&y==1){
					terrainGrid.get(x).set(y, new Tile(mountainTopBR.copy(), true, false));
				}
				
				else if(x==0&&y%2==1&&y>1){
					mountainTopTL.setRotation(270);
					terrainGrid.get(x).set(y, new Tile(mountainTopTL.copy(), true, false));
				}else if(x==0&&y%2==0){
					mountainTopTR.setRotation(270);
					terrainGrid.get(x).set(y, new Tile(mountainTopTR.copy(), true, false));
				}else if(x==1&&y%2==1){
					mountainTopBL.setRotation(270);
					terrainGrid.get(x).set(y, new Tile(mountainTopBL.copy(), true, false));
				}else if(x==1&&y%2==0){
					mountainTopBR.setRotation(270);
					terrainGrid.get(x).set(y, new Tile(mountainTopBR.copy(), true, false));
				}
				
				else if(x%2==1&&y==Globals.WORLD_SIZE_Y-1){
					mountainTopTL.setRotation(180);
					terrainGrid.get(x).set(y, new Tile(mountainTopTL.copy(), true, false));
				}else if(x%2==0&&y==Globals.WORLD_SIZE_Y-1){
					mountainTopTR.setRotation(180);
					terrainGrid.get(x).set(y, new Tile(mountainTopTR.copy(), true, false));
				}else if(x%2==1&&y==Globals.WORLD_SIZE_Y-2){
					mountainTopBL.setRotation(180);
					terrainGrid.get(x).set(y, new Tile(mountainTopBL.copy(), true, false));
				}else if(x%2==0&&y==Globals.WORLD_SIZE_Y-2){
					mountainTopBR.setRotation(180);
					terrainGrid.get(x).set(y, new Tile(mountainTopBR.copy(), true, false));
				}
				
				else if(x==Globals.WORLD_SIZE_X-1&&y%2==0){
					mountainTopTL.setRotation(90);
					terrainGrid.get(x).set(y, new Tile(mountainTopTL.copy(), true, false));
				}else if(x==Globals.WORLD_SIZE_X-1&&y%2==0){
					mountainTopTR.setRotation(90);
					terrainGrid.get(x).set(y, new Tile(mountainTopTR.copy(), true, false));
				}else if(x==Globals.WORLD_SIZE_X-2&&y%2==1){
					mountainTopBL.setRotation(90);
					terrainGrid.get(x).set(y, new Tile(mountainTopBL.copy(), true, false));
				}else if(x==Globals.WORLD_SIZE_X-2&&y%2==1){
					mountainTopBR.setRotation(90);
					terrainGrid.get(x).set(y, new Tile(mountainTopBR.copy(), true, false));
				}else{
					int waterChance = 1;
					for(int wI = -1; wI<2;wI++){
						for(int wJ = -1; wJ <2; wJ++){
							if( x+wI>0 && x+wI < Globals.WORLD_SIZE_X-1 && y+wJ>0 && y+wJ<Globals.WORLD_SIZE_Y-1 &&terrainGrid.get(wI+x).get(wJ+y)!=null && !terrainGrid.get(wI+x).get(wJ+y).isLand()){
								waterChance +=46;
							}
						}
					}
					/*if(terrainGrid.get(x).get(y-1)!=null && y > 2 && !terrainGrid.get(x).get(y - 1).isLand())
					{
						waterChance += 46;
					}
					if(x > 0 && !terrainGrid.get(x - 1).get(y).isLand())
					{
						waterChance += 46;
					}
					if(x > 0 && y >2 && !terrainGrid.get(x - 1).get(y - 1).isLand())
					{
						waterChance += 46;
					}
					if(x > 0&& y < (Globals.WORLD_SIZE_Y - 1) && !terrainGrid.get(x - 1).get(y+ 1).isLand())
					{
						waterChance += 46;
					}*/
					if(rand.nextInt(100) < waterChance - ((waterCount / maxWater) * 100))
					{
						//System.out.println(j+ " "+ randomY.size()+ " "+ x + " " +y);
							terrainGrid.get(x).set(y, (new Tile(water, false)));
							waterCount++;
					}else
					{

							terrainGrid.get(x).set(y, new Tile(grass, true));
					}
				}
					
				//}else
				//{
				//	terrainGrid.get(i).add(new Tile(grass, true));

			}
			
			//waterCounter++;
		}
		System.out.println("");
		
		int objectChance = 5;
		for (int i = 0; i < Globals.WORLD_SIZE_X; i++)
		{
			for (int j = 0; j < Globals.WORLD_SIZE_Y; j++)
			{
				if(rand.nextInt(100) <= objectChance)
				{
					int oType = rand.nextInt(3);
					if(oType == 0)
					{
						if(canPlaceObject(Rock.sizeX, Rock.sizeY, i, j))
						{
							// Rock rock = new Rock(i, j);
							addObject(new Rock(i, j), i, j);
						}
					}else if(oType == 1)
					{
						if(canPlaceObject(Chest.sizeX, Chest.sizeY, i, j))
						{
							// Chest chest = new Chest(i, j);
							addObject(new Chest(i, j), i, j);
						}
					}else if(oType == 2)
					{
						if(canPlaceObject(Tree.sizeX, Tree.sizeY, i, j))
						{
							// Chest chest = new Chest(i, j);
							addObject(new Tree(i, j), i, j);
						}
					}
				}
			}
		}

	}
	

	public boolean canPlaceObject(int sizeX, int sizeY, int x, int y)
	{
		if(sizeX + x > Globals.WORLD_SIZE_X || sizeY + y > Globals.WORLD_SIZE_Y)
		{
			return false;
		}
		for (int i = 0; i < sizeX; i++)
		{
			for (int j = 0; j < sizeY; j++)
			{
				System.out.println(x+i + " " +j+y);
				if(!terrainGrid.get(x + i).get(y + j).canPlaceObject())
				{
					return false;
				}
			}
		}
		return true;
	}

	public boolean isPassable(int x, int y)
	{
		return terrainGrid.get(x).get(y).isPassable();
	}

	public Objects getObject(int x, int y)
	{
		return terrainGrid.get(x).get(y).getObjectReference();
	}

	public void addObject(Objects obj, int x, int y)
	{
		objects.add(obj);
		for (int i = 0; i < obj.getSizeX(); i++)
		{
			for (int j = 0; j < obj.getSizeY(); j++)
			{
				if(!terrainGrid.get(x + i).get(j + y).canPlaceObject())
				{
					System.err.println("lolwat");
				}
				
				terrainGrid.get(x + i).get(j + y).setObject(obj, obj.getTextures().get(i).get(j));
				if(obj instanceof Tree){
					if(!(i==1&&j==1)){
						terrainGrid.get(x+i).get(j+y).changePassable(true);	
						terrainGrid.get(x+i).get(j+y).changeVBlock(false);	
					}

				}
			}
		}
	}
	
	public Tile tileAtPixel(int x, int y){
		int retX = 0, retY =0;
		for(int i=0;i<Globals.WORLD_SIZE_X;i++){
			if((i+1)*Globals.TILE_SIZE>x){
				retX=i;
				break;
			}
		}
		for(int i=0;i<Globals.WORLD_SIZE_Y;i++){
			if((i+1)*Globals.TILE_SIZE>y){
				retY=i;
				break;
			}
		}
		return terrainGrid.get(retX).get(retY);
	}
	
	public float playerDist(int i, int j, int xOffset, int yOffset){
		return (float) Math.sqrt(Math.pow((i -(xOffset+Globals.SCREEN_WIDTH/2)), 2) +Math.pow((j -(yOffset+Globals.SCREEN_HEIGHT/2)), 2));
	}
	
	public boolean canSee(int x, int y, int xOffset, int yOffset){
		double xx=xOffset+Globals.SCREEN_WIDTH/2;
		double yy=yOffset+Globals.SCREEN_HEIGHT/2;
		double a=0;
		
		/*while ((Math.floor(xx) != x && Math.floor(yy) != y)){
			System.out.println(xx+ " "+Math.floor(xx) + " " + yy +" "+Math.floor(yy)+ " " + x + " " + y);
			if(x-xx == 0){
				a=Math.PI;
			}else{
				a = (yy-y)/(xx - x);
			}
			if((Math.floor(xx)) != x){
				xx+= Math.cos(a);
			}
			if(Math.floor(yy) != y){
				yy+=Math.sin(a); 
			}
			
			if(tileAtPixel((int)xx, (int)yy).blocksView()){
				return false;
			}
		}*/
		int dist = (int) Math.floor(playerDist(x,y,xOffset,yOffset));
		for(int i = 0; i<dist;i++){
			xx+= (i/dist)*(x-xx);
			yy+= (i/dist)*(y-yy);
		}
		if(tileAtPixel((int)Math.floor(xx), (int) Math.floor(yy)).blocksView()){
			//System.out.println("cantSee");
			return false;
			
		}
		//System.out.println("cansee");
		return true;
	}
	
	public float [] getAlphas(int x, int y, int xOffset,int yOffset){
		float [] retAlphas = new float [4];
		int maxDist = Globals.SCREEN_HEIGHT/3;
		int xx = xOffset+Globals.SCREEN_WIDTH/2;
		int yy = yOffset+Globals.SCREEN_HEIGHT/2;
		int tX = x*Globals.TILE_SIZE;
		int tY =y*Globals.TILE_SIZE;
		if(playerDist(tX, tY, xOffset, yOffset)>maxDist){
				retAlphas[0]=255;
		}else{
			if(canSee(tX, tY, xOffset, yOffset)){
				retAlphas[0]=Math.abs(playerDist(tX, tY, xOffset, yOffset))/100; //255 *(Math.min(((playerDist(tX, tY, xOffset, yOffset))-maxDist/2), 0)/1000);
			}else{
				retAlphas[0]=255;
			}
		}
		
		if(playerDist(tX+Globals.TILE_SIZE, tY, xOffset, yOffset)>maxDist){
			retAlphas[1]=255;
		}else{
			if(canSee(tX+Globals.TILE_SIZE, tY, xOffset, yOffset)){
				retAlphas[1]= Math.abs(playerDist(tX, tY, xOffset, yOffset))/100;//255 * (Math.min(((playerDist(tX, tY, xOffset, yOffset))-maxDist/2), 0)/1000);
			}else{
				retAlphas[0]=255;
			}
		}
		
		if(playerDist(tX, tY+Globals.TILE_SIZE, xOffset, yOffset)>maxDist){
			retAlphas[2]=255;
		}else{
			if(canSee(tX, tY+Globals.TILE_SIZE, xOffset, yOffset)){
				retAlphas[2]=Math.abs(playerDist(tX, tY, xOffset, yOffset))/100; // 255 *(Math.min(((playerDist(tX, tY, xOffset, yOffset))-maxDist/2), 0)/1000);
			}else{
				retAlphas[0]=255;
			}
		}
		
		if(playerDist(tX+Globals.TILE_SIZE, tY+Globals.TILE_SIZE, xOffset, yOffset)>maxDist){
			retAlphas[3]=255;
		}else{
			if(canSee(tX+Globals.TILE_SIZE, tY+Globals.TILE_SIZE, xOffset, yOffset)){
				retAlphas[3]=Math.abs(playerDist(tX, tY, xOffset, yOffset))/100; //255 *(Math.min(((playerDist(tX, tY, xOffset, yOffset))-maxDist/2), 0)/1000);
			}else{
				retAlphas[0]=255;
			}
		}
		
		return retAlphas;
	}
	

	public void display(float xOffset, float yOffset) {

		for (int j = 0; j < Globals.WORLD_SIZE_Y; j++)
		{
			for (int i = 0; i < Globals.WORLD_SIZE_Y; i++)
			{
				if(((i+1)*Globals.TILE_SIZE) + xOffset > 0 && i*Globals.TILE_SIZE + xOffset <= Globals.SCREEN_WIDTH && (j+1)*Globals.TILE_SIZE + yOffset > 0 && j*Globals.TILE_SIZE + yOffset < Globals.SCREEN_HEIGHT)
				{
					terrainGrid.get(i).get(j).setAlphas(getAlphas(i, j, (int)-xOffset, (int)-yOffset));
					terrainGrid.get(i).get(j).draw((i * Globals.TILE_SIZE) + xOffset, (j * Globals.TILE_SIZE) + yOffset);
				}
			}
		}
	}
}
