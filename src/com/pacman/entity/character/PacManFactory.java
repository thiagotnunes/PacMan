package com.pacman.entity.character;

import org.newdawn.slick.SlickException;

import com.pacman.entity.direction.Direction;
import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;

public class PacManFactory {

	public PacMan from(CollisionPolygon collisionPolygon, Direction initialDirection, Board board) throws SlickException {
		return new PacMan(collisionPolygon,
				initialDirection, board);
	}

}
