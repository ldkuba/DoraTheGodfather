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
		Hunter hunter = new Hunter(this.gameState, NPCType.hunter, 70, 720);
		npcs.add(hunter);
		
		Hunter hunter2 = new Hunter(this.gameState, NPCType.hunter, 140, 720);
		npcs.add(hunter2);
		
		Hunter hunter3 = new Hunter(this.gameState, NPCType.hunter, 300, 720);
		npcs.add(hunter3);
	}
	
	public ArrayList<NPC> getNPCs()
	{
		return this.npcs;
	}
	
	public void addNPC(NPC npc)
	{
		this.npcs.add(npc);
	}
	
	public void removeNPC(NPC npc)
	{
		this.npcs.remove(npc);
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
