package main;

import generation.AirGenerator;
import generation.RadialGradient;
import generation.RiverGenerator;
import generation.WorldGenerator;

public class Controller {

	private Render draw;
	WorldGenerator worldGen;
	AirGenerator airGen;
	RadialGradient radGrad;
	RiverGenerator riverGen;
	Camera cam;
	
	public static int worldSize = 150;
	public static final float landSpacing = 0.2f;
	private int scale = 1;
	Land[][] world;
	
	public Controller() {
		draw = new Render();
		cam = new Camera();
		worldGen = new WorldGenerator();
		radGrad = new RadialGradient();
		airGen = new AirGenerator();
		riverGen = new RiverGenerator();
		world = new Land[worldSize][worldSize];
	}
	
	public void generate() {
		worldGen.generate(world,scale);
		radGrad.generate(world);
		airGen.generate(world, 1.65f);
		riverGen.generate(world, 0.80f, 0.1f);
	}
	
	public void update() {
		//render
		draw.update(world);
		draw.drawWater();
		cam.update();
	}
	
}
