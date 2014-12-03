package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Render implements Runnable{
	private Thread renderThread;
	private Graphics2D g;
	
	public Render(Graphics g) {
		this.g = g.getGraphics2D();
	}
	
	public synchronized void start() {
		renderThread = new Thread(this, "Render Thread");
		renderThread.start();
	}
	
	public synchronized void resume() {
		notify();
	}

	@Override
	public void run() {
		while(Main.running) {
			draw();
			synchronized(this) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void draw() {
		BufferedImage buffer = new BufferedImage(Graphics.WIDTH, Graphics.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gBuffer = (Graphics2D)buffer.getGraphics();
		//drawStuff(gBuffer);
		g.drawImage(buffer, 0, 0, Graphics.WIDTH, Graphics.HEIGHT, null);
	}
}
