package generation;

import main.Land;

public class RadialGradient {

	public void generate(Land[][] world) {
		int width = world.length;
		int depth = world[0].length;
		int centerX = width/2;
		int centerZ = depth/2;
		float maxDist = (float)Math.sqrt((centerX)*(centerX) + (centerZ)*(centerZ));
		System.out.println(maxDist);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < depth; j++) {
				float grad = 1-((float)Math.sqrt((i-centerX)*(i-centerX) + (j-centerZ)*(j-centerZ))/maxDist);
				grad *= grad*grad*grad*3;
				world[i][j].height *= grad;
			}
		}
	}

}
