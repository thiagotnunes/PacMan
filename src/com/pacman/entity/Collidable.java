package com.pacman.entity;

import com.pacman.geometry.SquarePolygon;

public interface Collidable {

	boolean isCollidingWith(SquarePolygon polygon);

}
