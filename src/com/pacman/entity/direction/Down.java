package com.pacman.entity.direction;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.pacman.entity.character.AnimationFactory;
import com.pacman.geometry.SquarePolygon;

public class Down extends Direction {

	private Animation animation;

	public Down(AnimationFactory animationFactory) throws SlickException {
		animation = animationFactory
				.from(toString(), Direction.ANIMATION_DELAY);
	}

	@Override
	public SquarePolygon move(SquarePolygon p, Float delta) {
		return p.translate(0, delta);
	}

	@Override
	public Animation getAnimation() {
		return animation;
	}

	@Override
	public String toString() {
		return "down";
	}
}
