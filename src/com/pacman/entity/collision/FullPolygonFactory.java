package com.pacman.entity.collision;

import com.pacman.geometry.CollisionPolygon;

public class FullPolygonFactory implements PolygonFactory {

	@Override
	public CollisionPolygon from(Float x, Float y, Float width) {
		return new CollisionPolygon(x * width, y * width, width);
	}

}
