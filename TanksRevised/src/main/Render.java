package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import world.DirtLine;
import world.Ground;

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
		gBuffer.setColor(Color.cyan);
		gBuffer.fillRect(0, 0, Graphics.WIDTH, Graphics.HEIGHT);
		drawGround(gBuffer);
		g.drawImage(buffer, 0, 0, Graphics.WIDTH, Graphics.HEIGHT, null);
	}

	private void drawGround(Graphics2D gBuffer) {
		gBuffer.setColor(new Color(139, 69, 19));
		Ground ground = Main.getGround();
		ArrayList<DirtLine> groundList = ground.getGroundList();
		for(int i = 0; i < ground.getWidth(); i++) {
			DirtLine newDirtLine = groundList.get(i);
			gBuffer.drawLine((int)newDirtLine.getTop().getX(), (int)newDirtLine.getTop().getY(), (int)newDirtLine.getBottom().getX(), (int)newDirtLine.getBottom().getY());
		}
	}
}
