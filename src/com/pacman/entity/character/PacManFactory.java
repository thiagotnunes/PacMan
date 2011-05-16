package com.pacman.entity.character;

import org.newdawn.slick.SlickException;

import com.pacman.entity.maze.Board;
import com.pacman.entity.movement.MovementBuilder;
import com.pacman.entity.movement.NullMovement;
import com.pacman.entity.movement.strategy.BufferedMovementStrategy;
import com.pacman.geometry.CollisionPolygon;

public class PacManFactory {

	private final MovementBuilder movementBuilder;

	public PacManFactory(MovementBuilder movementBuilder) {
		this.movementBuilder = movementBuilder;
	}

	public PacMan from(CollisionPolygon collisionPolygon, Board board)
			throws SlickException {
		movementBuilder.buildMovements();
		return new PacMan(collisionPolygon, new BufferedMovementStrategy(board,
				movementBuilder.defaultDirection(), new NullMovement()), board);
	}

}
