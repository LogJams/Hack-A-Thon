package main;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class Driver {
	
	public int screenWidth = 800;
	public int screenHeight = 600;
	Controller controller;
	public float fovAngle = 180;
	public float nearClip = .001f;
	public float farClip= 1000;
	
	public Driver() {
		try {
			Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controller = new Controller();

		Display.setTitle("Hack and Tell");
		initGraphics();

		controller.generate();
		
		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			Time.update();
			controller.update();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	public void initGraphics() {
		GL11.glClearColor(135/255f, 1206/255f, 250/255f, 1.0f); // Blue Background
	//	glEnable(GL_CULL_FACE);// Enables face culling (working)
	//	glCullFace(GL_BACK); // Doesn't draw back faces

		glEnable(GL_DEPTH_TEST);
		glEnable(GL11.GL_TEXTURE_2D);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);


		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		 GLU.gluPerspective(45.0f, Display.getWidth() / Display.getHeight(), 1.0f, 100.0f);
		 glMatrixMode(GL_MODELVIEW);
	}

	public static void main(String[] args) {
		new Driver();
	}
	
}
