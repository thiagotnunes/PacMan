package com.pacman.entity.movement;

import org.newdawn.slick.Animation;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;


public interface Movement {

	Integer MOVEMENT_ANIMATION_DELAY = 70;

	Boolean canMove(CollisionPolygon collisionPolygon, Float delta, Board board);
	
	CollisionPolygon move(CollisionPolygon collisionPolygon, Float delta);

	Animation getAnimation();
	
	String name();
}
