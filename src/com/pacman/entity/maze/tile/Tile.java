package com.pacman.entity.maze.tile;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.collision.Collidable;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.geometry.Point;
import com.pacman.renderer.Renderable;

public class Tile implements Renderable, Collidable {

	private final CollisionPolygon collisionPolygon;
	private Image image;

	public Tile(CollisionPolygon collisionPolygon) {
		this.collisionPolygon = collisionPolygon;
	}

	public Tile(CollisionPolygon collisionPolygon, Image image) {
		this(collisionPolygon);
		this.image = image;
	}

	@Override
	public void draw(Graphics g) {
		collisionPolygon.draw(g);
		if (image != null) {
			Polygon polygon = collisionPolygon.getPolygon();
			image.draw(0, 0, polygon.getWidth(), polygon.getHeight());
		}
	}

	@Override
	public Point getPosition() {
		return collisionPolygon.getPosition();
	}

	public Polygon getPolygon() {
		return collisionPolygon.getPolygon();
	}

	public boolean isCollidingWith(CollisionPolygon collidable) {
		return collisionPolygon.isCollidingWith(collidable);
	}

}
