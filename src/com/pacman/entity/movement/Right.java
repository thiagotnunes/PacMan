package com.pacman.entity.movement;

import org.newdawn.slick.SlickException;

import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.MovementAnimationFactory;

public class Right extends Movement {

	public Right(MovementAnimationFactory animationFactory) throws SlickException {
		super("right", animationFactory);
	}

	@Override
	public CollisionPolygon move(CollisionPolygon p, Float delta) {
		return p.translate(delta, 0);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Right) {
			Right other = ((Right) obj);
			return name.equals(other.name);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
