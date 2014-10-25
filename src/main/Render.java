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
	}
	
	public void update(Land[][] world) {
		texture.bind();
		GL11.glColor3f(0.2f, 0.75f, 0.2f);
		GL11.glBegin(GL11.GL_QUADS);
		float[][] land = new float[5][5];
		for (int i = 0; i < 5; i++) { //x
			for (int j = 0; j < 5; j++) { //z
				land[i][j] = Math.abs(i-j)/2.0f;
			}
		}
		//Draw the land
		for (int i = 0; i < land.length-1; i++) { //x
			for (int j = 0; j < land[0].length-1; j++) { //z
				GL11.glVertex3f(0 + i-2, -1 + land[i][j], -1 + j-8); //far left
				GL11.glTexCoord2f(0, 0);
				
				GL11.glVertex3f(0 + i-2, -1 + land[i][j+1], 0 + j-8); //near left
				GL11.glTexCoord2f(0, 1);

				GL11.glVertex3f(1 + i-2, -1 + land[i+1][j+1], 0 + j-8); //near right
				GL11.glTexCoord2f(1, 1);

				GL11.glVertex3f(1 + i-2, -1 + land[i+1][j], -1 + j-8); //far right
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
		}
		return t;
	}
}
