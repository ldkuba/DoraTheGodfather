package com.dora.world;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.dora.main.Globals;

public class World
{

	Image water;
	Image grass;
	Image dirt;
	Image tree;
	Image rock;

	ArrayList<ArrayList<Integer>> terrainGrid;
	ArrayList<ArrayList<Integer>> objectGrid;

	public World()
	{
		try
		{
			dirt = new Image("res/textures/Dirt.png");
			water = new Image("res/textures/Water.png");
			grass = new Image("res/textures/Grass.png");
			rock = new Image("res/textures/Rock.png");
			tree = new Image("res/textures/Tree.png");
		}catch (SlickException e)
		{
			System.err.println("Unable to load map textures!");
			;
		}
		terrainGrid = new ArrayList<ArrayList<Integer>>(Globals.WORLD_SIZE_X);
		objectGrid = new ArrayList<ArrayList<Integer>>(Globals.WORLD_SIZE_X); // grid
																				// for
																				// objects,
																				// 0
																				// means
																				// empty

		// generate terrain & Initial state of objects in the world.
		fill();
	}

	void fill()
	{
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
		for (int i = 0; i < Globals.WORLD_SIZE_X; i++)
		{
			terrainGrid.add(new ArrayList<Integer>(Globals.WORLD_SIZE_Y));
			objectGrid.add(new ArrayList<Integer>(Globals.WORLD_SIZE_Y));

			for (int j = 0; j < Globals.WORLD_SIZE_Y; j++)
			{
				if(waterOffset <= waterCounter)
				{
					int waterChance = 1;
					if(j > 0 && terrainGrid.get(i).get(j - 1) == 1)
					{
						waterChance += 46;
					}
					if(i > 0 && terrainGrid.get(i - 1).get(j) == 1)
					{
						waterChance += 46;
					}
					if(i > 0 && j < 0 * (Globals.WORLD_SIZE_Y - 1) && terrainGrid.get(i - 1).get(j - 1) == 1)
					{
						waterChance += 46;
					}
					if(i > 0 && j < (Globals.WORLD_SIZE_Y - 1) && terrainGrid.get(i - 1).get(j + 1) == 1)
					{
						waterChance += 46;
					}
					if(rand.nextInt(100) < waterChance - ((waterCount / maxWater) * 100))
					{
						terrainGrid.get(i).add(1);
						waterCount++;
					}else
					{
						terrainGrid.get(i).add(0);
					}
				}else
				{
					terrainGrid.get(i).add(0);
				}
				objectGrid.get(i).add(0);

			}
			waterCounter++;
		}

		int objectChance = 100;
		for (int i = 0; i < Globals.WORLD_SIZE_X; i++)
		{
			for (int j = 0; j < Globals.WORLD_SIZE_Y; j++)
			{
				if(rand.nextInt(100) < objectChance)
				{
					if(objectGrid.get(i).get(j) == 0)
					{
						objectGrid.get(i).set(j, rand.nextInt() % 2 + 1);
					}
				}
			}
		}

	}

	public void draw(Integer i, Integer j, int x, int y)
	{

		if(i == 0)
		{
			grass.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		}else if(i == 1)
		{
			water.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		}else if(i == 2)
		{
			dirt.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		}

		if(j == 1)
		{
			tree.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		}else if(j == 2)
		{
			rock.draw(x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
		}
	}

	public void display(int xOffset, int yOffset)
	{
		for (int j = 0; j < Globals.WORLD_SIZE_Y; j++)
		{
			for (int i = 0; i < Globals.WORLD_SIZE_Y; i++)
			{
				if(((i+1)*Globals.TILE_SIZE) + xOffset > 0 && i*Globals.TILE_SIZE + xOffset <= Globals.SCREEN_WIDTH && (j+1)*Globals.TILE_SIZE + yOffset > 0 && j*Globals.TILE_SIZE + yOffset < Globals.SCREEN_HEIGHT)
				{
					draw(terrainGrid.get(i).get(j),
						 objectGrid.get(i).get(j),
						 (i * Globals.TILE_SIZE) + xOffset,
						 (j * Globals.TILE_SIZE) + yOffset);
				}
			}
		}
	}
}
