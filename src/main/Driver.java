package main;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Driver {
	
	public int screenWidth = 800;
	public int screenHeight = 600;
	Controller controller;
	
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

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	public void testDraw() {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(50, 0);
		GL11.glVertex2f(50, 50);
		GL11.glVertex2f(0, 50);
		GL11.glVertex2f(0, 0);
		GL11.glEnd();
	}

	public static void main(String[] args) {
		new Driver();
	}
	
}
