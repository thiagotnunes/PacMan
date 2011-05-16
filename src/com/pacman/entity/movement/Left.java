package com.pacman.entity.movement;

import org.newdawn.slick.SlickException;

import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.AnimationFactory;

public class Left extends Movement {

	public Left(AnimationFactory animationFactory) throws SlickException {
		super(animationFactory);
	}

	@Override
	public CollisionPolygon move(CollisionPolygon p, Float delta) {
		return p.translate(-delta, 0);
	}

	@Override
	public String toString() {
		return "left";
	}

}
