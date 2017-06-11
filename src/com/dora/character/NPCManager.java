package com.dora.character;

import java.util.ArrayList;

import com.dora.character.NPC.NPCType;
import com.dora.main.GameState;

public class NPCManager
{
	private ArrayList<NPC> npcs;
	
	private GameState gameState;
	
	public NPCManager(GameState gs)
	{
		this.gameState = gs;
		
		npcs = new ArrayList<NPC>();
		
		generateNPCS();
	}
	
	private void generateNPCS()
	{
		Hunter hunter = new Hunter(this.gameState, NPCType.hunter, 10, 10);
		npcs.add(hunter);
	}
	
	public void update(float tpf)
	{
		for(int i = 0; i < npcs.size(); i++)
		{
			npcs.get(i).update(tpf);
		}
	}
	
	public void render(int xOffset, int yOffset)
	{
		for(int i = 0; i < npcs.size(); i++)
		{
			npcs.get(i).render(xOffset, yOffset);
		}
	}
}
