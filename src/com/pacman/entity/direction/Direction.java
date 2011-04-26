package com.pacman.entity.direction;

import com.pacman.geometry.SquarePolygon;

public interface Direction {
	
	SquarePolygon move(SquarePolygon p, float delta);

}
