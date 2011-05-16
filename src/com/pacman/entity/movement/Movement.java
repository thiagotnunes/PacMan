package com.pacman.entity.movement;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.AnimationFactory;

public abstract class Movement {

	public static final Integer ANIMATION_DELAY = 70;

	private boolean stopped;
	private Animation animation;
	private Animation stoppedAnimation;
	
	public Movement(AnimationFactory animationFactory) throws SlickException {
		animation = animationFactory.from(this, ANIMATION_DELAY, false);
		stoppedAnimation = animationFactory.from(this, ANIMATION_DELAY, true);
	}
	
	protected Movement() {}

	public abstract CollisionPolygon move(CollisionPolygon collisionPolygon, Float delta);

	public Animation getAnimation() {
		return stopped ? stoppedAnimation : animation;
	}

	public void stop() {
		stopped = true;
	}

	public Boolean canMove(CollisionPolygon collisionPolygon, Float delta,
			Board board) {
		CollisionPolygon polygon = move(collisionPolygon, delta);
		
		return !board.isCollidingWithWall(polygon);
	}
}
