package com.pacman.entity.maze;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.collision.Collidable;
import com.pacman.geometry.Point;
import com.pacman.geometry.SquarePolygon;
import com.pacman.renderer.Renderable;

public class Tile implements Renderable, Collidable {

	private final SquarePolygon squarePolygon;

	public Tile(SquarePolygon squarePolygon) {
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

	public boolean isCollidingWith(SquarePolygon collidable) {
		return squarePolygon.isCollidingWith(collidable);
	}

}
