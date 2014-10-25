package main;

import static org.lwjgl.opengl.GL11.glLoadIdentity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	public Vector3f position; //camera position
	public Vector3f rotation; //camera rotation
	public float speed = 1f; // m/s
	public float rotSpeed = 30f; //deg/s
	float toRad = (float)Math.PI/180; //conversion factor

	public Camera() {
		position = new Vector3f(0,0,0);
		rotation = new Vector3f(0,0,0);
	}

	public void update() {
		glLoadIdentity(); //reset field of view to position = rotation = 0
		float dt = Time.dt;
		Vector3f velocity = new Vector3f();
		//handle translation input
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			velocity.z += speed*dt;
		} if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			velocity.z -= speed*dt;
		} if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			velocity.x += speed*dt;
		} if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			velocity.x -= speed*dt;
		} if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			velocity.y -= speed*dt;
		} if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			velocity.y += speed*dt;
		}
		//handle rotation input
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			rotation.y -= rotSpeed*dt;
		} if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			rotation.y += rotSpeed*dt;
		}
		//rotate the velocity based on the Y rotation
		Vector3f vel = new Vector3f((float)(velocity.x*Math.cos(rotation.y*toRad) - velocity.z*Math.sin(rotation.y*toRad)),
									velocity.y, (float)(velocity.x*Math.sin(rotation.y*toRad) + velocity.z*Math.cos(rotation.y*toRad)));
		Vector3f.add(position, vel, position);
		//translate world
		GL11.glRotatef(rotation.y, 0, 1, 0);
		GL11.glTranslatef(position.x, position.y, position.z);

	}
}
