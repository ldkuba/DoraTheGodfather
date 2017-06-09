package item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Item
{
	public static enum ItemIDs 
	{
		empty, one
	}
	
	public static Image[] itemImages;
	
	protected ItemIDs id;
	
	public Item() {}
	
	public static void loadImages(String filePath, int squareSize, int number)
	{
		itemImages = new Image[number];
		
		Image sSImage = null;
		
		try
		{
			sSImage = new Image(filePath);
		}catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		SpriteSheet sS = new SpriteSheet(sSImage, squareSize, squareSize);
		
		for(int j = 0; j < Math.sqrt(number); j++)
		{
			for(int i = 0; i < Math.sqrt(number); i++)
			{
				itemImages[i*j] = sS.getSubImage(i, j);
			}
		}
		
	}
	
	public ItemIDs getId()
	{
		return this.id;
	}
}
