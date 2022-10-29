import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food extends GameObject{
	
	public Food(int x, int y, ID id) {
		super(x, y, id);
		this.x = Game.clamp(x, 0, Game.gridWidth - 1);
		this.y = Game.clamp(y, 1, Game.gridHeight - 2);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x*20, y*20, 20, 20);		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x*20, y*20, 20, 20);
	}

}
