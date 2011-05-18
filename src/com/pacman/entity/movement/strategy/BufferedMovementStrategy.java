package com.pacman.entity.movement.strategy;

import com.pacman.entity.maze.Board;
import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.MovementBuilder;
import com.pacman.geometry.CollisionPolygon;

public class BufferedMovementStrategy implements MovementStrategy {

	private final Board board;
	private final MovementBuilder movementBuilder;
	private Movement currentMovement;
	protected Movement bufferedMovement;

	public BufferedMovementStrategy(Board board, MovementBuilder movementBuilder, Movement bufferedMovement) {
		this.board = board;
		this.movementBuilder = movementBuilder;
		this.currentMovement = movementBuilder.defaultMovement();
		this.bufferedMovement = bufferedMovement;
	}

	@Override
	public Movement currentMovement() {
		return currentMovement;
	}

	@Override
	public void update(Movement movement,
			CollisionPolygon collisionPolygon, Float speed) {
		if (movement.canMove(collisionPolygon, speed, board)) {
			currentMovement = movement;
		} else {
			bufferedMovement = movement;
		}
	}

	@Override
	public Movement availableMovement(CollisionPolygon collisionPolygon,
			Float speed) {
		if (bufferedMovement.canMove(collisionPolygon, speed, board)) {
			currentMovement = bufferedMovement;
			bufferedMovement = movementBuilder.nullMovement();
		} else if (!currentMovement.canMove(collisionPolygon, speed, board)) {
			currentMovement = movementBuilder.stoppedFrom(currentMovement);
			bufferedMovement = movementBuilder.nullMovement();
		}
		
		return currentMovement;
	}
}
