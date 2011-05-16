package com.pacman.entity.character;

import org.newdawn.slick.SlickException;

import com.pacman.entity.movement.MovementBuilder;

public class PacManKeyListenerFactory {

	private final MovementBuilder movementBuilder;

	public PacManKeyListenerFactory(MovementBuilder movementBuilder) {
		this.movementBuilder = movementBuilder;
	}

	public PacManKeyListener from(PacMan pacMan) throws SlickException {
		movementBuilder.buildMovements();
		return new PacManKeyListener(pacMan, movementBuilder);
	}

}
