package com.pacman.entity.direction;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.AnimationFactory;

public class Left extends Direction {

	private Animation animation;

	public Left(AnimationFactory animationFactory) throws SlickException {
		animation = animationFactory.from(toString(), ANIMATION_DELAY);
	}

	@Override
	public CollisionPolygon move(CollisionPolygon p, Float delta) {
		return p.translate(-delta, 0);
	}

	@Override
	public String toString() {
		return "left";
	}

	@Override
	public Animation getAnimation() {
		return animation;
	}
}
