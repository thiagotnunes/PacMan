package com.pacman.entity.movement;

import org.newdawn.slick.SlickException;

import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.MovementAnimationFactory;

public class Left extends BaseMovement {

	public Left(MovementAnimationFactory animationFactory) throws SlickException {
		super("left", animationFactory);
	}

	@Override
	public CollisionPolygon move(CollisionPolygon p, Float delta) {
		return p.translate(-delta, 0);
	}
	
}
