package com.pacman.entity.maze.tile;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.collision.Collidable;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.Drawable;

public class Tile implements Drawable, Collidable {

	protected final CollisionPolygon collisionPolygon;

	public Tile(CollisionPolygon collisionPolygon) {
		this.collisionPolygon = collisionPolygon;
	}

	@Override
	public void draw(Graphics g) {
		collisionPolygon.draw(g);
	}

	public Polygon getPolygon() {
		return collisionPolygon.getPolygon();
	}

	public boolean isCollidingWith(CollisionPolygon collidable) {
		return collisionPolygon.isCollidingWith(collidable);
	}

}
