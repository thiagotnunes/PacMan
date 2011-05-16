package com.pacman.entity.movement;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;

public class Stopped extends Movement {

	@Override
	public CollisionPolygon move(CollisionPolygon collisionPolygon, Float delta) {
		return collisionPolygon;
	}
	
	@Override
	public Boolean canMove(CollisionPolygon collisionPolygon, Float delta,
			Board board) {
		return false;
	}

}
