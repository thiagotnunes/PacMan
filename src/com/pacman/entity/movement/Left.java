package com.pacman.entity.movement;

import org.newdawn.slick.SlickException;

import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.MovementAnimationFactory;

public class Left extends Movement {

	public Left(MovementAnimationFactory animationFactory) throws SlickException {
		super("left", animationFactory);
	}

	@Override
	public CollisionPolygon move(CollisionPolygon p, Float delta) {
		return p.translate(-delta, 0);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Left) {
			Left other = ((Left) obj);
			return name.equals(other.name);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
