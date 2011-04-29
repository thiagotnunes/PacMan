package com.pacman.entity.collision;

import com.pacman.geometry.SquarePolygon;

public class FullPolygonFactory implements PolygonFactory {

	@Override
	public SquarePolygon from(Float x, Float y, Float width) {
		return new SquarePolygon(x * width, y * width, width);
	}

}
