package com.pacman.entity.direction;

import org.newdawn.slick.Animation;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;

public abstract class Direction {

	public static final Integer ANIMATION_DELAY = 70;

	public abstract CollisionPolygon move(CollisionPolygon collisionPolygon, Float delta);

	public abstract Animation getAnimation();

	public Boolean canMove(CollisionPolygon collisionPolygon, Float delta,
			Board board) {
		CollisionPolygon polygon = move(collisionPolygon, delta);
		
		return !board.isCollidingWithWall(polygon);
	}

}
