package com.pacman.entity.movement;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Animation;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.MovementAnimationFactory;

public class BaseMovementTest {

	@Test
	public void shouldVerifyIfPolygonCanBeMoved() throws Exception {
		Movement movement = new BaseMovement("", mock(MovementAnimationFactory.class)) {
			@Override
			public CollisionPolygon move(CollisionPolygon p, Float delta) {
				return p;
			}
		};
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		Float SPEED = 0.5f;
		Board board = mock(Board.class);
		boolean isColliding = true;

		when(board.isCollidingWithWall(collisionPolygon)).thenReturn(
				isColliding);

		assertEquals(!isColliding, movement.canMove(collisionPolygon, SPEED,
				board));
	}

	@Test
	public void shouldGetDefaultAnimationWhenItIsNotStopped() throws Exception {
		MovementAnimationFactory animationFactory = mock(MovementAnimationFactory.class);
		Animation animation = mock(Animation.class);
		
		when(animationFactory.from(any(String.class), anyInt())).thenReturn(animation);
		
		Movement movement = new BaseMovement("", animationFactory) {
			@Override
			public CollisionPolygon move(CollisionPolygon collisionPolygon,
					Float delta) {
				return null;
			}
		};
		
		assertSame(animation, movement.getAnimation());
	}
	
}