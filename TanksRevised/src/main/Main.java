package main;

import world.Ground;

public class Main implements Runnable {
	public static boolean running = true;
	private static Main main;
	private static Update update;
	private static Render render;
	private Thread mainThread;
	private static Graphics g;
	private static Ground ground;
	
	
	public static void main(String[] args) {
		g = new Graphics(1024, 768);
		update = new Update();
		render = new Render(g);
		init();
		render.start();
		main = new Main();
		main.start();
	}
	
	public static void init() {
		ground = new Ground(Graphics.WIDTH);
	}
	
	public synchronized void start() {
		running = true;
		mainThread = new Thread(main, "Main Thread");
		mainThread.start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double nanoPerUpdate = 1000000000D/60D;
		double delta = 0D;
		init();
		while(running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoPerUpdate;
			lastTime = now;
			boolean shouldRender = false;
			
			while(delta >= 1) {
				update.update();
				delta--;
				shouldRender = true;
			}
			
			if(shouldRender) {
				render.resume();
			}
		}
	}
	
	public static Graphics getGraphics() {
		return g;
	}
	
	public static Ground getGround() {
		return ground;
	}
}
