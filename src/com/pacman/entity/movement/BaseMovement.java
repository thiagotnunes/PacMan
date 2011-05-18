package com.pacman.entity.movement;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.MovementAnimationFactory;

public abstract class BaseMovement implements Movement {

	private Animation animation;
	protected String name;
	
	public BaseMovement(String name, MovementAnimationFactory animationFactory) throws SlickException {
		this.name = name;
		animation = animationFactory.from(name, MOVEMENT_ANIMATION_DELAY);
	}

	@Override
	public Boolean canMove(CollisionPolygon collisionPolygon, Float delta,
			Board board) {
		CollisionPolygon polygon = move(collisionPolygon, delta);
		
		return !board.isCollidingWithWall(polygon);
	}
	
	@Override
	public Animation getAnimation() {
		return animation;
	}
	
	public String name() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BaseMovement) {
			BaseMovement other = ((BaseMovement) obj);
			return name.equals(other.name);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
