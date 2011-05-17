package com.pacman.entity.movement;

import org.newdawn.slick.SlickException;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.StoppedAnimationFactory;

public class Stopped extends Movement {

	public Stopped(StoppedAnimationFactory factory, Movement movement) throws SlickException {
		super(movement.getName(), factory);
	}

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
