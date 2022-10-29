import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Player extends GameObject{
	
	private ArrayList<Point> tail = new ArrayList<Point>();
	Handler handler;
	
	Random r = new Random();

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void tick() {
		collision();
		if(HUD.Score == this.tail.size()) {
			for(int i = 0; i < tail.size() - 1; i++) {
				this.tail.set(i, tail.get(i+1));
			}
		}
		Point coords = new Point(this.x, this.y);
		if(!tail.isEmpty()) {
			this.tail.set(this.tail.size() - 1, coords);
		}
		this.x = this.x + this.velX;
		this.y = this.y + this.velY;	
		this.x = Game.clamp(this.x, 0, Game.gridWidth - 1);
		this.y = Game.clamp(this.y, 0, Game.gridHeight - 2);
		eat();
	}

	private void eat() {
		for(int j = 0; j< handler.object.size(); j++) {
			if(handler.object.get(j).getId() == ID.Food) {
				GameObject food = handler.object.get(j);
				if(getBounds().intersects(food.getBounds())) {
					handler.object.remove(food);
					Point coords = new Point(food.getX(), food.getY());
					tail.add(coords);
					HUD.Score += 1;
					coords = new Point(r.nextInt(Game.gridWidth), r.nextInt(Game.gridHeight));
					while(this.tail.contains(coords)) {
						coords = new Point(r.nextInt(Game.gridWidth), r.nextInt(Game.gridHeight));
					}
					handler.addObject(new Food(coords.x, coords.y, ID.Food));
				}
			}
		}
	}
	
	private void collision() {
		for (int i = 0; i < this.tail.size() - 1 ; i++) {
			if( this.tail.get(i).x == this.x && this.tail.get(i).y == this.y) {
				handler.removeObject(this);
				HUD.Score = 0;
				handler.addObject(new Player(Game.WIDTH/40, Game.HEIGHT/40, ID.Player, handler));
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(this.x*20, this.y*20, 20, 20);
		for(Point coords : tail) {
			g.fillRect(coords.x*20, coords.y*20, 20, 20);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x*20, y*20, 20, 20);
	}

}
