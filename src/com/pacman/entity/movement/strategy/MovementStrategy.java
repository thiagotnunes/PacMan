package com.pacman.entity.movement.strategy;

import com.pacman.entity.movement.Movement;
import com.pacman.geometry.CollisionPolygon;

public interface MovementStrategy {

	Movement currentMovement();

	Movement next(Movement movement, CollisionPolygon collisionPolygon,
			Float speed);
}
