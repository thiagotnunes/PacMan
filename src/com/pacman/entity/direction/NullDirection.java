package com.pacman.entity.direction;

import org.newdawn.slick.Animation;

import com.pacman.geometry.SquarePolygon;

public class NullDirection implements Direction {

	@Override
	public SquarePolygon move(SquarePolygon p, float delta) {
		return p;
	}

	@Override
	public Animation getAnimation() {
		return null;
	}

}
