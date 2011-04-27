package com.pacman.entity.character;

import org.newdawn.slick.SlickException;

import com.pacman.entity.direction.DirectionBuilder;
import com.pacman.geometry.SquarePolygon;

public class PacManFactory {

	private DirectionBuilder directionFactory;

	public PacManFactory(DirectionBuilder directionFactory) {
		this.directionFactory = directionFactory;
	}

	public PacMan from(SquarePolygon collisionPolygon) throws SlickException {
		return new PacMan(collisionPolygon,
				directionFactory);
	}

}
