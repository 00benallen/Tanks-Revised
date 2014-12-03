package main;

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Graphics extends JFrame{
	private Graphics2D g;
	public static int WIDTH = 0;
	public static int HEIGHT = 0;
	
	public Graphics(int width, int height) {
		WIDTH = width; 
		HEIGHT = height;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		g = (Graphics2D) this.getContentPane().getGraphics();
	}
	
	public Graphics2D getGraphics2D() {return g;}

}
