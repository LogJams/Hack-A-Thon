package generation;

import java.util.Random;

import main.Land;

public class AirGenerator {

	private Random rGen = new Random();

	public float[][] generate(Land[][] world, float threshold) {
		//build a boolean array of regions higher than threshold
		int width = world.length;
		int depth = world[0].length;
		boolean[][] height = new boolean[width][depth];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < depth; j++) {
				height[i][j] = (world[i][j].height >= threshold);
				if (height[i][j]) {
					System.out.print("X ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println("");
		}
		float[][] pressure = null;
		//pick air direction, X or Z, + or - 1
		int dir = 1 + Math.round(rGen.nextFloat() - 1) * 2; //direction is +1 or -1
		if (rGen.nextFloat() > 0.5) {
			pressure = flowX(dir, height);
		} else {
			pressure = flowZ(dir, height);
		}
		return pressure;
	}

	private float[][] flowX(int dir, boolean[][] height) {
		//starting on one side, flow to the other
		float[][] pressure = null;
		
		return pressure;
	}

	private float[][] flowZ(int dir, boolean[][] height) {
		//starting on one side, flow to the other
		float[][] pressure = null;
		
		return pressure;
	}
}
