package com.pacman.entity.collision;

import com.pacman.geometry.CollisionPolygon;

public interface PolygonFactory {

	CollisionPolygon from(Float x, Float y, Float width);

}
