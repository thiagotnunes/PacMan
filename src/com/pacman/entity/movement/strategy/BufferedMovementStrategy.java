package com.pacman.entity.movement.strategy;

import com.pacman.entity.maze.Board;
import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.MovementFactory;
import com.pacman.geometry.CollisionPolygon;

public class BufferedMovementStrategy implements MovementStrategy {

	private final Board board;
	private final MovementFactory movementFactory;
	private Movement currentMovement;
	protected Movement bufferedMovement;

	public BufferedMovementStrategy(Board board, MovementFactory movementFactory, Movement bufferedMovement) {
		this.board = board;
		this.movementFactory = movementFactory;
		this.currentMovement = movementFactory.defaultMovement();
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
			bufferedMovement = movementFactory.nullMovement();
		} else if (!currentMovement.canMove(collisionPolygon, speed, board)) {
			currentMovement = movementFactory.stoppedFrom(currentMovement);
			bufferedMovement = movementFactory.nullMovement();
		}
		
		return currentMovement;
	}
}
