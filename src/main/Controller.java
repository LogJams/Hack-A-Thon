package main;

import generation.AirGenerator;
import generation.RadialGradient;
import generation.WorldGenerator;

public class Controller {

	private Render draw;
	WorldGenerator worldGen;
	AirGenerator airGen;
	RadialGradient radGrad;
	Camera cam;
	
	public static int worldSize = 100;
	public static final float landSpacing = 0.2f;
	private int scale = 2;
	Land[][] world;
	
	public Controller() {
		draw = new Render();
		cam = new Camera();
		worldGen = new WorldGenerator();
		radGrad = new RadialGradient();
		airGen = new AirGenerator();
		world = new Land[worldSize][worldSize];
	}
	
	public void generate() {
		worldGen.generate(world,scale);
		radGrad.generate(world);
		airGen.generate(world, 1.65f);
	}
	
	public void update() {
		//render
		draw.update(world);
		draw.drawWater();
		cam.update();
	}
	
}
