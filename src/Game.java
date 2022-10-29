import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -5864170491866819027L;
	
	public static final int WIDTH =635, HEIGHT = 638;
	
	private Thread thread;
	
	private boolean running = false;
	
	public static final int gridWidth = (int) WIDTH/20, gridHeight = (int) HEIGHT/20;
	
	private int[][] grid = new int[gridWidth][gridHeight] ;

	private Handler handler;
	
	private HUD hud;
	
	public Game() {
		this.handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window (WIDTH, HEIGHT, "Snake", this);
		
		Player player = new Player(WIDTH/40, HEIGHT/40, ID.Player, handler);
		
		handler.addObject(player);
		handler.addObject(new Food(5, 5, ID.Food));
		
		this.hud = new HUD();
	}

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while(delta > 0) {
				updates++;
				delta--;
				if(frames%5 == 0) {
					tick();
					render();
				}
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS : " + frames + " | TICKS : " + updates);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
		
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else 
			return var;
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();		
	}

	public static void main(String args[]) {
		new Game();

	}
}
