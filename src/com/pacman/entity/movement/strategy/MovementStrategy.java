package com.pacman.entity.movement.strategy;

import com.pacman.entity.movement.Movement;
import com.pacman.geometry.CollisionPolygon;

public interface MovementStrategy {

	Movement currentMovement();

	void update(Movement movement, CollisionPolygon collisionPolygon,
			Float speed);

	Movement availableMovement(CollisionPolygon collisionPolygon, Float speed);
}
