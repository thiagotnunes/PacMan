package com.pacman.entity.collision;

import com.pacman.geometry.CollisionPolygon;

public interface Collidable {

	boolean isCollidingWith(CollisionPolygon polygon);

}
