package com.pacman.entity.collision;

import com.pacman.geometry.SquarePolygon;

public interface Collidable {

	boolean isCollidingWith(SquarePolygon polygon);

}
