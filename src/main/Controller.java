package main;

public class Controller {

	private Render draw;
	WorldGenerator worldGen;
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
	
	public void update() {
		//update world generator
		worldGen.update(world);
		//render
		draw.update(world);
		cam.update();
	}
	
}
