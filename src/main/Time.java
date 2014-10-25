package main;

public class Time {

	public static float dt;
	private static long lastTime = System.nanoTime();
	
	public static void update() {
		long time = System.nanoTime();
		dt = (time - lastTime)/1000000000f;
		lastTime = time;
	}
}
