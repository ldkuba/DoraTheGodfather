package com.dora.object;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.dora.world.Objects;;

public class Chest extends Objects {

	public static int sizeX = 2;
	public static int sizeY = 1;
	public Chest(int xCoord, int yCoord) {
		super(sizeX, sizeY, xCoord, yCoord);
		
		try{
		getTextures().add(new ArrayList<Image>());
		getTextures().get(0).add(new Image("Images/ChestLeft"));
		getTextures().get(0).add(new Image("Images/ChestRight"));
		}catch(SlickException e){
			System.err.println("Unable to load Chest textures!!");
		}
	}

}
