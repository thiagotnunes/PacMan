package com.pacman.entity.direction;

import com.pacman.geometry.SquarePolygon;

public class Up implements Direction {

	@Override
	public SquarePolygon move(SquarePolygon p, float delta) {
		return p.translate(0, -delta);
	}

}
