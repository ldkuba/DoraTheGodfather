package item;

import org.newdawn.slick.Image;

public class Item
{
	public static enum ItemIDs 
	{
		empty, testItem
	}
	
	public static Image[] itemImages;
	
	public Item() {}
	
	public static void loadImages(String filePath, int squareSize, int number)
	{
		itemImages = new Image[number];
		
		//Load images from spritesheet (TODO)
		
	}
}
