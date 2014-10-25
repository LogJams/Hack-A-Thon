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
					j+=10;
					i+=2;
				}
			}
		}
		
		int numRivers = (int) (Math.random()*startOptions.size());
		System.out.println(numRivers);
		int rand = 0;
		for(int i = 0; i < numRivers; i++){
			rand = (int) (Math.random()*startOptions.size());
			if(rand == startOptions.size()){
				rand--;
			}
			riverStarts.add(startOptions.get(rand));
			startOptions.remove(rand);
		}
		
		for(int i = 0; i<riverStarts.size(); i++){
			startRiver(riverStarts.get(i));
		}
	}
	
	public void startRiver(Land land){
		land.isWeet = true;
		int rand = (int)(Math.random()*4);
		if(land.height > lakeLevel){
			buildRiver(land, rand);
		}
	}
	
	public boolean buildRiver(Land land, int direction){
		land.isWeet = true;
		int x = land.x;
		int z = land.z;
		if(land.height > lakeLevel){
			if(direction == 0){
				buildRiver(world[x-1][z], direction);
			}else if(direction == 1){
				buildRiver(world[x+1][z], direction);
			}else if(direction == 2){
				buildRiver(world[x][z-1], direction);
			}else{
				buildRiver(world[x][z+1], direction);
			}
		}
		return true;
	}
}
