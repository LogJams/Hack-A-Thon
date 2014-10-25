package generation;

import java.util.ArrayList;

import main.Land;

public class RiverGenerator {
	
	public Land[][] world;
	public float lakeLevel;
	public void generate(Land[][] world, float heightThreshold, float lakeLevel){
		this.lakeLevel = lakeLevel;
		this.world = world;
		ArrayList<Land> startOptions = new ArrayList<Land>();
		ArrayList<Land> riverStarts = new ArrayList<Land>();
		for(int i = 0; i < world.length; i++){
			for(int j = 0; j < world[0].length; j++){
				if(world[i][j].height>=heightThreshold){
					startOptions.add(world[i][j]);
				}
			}
		}
		
		int numRivers = (int) Math.random()*startOptions.size();
		int rand = 0;
		for(int i = 0; i < numRivers; i++){
			rand = (int) Math.random()*startOptions.size();
			if(rand == startOptions.size()){
				rand--;
			}
			riverStarts.add(startOptions.get(rand));
			startOptions.remove(rand);
		}
		
		for(int i = 0; i<riverStarts.size(); i++){
			buildRiver(riverStarts.get(i), false);
		}
	}
	
	public boolean buildRiver(Land land, boolean done){
		land.isWet = true;
		int x = land.xIndex;
		int z = land.zIndex;
		if(land.height > lakeLevel){
			buildRiver(world[x-1][z], false);
		}
		return done;
		
	}
}
