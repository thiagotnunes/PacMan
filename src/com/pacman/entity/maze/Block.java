package com.pacman.entity.maze;

import org.lwjgl.util.Point;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import com.pacman.entity.Collidable;
import com.pacman.renderer.Renderable;

public class Block implements Renderable, Collidable {

	private static final int INITIAL_X = 0;
	private static final int INITIAL_Y = 0;
	private static final int ENDING_X = 24;
	private static final int ENDING_Y = 24;
	private static final Point[] squarePoints = new Point[] {
			new Point(INITIAL_X, INITIAL_Y), new Point(ENDING_X, INITIAL_Y),
			new Point(ENDING_X, ENDING_Y), new Point(INITIAL_X, ENDING_Y) };

	private Polygon polygon;

	public Block(int x, int y) {
		this(new Polygon(createPoints(x, y)));
	}

	protected Block(Polygon polygon) {
		this.polygon = polygon;
	}

	private static float[] createPoints(int x, int y) {
		return new float[] { x + squarePoints[0].getX(),
				y + squarePoints[0].getY(), x + squarePoints[1].getX(),
				y + squarePoints[1].getY(), x + squarePoints[2].getX(),
				y + squarePoints[2].getY(), x + squarePoints[3].getX(),
				y + squarePoints[3].getY() };
	}

	@Override
	public void draw(Graphics g) {
		g.draw(polygon);
	}

	@Override
	public Point getPosition() {
		return new Point((int) polygon.getMinX(), (int) polygon.getMinY());
	}

	protected Polygon getPolygon() {
		return polygon;
	}

	@Override
	public boolean isCollidingWith(Shape shape) {
		return polygon.intersects(shape);
	}

}
