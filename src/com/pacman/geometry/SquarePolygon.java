package com.pacman.geometry;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.collision.Collidable;
import com.pacman.renderer.Renderable;

public class SquarePolygon implements Renderable, Collidable {

	private Polygon polygon;

	public SquarePolygon(Float x, Float y, Float width) {
		polygon = new Polygon(createPoints(x, y, width));
		polygon.setClosed(true);
	}

	protected SquarePolygon(Polygon polygon) {
		this.polygon = polygon;
	}

	private float[] createPoints(Float x, Float y, Float width) {
		Float endingX = width - 1;
		Float endingY = endingX;

		return new float[] { x, y, x + endingX, y, x + endingX, y + endingY, x,
				y + endingY };
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void draw(Graphics g) {
		g.draw(polygon);
	}

	@Override
	public Point getPosition() {
		return new Point(polygon.getX(), polygon.getY());
	}

	@Override
	public boolean isCollidingWith(SquarePolygon collidable) {
		return polygon.intersects(collidable.getPolygon());
	}

	public SquarePolygon translate(float x, float y) {
		float currentX = polygon.getX();
		float currentY = polygon.getY();

		Polygon translatedPolygon = polygon.copy();
		translatedPolygon.setLocation(currentX + x, currentY + y);

		return new SquarePolygon(translatedPolygon);
	}

	@Override
	public String toString() {
		return "[(" + polygon.getMinX() + ", " + polygon.getMinY() + ")\n, "
				+ "(" + polygon.getMaxX() + ", " + polygon.getMinY() + ")\n, "
				+ "(" + polygon.getMaxX() + ", " + polygon.getMaxY() + ")\n, "
				+ "(" + polygon.getMinX() + ", " + polygon.getMaxY() + ")]";
	}

}
