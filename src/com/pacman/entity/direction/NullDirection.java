package com.pacman.entity.direction;

import com.pacman.geometry.SquarePolygon;

public class NullDirection implements Direction {

	@Override
	public SquarePolygon move(SquarePolygon p, float delta) {
		return null;
	}

}
