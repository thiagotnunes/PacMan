package com.pacman.entity.character;

import org.newdawn.slick.SlickException;

import com.pacman.entity.direction.DirectionBuilder;
import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;

public class PacManFactory {

	private final DirectionBuilder directionBuilder;

	public PacManFactory(DirectionBuilder directionBuilder) {
		this.directionBuilder = directionBuilder;
	}

	public PacMan from(CollisionPolygon collisionPolygon, Board board) throws SlickException {
		directionBuilder.buildDirections();
		return new PacMan(collisionPolygon,
				directionBuilder.defaultDirection(), board);
	}

}
