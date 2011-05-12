package com.pacman.entity.maze.tile;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;

import com.pacman.geometry.CollisionPolygon;

public class ImageTile extends Tile {

	private final Image image;

	public ImageTile(CollisionPolygon collisionPolygon, Image image) {
		super(collisionPolygon);
		this.image = image;
	}

	@Override
	public void draw(Graphics g) {
		Polygon polygon = collisionPolygon.getPolygon();
		image.draw(polygon.getX(), polygon.getY(), polygon.getWidth(), polygon
				.getHeight());
	}

}
