import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int Score = 0;

	public void tick() {
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Score : " + Score, 10, 15);
	}
	
}
