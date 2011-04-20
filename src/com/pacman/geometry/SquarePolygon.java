package com.pacman.geometry;

import java.awt.Point;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import com.pacman.entity.Collidable;
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

		return new float[] { 
				x, y,  
				x + endingX, y, 
				x + endingX, y + endingY, 
				x ,	y + endingY };
	}

	public Polygon getPolygon() {
		return polygon;
	}
	
	public void draw(Graphics g) {
		g.draw(polygon);
	}

	@Override
	public Point getPosition() {
		return new Point((int) polygon.getMinX(), (int) polygon.getMinY());
	}

	@Override
	public boolean isCollidingWith(Shape shape) {
		return polygon.intersects(shape);
	}

	public SquarePolygon translate(float x, float y) {
		float currentX = polygon.getX();
		float currentY = polygon.getY();
		
		Polygon translatedPolygon = polygon.copy();
		translatedPolygon.setLocation(currentX + x, currentY + y);
		
		return new SquarePolygon(translatedPolygon);
	}

}
