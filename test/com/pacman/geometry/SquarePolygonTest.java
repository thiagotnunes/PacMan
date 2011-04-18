package com.pacman.geometry;

import static org.junit.Assert.*;

import org.junit.Test;
import org.lwjgl.util.Point;
import org.newdawn.slick.geom.Polygon;

public class SquarePolygonTest {

	@Test
	public void shouldCreateSquarePolygon() throws Exception {
		int x = 0;
		int y = 0;
		int width = 25;
		SquarePolygon square = new SquarePolygon(x, y, width);

		Point[] squarePoints = new Point[] { new Point(x, y),
				new Point(width - 1, y), new Point(width - 1, width - 1),
				new Point(x, width - 1) };
		Polygon expectedPolygon = new Polygon(new float[] {
				x + squarePoints[0].getX(), y + squarePoints[0].getY(),
				x + squarePoints[1].getX(), y + squarePoints[1].getY(),
				x + squarePoints[2].getX(), y + squarePoints[2].getY(),
				x + squarePoints[3].getX(), y + squarePoints[3].getY() });

		Polygon actualPolygon = square.getPolygon();

		assertEquals(expectedPolygon.getPointCount(), actualPolygon
				.getPointCount());
		validatePolygons(expectedPolygon, actualPolygon);
	}

	private void validatePolygons(Polygon expectedPolygon, Polygon actualPolygon) {
		for (int i = 0; i < expectedPolygon.getPointCount(); i++) {
			float[] expectedPoint = expectedPolygon.getPoint(i);
			float[] actualPoint = actualPolygon.getPoint(i);
			for (int j = 0; j < expectedPoint.length; j++) {
				assertEquals(expectedPoint[j], actualPoint[j], 2);
			}
		}
	}
}
