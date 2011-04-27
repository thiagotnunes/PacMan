package com.pacman.entity.direction;

import org.newdawn.slick.Animation;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.SquarePolygon;

public class NullDirection extends Direction {

	@Override
	public SquarePolygon move(SquarePolygon p, Float delta) {
		return null;
	}

	@Override
	public Animation getAnimation() {
		return null;
	}
	
	@Override
	public Boolean canMove(SquarePolygon collisionPolygon, Float delta,
			Board board) {
		return false;
	}

}
