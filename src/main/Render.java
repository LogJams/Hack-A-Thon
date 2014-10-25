package main;

import org.lwjgl.opengl.GL11;

public class Render {

	public void update(Land[][] world) {
		GL11.glColor3f(0.2f, 0.75f, 0.2f);
		GL11.glBegin(GL11.GL_QUADS);
		float[][] land = new float[5][5];
		for (int i = 0; i < 5; i++) { //x
			for (int j = 0; j < 5; j++) { //z
				land[i][j] = Math.abs(i-j)/2.0f;
			}
		}
		for (int i = 0; i < land.length-1; i++) { //x
			for (int j = 0; j < land[0].length-1; j++) { //z
				GL11.glVertex3f(0 + i-2, -1 + land[i][j], -1 + j-8); //far left
				GL11.glVertex3f(0 + i-2, -1 + land[i][j+1], 0 + j-8); //near left
				GL11.glVertex3f(1 + i-2, -1 + land[i+1][j+1], 0 + j-8); //near right
				GL11.glVertex3f(1 + i-2, -1 + land[i+1][j], -1 + j-8); //far right
			}
		}
		GL11.glEnd();
		
		
	}
}
