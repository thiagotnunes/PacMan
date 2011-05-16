package com.pacman.entity.movement;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.AnimationFactory;

public abstract class Movement {

	public static final Integer ANIMATION_DELAY = 70;

	private Animation animation;
	
	public Movement(AnimationFactory animationFactory) throws SlickException {
		animation = animationFactory.from(this, ANIMATION_DELAY, false);
	}
	
	protected Movement() {}

	public abstract CollisionPolygon move(CollisionPolygon collisionPolygon, Float delta);

	public Animation getAnimation() {
		return animation;
	}

	public Boolean canMove(CollisionPolygon collisionPolygon, Float delta,
			Board board) {
		CollisionPolygon polygon = move(collisionPolygon, delta);
		
		return !board.isCollidingWithWall(polygon);
	}
}
