package com.pacman.entity.movement;

import org.newdawn.slick.SlickException;

import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.AnimationFactory;

public class Right extends Movement {

	public Right(AnimationFactory animationFactory) throws SlickException {
		super(animationFactory);
	}

	@Override
	public CollisionPolygon move(CollisionPolygon p, Float delta) {
		return p.translate(delta, 0);
	}

	@Override
	public String toString() {
		return "right";
	}

}
