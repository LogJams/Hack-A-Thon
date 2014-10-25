package main;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class Render {

	Texture texture;
	Texture water;
	
	public Render() {
		texture = loadTexture("Texture");
		water = loadTexture("Water");
		texture.bind();
	}
	
	public void drawWater() {
		int worldSize = Math.round(Controller.landSpacing * Controller.worldSize + 0.5f);
		GL11.glColor3f(1, 1, 1);
		water.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(-1,0.1f, -1); //far left
		GL11.glTexCoord2f(0, 0);
		
		GL11.glVertex3f(-1, 0.1f, worldSize+2); //near left
		GL11.glTexCoord2f(0, 1);

		GL11.glVertex3f(worldSize+2, 0.1f, worldSize+2); //near right
		GL11.glTexCoord2f(1, 1);

		GL11.glVertex3f(worldSize+2, 0.1f, -1); //far right
		GL11.glTexCoord2f(1, 0);
		GL11.glEnd();
	}
	
	public void update(Land[][] world) {
		texture.bind();
		GL11.glColor3f(.7f, .7f, .7f);
		GL11.glBegin(GL11.GL_QUADS);
		float wet = 0.5f;
		//Draw the world
		for (int i = 0; i < world.length-1; i++) { //x
			for (int j = 0; j < world[0].length-1; j++) { //z
				float size = Controller.landSpacing;
				wet = 0;
				if (world[i][j].isWeet) {
					wet = 0.5f;
				}
				
				GL11.glVertex3f(0 + i*size, world[i][j].height, 0 + j*size); //far left
				GL11.glTexCoord2f(0, 0+wet);
				
				GL11.glVertex3f(0 + i*size, world[i][j+1].height, size + j*size); //near left
				GL11.glTexCoord2f(0, 0.5f+wet);

				GL11.glVertex3f(size + i*size, world[i+1][j+1].height, size + j*size); //near right
				GL11.glTexCoord2f(1, 0.5f+wet);

				GL11.glVertex3f(size + i*size, world[i+1][j].height, 0 + j*size); //far right
				GL11.glTexCoord2f(1, 0+wet);
			}
		}
		GL11.glEnd();	
	}
	
	public static Texture loadTexture(String name) {
		Texture t = null;
		try {
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/"+name+".png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return t;
	}
}
