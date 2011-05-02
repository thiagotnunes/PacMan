package com.pacman.entity.collision;

import com.pacman.geometry.CollisionPolygon;

public interface Collidable {

	boolean isCollidingWithWall(CollisionPolygon polygon);

}
