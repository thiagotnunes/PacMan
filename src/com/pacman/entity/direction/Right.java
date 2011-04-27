package com.pacman.entity.direction;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.pacman.entity.character.AnimationFactory;
import com.pacman.geometry.SquarePolygon;

public class Right implements Direction {

	private Animation animation;

	public Right(AnimationFactory animationFactory) throws SlickException {
		animation = animationFactory.from(toString(), ANIMATION_DELAY);
	}

	@Override
	public SquarePolygon move(SquarePolygon p, float delta) {
		return p.translate(delta, 0);
	}

	@Override
	public Animation getAnimation() {
		return animation;
	}

	@Override
	public String toString() {
		return "right";
	}

}
