package com.pacman.entity.maze;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import com.pacman.entity.Collidable;
import com.pacman.entity.Point;
import com.pacman.geometry.SquarePolygon;
import com.pacman.renderer.Renderable;

public class Block implements Renderable, Collidable {

	private final SquarePolygon squarePolygon;

	public Block(SquarePolygon squarePolygon) {
		this.squarePolygon = squarePolygon;
	}

	@Override
	public void draw(Graphics g) {
		squarePolygon.draw(g);
	}

	@Override
	public Point getPosition() {
		return squarePolygon.getPosition();
	}

	protected Polygon getPolygon() {
		return squarePolygon.getPolygon();
	}

	public boolean isCollidingWith(Shape shape) {
		return squarePolygon.isCollidingWith(shape);
	}

}
