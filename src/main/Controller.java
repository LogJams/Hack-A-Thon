package main;

import generation.AirGenerator;
import generation.WorldGenerator;

public class Controller {

	private Render draw;
	WorldGenerator worldGen;
	AirGenerator airGen;
	Camera cam;
	
	private int worldSize = 20;
	public static final float landSpacing = 1.2f;
	Land[][] world;
	
	public Controller() {
		draw = new Render();
		cam = new Camera();
		worldGen = new WorldGenerator();
		world = new Land[worldSize][worldSize];
	}
	
	public void generate() {
	//	worldGen.generate(world);
		airGen.generate(world);
	}
	
	public void update() {
		//render
		draw.update(world);
		cam.update();
	}
	
}
