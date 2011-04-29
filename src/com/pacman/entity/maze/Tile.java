package com.pacman.entity.maze;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.collision.Collidable;
import com.pacman.geometry.Point;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.renderer.Renderable;

public class Tile implements Renderable, Collidable {

	private final CollisionPolygon collisionPolygon;

	public Tile(CollisionPolygon collisionPolygon) {
		this.collisionPolygon = collisionPolygon;
	}

	@Override
	public void draw(Graphics g) {
		collisionPolygon.draw(g);
	}

	@Override
	public Point getPosition() {
		return collisionPolygon.getPosition();
	}

	protected Polygon getPolygon() {
		return collisionPolygon.getPolygon();
	}

	public boolean isCollidingWith(CollisionPolygon collidable) {
		return collisionPolygon.isCollidingWith(collidable);
	}

}
