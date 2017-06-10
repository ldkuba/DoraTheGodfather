package com.dora.object;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.dora.world.Objects;
public class Rock extends Objects {
	
	public static int sizeX = 1;
	public static int sizeY =1;
	
	public Rock(int xCoord, int yCoord) {
		super(sizeX, sizeY, xCoord, yCoord);
		getTextures().add((new ArrayList<Image>()));
		try{
		getTextures().get(0).add(new Image("res/Textures/Rock.png"));
		}catch(SlickException e){
			System.err.println("Unable to load rock tex!");
		}
	}
}
