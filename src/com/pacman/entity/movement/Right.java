package com.pacman.entity.movement;

import org.newdawn.slick.SlickException;

import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.MovementAnimationFactory;

public class Right extends BaseMovement {

	public Right(MovementAnimationFactory animationFactory) throws SlickException {
		super("right", animationFactory);
	}

	@Override
	public CollisionPolygon move(CollisionPolygon p, Float delta) {
		return p.translate(delta, 0);
	}
}
