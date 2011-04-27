package com.pacman.entity.direction;

import org.newdawn.slick.Animation;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.SquarePolygon;

public abstract class Direction {

	public static final Integer ANIMATION_DELAY = 70;

	public abstract SquarePolygon move(SquarePolygon collisionPolygon, Float delta);

	public abstract Animation getAnimation();

	public Boolean canMove(SquarePolygon collisionPolygon, Float delta,
			Board board) {
		SquarePolygon polygon = move(collisionPolygon, delta);
		
		return !board.isCollidingWith(polygon);
	}

}
