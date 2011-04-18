package com.pacman.entity;

import org.newdawn.slick.geom.Shape;

public interface Collidable {

	boolean isCollidingWith(Shape shape);

}
