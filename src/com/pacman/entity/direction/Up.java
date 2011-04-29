package com.pacman.entity.direction;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.pacman.entity.character.AnimationFactory;
import com.pacman.geometry.CollisionPolygon;

public class Up extends Direction {

	private Animation animation;

	public Up(AnimationFactory animationFactory) throws SlickException {
		animation = animationFactory.from(toString(), ANIMATION_DELAY);
	}

	@Override
	public CollisionPolygon move(CollisionPolygon p, Float delta) {
		return p.translate(0, -delta);
	}

	@Override
	public Animation getAnimation() {
		return animation;
	}

	@Override
	public String toString() {
		return "up";
	}

}
