import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Trail extends GameObject{
	
	private float life;
	private Handler handler;
	private int width, height;
	
	public Trail(int x, int y, ID id, int width, int height, float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.width = width;
		this.height = height;
		this.life = life;
	}

	@Override
	public void tick() {
		if(life > 0) {
			life -= 0.001;
		}
		else {
			handler.removeObject(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
