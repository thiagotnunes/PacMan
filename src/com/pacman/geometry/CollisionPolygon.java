package com.pacman.geometry;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.collision.Collidable;
import com.pacman.graphics.Drawable;

public class CollisionPolygon implements Drawable, Collidable {

	private Polygon polygon;

	public CollisionPolygon(Float x, Float y, Float width) {
		polygon = new Polygon(createPoints(x, y, width));
		polygon.setClosed(true);
	}

	protected CollisionPolygon(Polygon polygon) {
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

	@Override
	public void draw(Graphics g) {
		g.draw(polygon);
	}

	@Override
	public boolean isCollidingWith(CollisionPolygon collidable) {
		return polygon.intersects(collidable.getPolygon());
	}

	public CollisionPolygon translate(float x, float y) {
		float currentX = polygon.getX();
		float currentY = polygon.getY();

		Polygon translatedPolygon = polygon.copy();
		translatedPolygon.setLocation(currentX + x, currentY + y);

		return new CollisionPolygon(translatedPolygon);
	}

	@Override
	public String toString() {
		return "[(" + polygon.getMinX() + ", " + polygon.getMinY() + ")\n, "
				+ "(" + polygon.getMaxX() + ", " + polygon.getMinY() + ")\n, "
				+ "(" + polygon.getMaxX() + ", " + polygon.getMaxY() + ")\n, "
				+ "(" + polygon.getMinX() + ", " + polygon.getMaxY() + ")]";
	}

}
