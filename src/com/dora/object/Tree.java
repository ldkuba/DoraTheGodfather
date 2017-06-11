package com.dora.object;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.dora.world.Objects;

public class Tree extends Objects {

	public static int sizeX = 3;
	public static int sizeY = 3;
	
	public Tree(int xCoord, int yCoord) {
		super(sizeX, sizeY, xCoord, yCoord);
		
		getTextures().add(new ArrayList<Image>());
		getTextures().add(new ArrayList<Image>());
		getTextures().add(new ArrayList<Image>());
		
		try {
			getTextures().get(0).add(new Image("res/Textures/TreeTopLeft.png"));
			getTextures().get(0).add(new Image("res/Textures/TreeMidLeft.png"));
			getTextures().get(0).add(new Image("res/Textures/TreeBottomLeft.png"));
			getTextures().get(1).add(new Image("res/Textures/TreeTopMid.png"));
			getTextures().get(1).add(new Image("res/Textures/TreeMidMid.png"));
			getTextures().get(1).add(new Image("res/Textures/TreeBottomMid.png"));
			getTextures().get(2).add(new Image("res/Textures/TreeTopRight.png"));
			getTextures().get(2).add(new Image("res/Textures/TreeMidRight.png"));
			getTextures().get(2).add(new Image("res/Textures/TreeBottomRight.png"));
		} catch (SlickException e) {
			System.err.println("unable to load tree textures!");
			e.printStackTrace();
		}
	}

}
