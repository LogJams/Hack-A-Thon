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
		controller = new Controller();
		try {
			Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Display.setTitle("Hack and Tell");
		initGraphics();

		
		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			testDraw();
			controller.update();
			Display.update();
		}
		Display.destroy();
	}
	
	public void initGraphics() {
	//	GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
		GL11.glEnable(GL11.GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
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
	
	public void testDraw() {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(50, 0, -20);
		GL11.glVertex3f(50, 50, -20);
		GL11.glVertex3f(0, 50, -20);
		GL11.glVertex3f(0, 0, -20);
		GL11.glEnd();
	}

	public static void main(String[] args) {
		new Driver();
	}
	
}
