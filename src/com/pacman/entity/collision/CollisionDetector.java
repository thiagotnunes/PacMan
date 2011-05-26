package com.pacman.entity.collision;

import java.util.List;

import com.pacman.geometry.CollisionPolygon;

public class CollisionDetector {

	public <T extends Collidable> T getCollidable(List<T> collidables,
			CollisionPolygon collisionPolygon) {
		for (T collidable : collidables) {
			if (collidable.isCollidingWith(collisionPolygon))
				return collidable;
		}
		return null;
	}

}
