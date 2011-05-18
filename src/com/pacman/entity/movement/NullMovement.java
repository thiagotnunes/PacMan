package com.pacman.entity.movement;

import org.newdawn.slick.Animation;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;

public class NullMovement implements Movement {

	protected NullMovement() {}
	
	@Override
	public Boolean canMove(CollisionPolygon collisionPolygon, Float delta,
			Board board) {
		return false;
	}

	@Override
	public CollisionPolygon move(CollisionPolygon collisionPolygon, Float delta) {
		return null;
	}

	@Override
	public Animation getAnimation() {
		return null;
	}
	
	@Override
	public String name() {
		return "nullMovement";
	}

}
