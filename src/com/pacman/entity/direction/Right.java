package com.pacman.entity.direction;

import com.pacman.geometry.SquarePolygon;

public class Right implements Direction {

	@Override
	public SquarePolygon move(SquarePolygon p, float delta) {
		return p.translate(-delta, 0);
	}

}
