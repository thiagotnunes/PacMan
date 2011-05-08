package com.pacman.entity.character;

import org.newdawn.slick.SlickException;

import com.pacman.entity.direction.DirectionBuilder;

public class PacManKeyListenerFactory {

	private final DirectionBuilder directionBuilder;

	public PacManKeyListenerFactory(DirectionBuilder directionBuilder) {
		this.directionBuilder = directionBuilder;
	}

	public PacManKeyListener from(PacMan pacMan) throws SlickException {
		directionBuilder.buildDirections();
		return new PacManKeyListener(pacMan, directionBuilder);
	}

}
