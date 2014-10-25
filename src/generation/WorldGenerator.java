package generation;

import main.Land;

public class WorldGenerator {

	//get in world and change the height
	int xFreq = 5;
	int zFreq = 8;
	float[][] functionResults;
	int prime1 = 15731;
	int prime2 = 789221;
	int prime3 = 1376312589;
	Land[][] world;


	public void generate(Land[][] world, int heightScale) {
		this.world = world;
		functionResults = new float[(int) (world.length/xFreq)+4][(int) (world[0].length/zFreq)+4];
		int x = 0;
		int z = 0;
		for(int i = 0; i<functionResults.length; i++){
			for(int j = 0; j<functionResults[0].length; j++){
				functionResults[i][j] = function(x-2*xFreq,z-2*zFreq);
				z+=zFreq;
			}
			x+=xFreq;
		}
		System.out.println("world[0]: "+world[0].length);
		for(int i = 0; i < world.length; i++){
			for(int j = 0; j<world[0].length; j++){
				world[i][j] = new Land(heightScale*interpolate(i,j));
			}
		}
	}

	public float function(int x, int z){
		x = (z^x) << (x)^z;
		//x = (x<<13)^x;
		return Math.abs((1.0f - ((x * (x * x * prime1 + prime2) + prime3) & 0x7fffffff) * 0.000000000931322574615478515625f));

	}

	public float interpolate(int x, int z){
		int left= (int) (x/xFreq)+1;
		int back = (z/zFreq);
		System.out.println("left: "+left);
		float[] interps = new float[4];
		for(int i = 0; i < 4; i++){
			System.out.println("back: "+back);
			interps[i] =  cubicInterpolate(functionResults[left-1][back], functionResults[left][back], functionResults[left+1][back], functionResults[left+2][back], ((float)(x%xFreq))/xFreq);
			back++;
		}
		return cubicInterpolate(interps[0], interps[1], interps[2], interps[3], ((float)(z%zFreq))/z);
		/*z = (int) (z/zFreq);
		x+=(xFreq);
		int altx = x-2;
		System.out.println("functionResults[0].length: "+functionResults[0].length);
		float[] interps = new float[4];
		for(int i = 0; i<4; i++){
			int left = (int) (altx/xFreq);
			int right = left+1;
			
			System.out.println("Altx: "+altx);
			System.out.println("left: "+left);
			System.out.println("right: "+right);
			System.out.println("z: "+z);
			if(z >= functionResults[0].length-1){
				z = functionResults[0].length-1;
			}
			if(left <= 0){
				left = 0;
				if(right+1 >= functionResults.length){
					right = functionResults.length-1;
					interps[i] = cubicInterpolate(functionResults[left][z], functionResults[left][z], functionResults[right][z], functionResults[right][z], ((altx%xFreq)/xFreq));
				}else{
					interps[i] = cubicInterpolate(functionResults[left][z], functionResults[left][z], functionResults[right][z], functionResults[right+1][z], ((altx%xFreq)/xFreq));
				}
			}else if(right+1 >= functionResults.length){
				right = functionResults.length -1;
				if(left >= functionResults.length-1){
					left = functionResults.length-1;
					interps[i] = cubicInterpolate(functionResults[left][z], functionResults[left][z], functionResults[right][z], functionResults[right][z], ((altx%xFreq)/xFreq));
				}
				interps[i] = cubicInterpolate(functionResults[left-1][z], functionResults[left][z], functionResults[right][z], functionResults[right][z], ((altx%xFreq)/xFreq));
			}
			else{
				interps[i] = cubicInterpolate(functionResults[left-1][z], functionResults[left][z], functionResults[right][z], functionResults[right+1][z], ((altx%xFreq)/xFreq));
			}
			altx++;
			if(altx == x){
				altx++;
			}
		}

		float fullInterp = cubicInterpolate(interps[0], interps[1], interps[2], interps[3], x);
*/
		//return fullInterp;
	}

	private float cubicInterpolate(float v0, float v1, float v2, float v3, float x) {
		float P = (v3 - v2) - (v0 - v1);
		float Q = (v0 - v1) - P;
		float R = v2 - v0;
		float S = v1;

		return P*x*x*x + Q*x*x + R*x + S;
	}
}
