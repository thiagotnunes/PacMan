package com.pacman.entity.direction;

import org.newdawn.slick.Animation;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;

public class NullDirection extends Direction {

	@Override
	public CollisionPolygon move(CollisionPolygon p, Float delta) {
		return null;
	}

	@Override
	public Animation getAnimation() {
		return null;
	}
	
	@Override
	public Boolean canMove(CollisionPolygon collisionPolygon, Float delta,
			Board board) {
		return false;
	}

}
