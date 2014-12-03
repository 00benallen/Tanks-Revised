package world;

import java.awt.geom.Point2D;

public class DirtLine {
	private int length;
	private Point2D.Double top, bottom;
	
	public DirtLine(Point2D.Double bottom, int height) {
		this.setBottom(bottom);
		this.setTop(new Point2D.Double(bottom.getX(), bottom.getY() + height));
		this.setLength(height);
	}

	public Point2D.Double getTop() {
		return top;
	}

	private void setTop(Point2D.Double top) {
		this.top = top;
	}

	public Point2D.Double getBottom() {
		return bottom;
	}

	private void setBottom(Point2D.Double bottom) {
		this.bottom = bottom;
	}

	public int getLength() {
		return length;
	}

	private void setLength(int length) {
		this.length = length;
	}
	
	public DirtLine[] destroy(int topY, int bottomY) {
		DirtLine newDirtLine1 = new DirtLine(this.getBottom(), this.getLength());
		DirtLine newDirtLine2 = new DirtLine(this.getBottom(), this.getLength());
		if(topY < top.getY() && bottomY < bottom.getY()) {
			newDirtLine1.getTop().setLocation(newDirtLine1.getTop().getX(), bottomY);
			newDirtLine1.setLength((int)Point2D.distance(this.getTop().getX(), this.getTop().getY(), this.getBottom().getX(), this.getBottom().getY()));
			DirtLine[] singleLine = new DirtLine[1];
			singleLine[0] = newDirtLine1;
			return singleLine;
		}
		if(topY > top.getY() && bottomY < bottom.getY()) {
			newDirtLine1.getBottom().setLocation(newDirtLine1.getBottom().getX(), bottomY);
			newDirtLine2.getBottom().setLocation(newDirtLine2.getBottom().getX(), topY);
			newDirtLine1.setLength((int)Point2D.distance(this.getTop().getX(), this.getTop().getY(), this.getBottom().getX(), this.getBottom().getY()));
			DirtLine[] doubleLine = new DirtLine[2];
			doubleLine[0] = newDirtLine1;
			doubleLine[1] = newDirtLine2;
			return doubleLine;
		}
		if(topY > top.getY() && bottomY > bottom.getY()) {
			newDirtLine1.getBottom().setLocation(newDirtLine1.getBottom().getX(), topY);
			newDirtLine1.setLength((int)Point2D.distance(this.getTop().getX(), this.getTop().getY(), this.getBottom().getX(), this.getBottom().getY()));
			DirtLine[] singleLine = new DirtLine[1];
			singleLine[0] = newDirtLine1;
			return singleLine;
		}
		return null;
	}

}
