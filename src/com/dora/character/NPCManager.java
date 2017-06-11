package com.dora.character;

import java.util.ArrayList;

import com.dora.character.NPC.NPCType;
import com.dora.item.Item;
import com.dora.item.Item1;
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
		Item[] testInv = {new Item1(), new Item1(), new Item1()};
		
		Hunter hunter = new Hunter(this.gameState, NPCType.hunter, 70, 720);
		hunter.setInventory(testInv);
		npcs.add(hunter);
		
		Hunter hunter2 = new Hunter(this.gameState, NPCType.hunter, 140, 720);
		hunter2.setInventory(testInv);
		npcs.add(hunter2);
		
		Hunter hunter3 = new Hunter(this.gameState, NPCType.hunter, 700, 500);
		hunter3.setInventory(testInv);
		npcs.add(hunter3);
		
		Hunter hunter4 = new Hunter(this.gameState, NPCType.hunter, 500, 120);
		hunter4.setInventory(testInv);
		npcs.add(hunter4);
		
		Hunter hunter5 = new Hunter(this.gameState, NPCType.hunter, 800, 320);
		hunter5.setInventory(testInv);
		npcs.add(hunter5);
		
		Hunter hunter6 = new Hunter(this.gameState, NPCType.hunter, 1000, 720);
		hunter6.setInventory(testInv);
		npcs.add(hunter6);
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
