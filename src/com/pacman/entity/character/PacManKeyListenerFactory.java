package com.pacman.entity.character;

import com.pacman.entity.direction.DirectionBuilder;

public class PacManKeyListenerFactory {

	public PacManKeyListener from(PacMan pacMan, DirectionBuilder directionBuilder) {
		return new PacManKeyListener(pacMan, directionBuilder);
	}

}
