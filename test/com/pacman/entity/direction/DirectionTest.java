package com.pacman.entity.direction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Animation;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;


public class DirectionTest {

	@Test
	public void shouldVerifyIfPolygonCanBeMoved() throws Exception {
		Direction direction = new Direction() {
			@Override
			public Animation getAnimation() {
				return null;
			}

			@Override
			public CollisionPolygon move(CollisionPolygon p, Float delta) {
				return p;
			}};
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		Float SPEED = 0.5f;
		Board board = mock(Board.class);
		boolean isColliding = true;
		
		when(board.isCollidingWith(collisionPolygon)).thenReturn(isColliding);
		
		assertEquals(!isColliding, direction.canMove(collisionPolygon, SPEED, board));
	}
	
}
