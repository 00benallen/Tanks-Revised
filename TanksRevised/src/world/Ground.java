package world;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import main.Graphics;

public class Ground {
	private ArrayList<DirtLine> groundList = new ArrayList<DirtLine>();
	private int width;
	
	public Ground(int width) {
		this.setWidth(width);
		generateGround();
	}
	
	public void generateGround() {
		Random hillGen = new Random();
		
		DirtLine initLine = new DirtLine(new Point2D.Double(0, Graphics.HEIGHT), hillGen.nextInt(Graphics.HEIGHT/3) + 20);
		int groundMade = 0;
		groundList.add(initLine);
		while(groundMade < width) {
			int hillWidth = hillGen.nextInt(140) + 10;
			DirtLine newLine = initLine;
			for(int j = 0; j < hillWidth/3; j++) {
				newLine.getBottom().setLocation(newLine.getBottom().getX() + 1, newLine.getBottom().getY());
				newLine.getTop().setLocation(newLine.getTop().getX() + 1, newLine.getTop().getY() + 1);
				groundList.add(newLine);
				Point2D.Double bottom = newLine.getBottom();
				int length = newLine.getLength();
				newLine = new DirtLine(bottom, length);
				if(groundMade < width) {
					groundMade++;
				}
			}
			for(int j = 0; j < (hillWidth/3)*2; j++) {
				newLine.getBottom().setLocation(newLine.getBottom().getX() + 1, newLine.getBottom().getY());
				newLine.getTop().setLocation(newLine.getTop().getX() + 1, newLine.getTop().getY());
				groundList.add(newLine);
				Point2D.Double bottom = newLine.getBottom();
				int length = newLine.getLength();
				newLine = new DirtLine(bottom, length);
				if(groundMade < width) {
					groundMade++;
				}
			}
			for(int j = 0; j < hillWidth; j++) {
				newLine.getBottom().setLocation(newLine.getBottom().getX() + 1, newLine.getBottom().getY());
				newLine.getTop().setLocation(newLine.getTop().getX() + 1, newLine.getTop().getY() - 1);
				groundList.add(newLine);
				Point2D.Double bottom = newLine.getBottom();
				int length = newLine.getLength();
				newLine = new DirtLine(bottom, length);
				if(groundMade < width) {
					groundMade++;
				}
			}
		}
	}

	public int getWidth() {
		return width;
	}

	private void setWidth(int width) {
		this.width = width;
	}
	
	public ArrayList<DirtLine> getGroundList() {
		return groundList;
	}
}
