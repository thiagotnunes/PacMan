package com.pacman.entity.direction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Animation;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.SquarePolygon;


public class DirectionTest {

	@Test
	public void shouldVerifyIfPolygonCanBeMoved() throws Exception {
		Direction direction = new Direction() {
			@Override
			public Animation getAnimation() {
				return null;
			}

			@Override
			public SquarePolygon move(SquarePolygon p, Float delta) {
				return p;
			}};
		SquarePolygon collisionPolygon = mock(SquarePolygon.class);
		Float SPEED = 0.5f;
		Board board = mock(Board.class);
		boolean isColliding = true;
		
		when(board.isCollidingWith(collisionPolygon)).thenReturn(isColliding);
		
		assertEquals(!isColliding, direction.canMove(collisionPolygon, SPEED, board));
	}
	
}
