package com.pacman.geometry;

import org.lwjgl.util.Point;
import org.newdawn.slick.geom.Polygon;

public class SquarePolygon {

	private Polygon polygon;

	public SquarePolygon(Integer x, Integer y, Integer width) {
		polygon = new Polygon(createPoints(x, y, width));
	}

	private float[] createPoints(Integer x, Integer y, Integer width) {
		Integer endingX = width - 1;
		Integer endingY = endingX;
		Point[] squarePoints = new Point[] { new Point(x, y),
				new Point(x + endingX, y), new Point(endingX, endingY),
				new Point(x, endingY) };

		return new float[] { x + squarePoints[0].getX(),
				y + squarePoints[0].getY(), x + squarePoints[1].getX(),
				y + squarePoints[1].getY(), x + squarePoints[2].getX(),
				y + squarePoints[2].getY(), x + squarePoints[3].getX(),
				y + squarePoints[3].getY() };
	}

	public Polygon getPolygon() {
		return polygon;
	}

}
