package main;

public class Render implements Runnable{
	Thread renderThread;
	
	public Render() {
		
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
		
	}
}
