package com.pacman.entity.movement;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.StoppedAnimationFactory;

public class Stopped implements Movement {

	private String name;

	protected Stopped(StoppedAnimationFactory factory, BaseMovement movement) throws SlickException {
		String movementName = movement.toString();
		name = "stopped_" + movementName;
		factory.from(movementName, MOVEMENT_ANIMATION_DELAY);
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

	@Override
	public Animation getAnimation() {
		return null;
	}

	@Override
	public String name() {
		return name;
	}
}
