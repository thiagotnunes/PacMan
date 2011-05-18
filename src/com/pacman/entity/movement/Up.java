package com.pacman.entity.movement;

import org.newdawn.slick.SlickException;

import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.MovementAnimationFactory;

public class Up extends BaseMovement {

	public Up(MovementAnimationFactory animationFactory) throws SlickException {
		super("up", animationFactory);
	}

	@Override
	public CollisionPolygon move(CollisionPolygon p, Float delta) {
		return p.translate(0, -delta);
	}
}
