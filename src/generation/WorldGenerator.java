package generation;

import main.Land;

public class WorldGenerator {

	//get in world and change the height
	float xFreq = 5;
	float zFreq = 8;
	float[][] functionResults;
	int prime1 = 15731;
	int prime2 = 789221;
	int prime3 = 1376312589;
	Land[][] world;


	public void generate(Land[][] world) {
		this.world = world;
		functionResults = new float[(int) (world.length/xFreq)][(int) (world[0].length/zFreq)];
		int x = 0;
		int z = 0;
		for(int i = 0; i<functionResults.length; i++){
			for(int j = 0; j<functionResults[0].length; j++){
				functionResults[i][j] = function(x,z);
				z+=zFreq;
			}
			x+=xFreq;
		}

		for(int i = 0; i < world.length; i++){
			for(int j = 0; j<world[0].length; j++){
				world[i][j] = new Land(interpolate(i,j));
			}
		}
	}

	public float function(int x, int z){
		x = (z^x) << (x)^z;
		//x = (x<<13)^x;
		return Math.abs((1.0f - ((x * (x * x * prime1 + prime2) + prime3) & 0x7fffffff) * 0.000000000931322574615478515625f));

	}

	public float interpolate(int x, int z){
		System.out.println("x: "+x+", z: "+z);
		x+=(xFreq);
		System.out.println("functionResults["+functionResults.length+"]["+functionResults[0].length+"]");
		int altx = x-2;
		float[] interps = new float[4];
		for(int i = 0; i<4; i++){
			System.out.println("i: "+i);
			int left = (int) (altx/xFreq);
			System.out.println("left: "+left);
			int right = left+1;

			if(left <= 0){
				if(right >= world.length){
					interps[i] = cubicInterpolate(functionResults[left][z], functionResults[left][z], functionResults[right][z], functionResults[right][z], (int)(x%xFreq));
				}else{
					interps[i] = cubicInterpolate(functionResults[left][z], functionResults[left][z], functionResults[right][z], functionResults[right+1][z], (int)(x%xFreq));
				}
			}else if(right >= world.length){
				interps[i] = cubicInterpolate(functionResults[left-1][z], functionResults[left][z], functionResults[right][z], functionResults[right][z], (int)(x%xFreq));
			}
			else{
				interps[i] = cubicInterpolate(functionResults[left-1][z], functionResults[left][z], functionResults[right][z], functionResults[right+1][z], (int)(x%xFreq));
			}
			altx++;
			if(altx == x){
				altx++;
			}
		}

		float fullInterp = cubicInterpolate(interps[0], interps[1], interps[2], interps[3], x);

		return fullInterp;
	}

	private float cubicInterpolate(float v0, float v1, float v2, float v3, float x) {
		float P = (v3 - v2) - (v0 - v1);
		float Q = (v0 - v1) - P;
		float R = v2 - v0;
		float S = v1;

		return P*x*x*x + Q*x*x + R*x + S;
	}
}
