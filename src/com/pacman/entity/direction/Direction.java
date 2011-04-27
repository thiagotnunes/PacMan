package com.pacman.entity.direction;

import org.newdawn.slick.Animation;

import com.pacman.geometry.SquarePolygon;

public interface Direction {

	Integer ANIMATION_DELAY = 70;

	SquarePolygon move(SquarePolygon p, float delta);

	Animation getAnimation();

}
