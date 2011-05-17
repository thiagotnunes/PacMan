package com.pacman.entity.movement;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.MovementAnimationFactory;

public abstract class Movement {

	public static final Integer ANIMATION_DELAY = 70;

	private Animation animation;

	protected String name;
	
	protected Movement() {}
	
	public Movement(String name, MovementAnimationFactory animationFactory) throws SlickException {
		this.name = name;
		animation = animationFactory.from(name, ANIMATION_DELAY);
	}
	
	public abstract CollisionPolygon move(CollisionPolygon collisionPolygon, Float delta);

	public Animation getAnimation() {
		return animation;
	}

	public Boolean canMove(CollisionPolygon collisionPolygon, Float delta,
			Board board) {
		CollisionPolygon polygon = move(collisionPolygon, delta);
		
		return !board.isCollidingWithWall(polygon);
	}

	public String getName() {
		return name;
	}
}
