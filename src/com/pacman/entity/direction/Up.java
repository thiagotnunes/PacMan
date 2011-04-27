package com.pacman.entity.direction;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.pacman.entity.character.AnimationFactory;
import com.pacman.geometry.SquarePolygon;

public class Up implements Direction {

	private Animation animation;

	public Up(AnimationFactory animationFactory) throws SlickException {
		animation = animationFactory.from(toString(), ANIMATION_DELAY);
	}

	@Override
	public SquarePolygon move(SquarePolygon p, float delta) {
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
