package com.pacman.entity.collision;

import com.pacman.geometry.SquarePolygon;

public interface PolygonFactory {

	SquarePolygon from(Float x, Float y, Float width);

}
