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
		airGen = new AirGenerator();
		world = new Land[worldSize][worldSize];
	}
	
	public void generate() {
		worldGen.generate(world);
//		for (int i = 0; i < worldSize; i++) {
//			for (int j = 0; j < worldSize; j++) {
//				world[i][j] = new Land((float)Math.abs((Math.cos(i*1.3f)*Math.sin(j))*3));
//			}
//		}
		airGen.generate(world, 2.65f);
	}
	
	public void update() {
		//render
		draw.update(world);
		cam.update();
	}
	
}
