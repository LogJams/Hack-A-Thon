package main;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class Render {

	Texture texture;
	
	public Render() {
		texture = loadTexture();
		texture.bind();
	}
	
	public void update(Land[][] world) {
		GL11.glColor3f(0.2f, 0.75f, 0.2f);
		GL11.glBegin(GL11.GL_QUADS);
		//Draw the world
		for (int i = 0; i < world.length-1; i++) { //x
			for (int j = 0; j < world[0].length-1; j++) { //z
				
				//System.out.println(world[i][j].height);
				
				GL11.glVertex3f(0 + i-2, -1 + world[i][j].height, -1 + j-8); //far left
				GL11.glTexCoord2f(0, 0);
				
				GL11.glVertex3f(0 + i-2, -1 + world[i][j+1].height, 0 + j-8); //near left
				GL11.glTexCoord2f(0, 1);

				GL11.glVertex3f(1 + i-2, -1 + world[i+1][j+1].height, 0 + j-8); //near right
				GL11.glTexCoord2f(1, 1);

				GL11.glVertex3f(1 + i-2, -1 + world[i+1][j].height, -1 + j-8); //far right
				GL11.glTexCoord2f(1, 0);
			}
		}
		GL11.glEnd();	
	}
	
	public static Texture loadTexture() {
		Texture t = null;
		try {
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Texture.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return t;
	}
}
