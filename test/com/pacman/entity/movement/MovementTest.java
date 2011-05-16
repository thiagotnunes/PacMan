package com.pacman.entity.movement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Animation;

import com.pacman.entity.maze.Board;
import com.pacman.entity.movement.Movement;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.AnimationFactory;

public class MovementTest {

	@Test
	public void shouldVerifyIfPolygonCanBeMoved() throws Exception {
		Movement movement = new Movement(mock(AnimationFactory.class)) {
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
		AnimationFactory animationFactory = mock(AnimationFactory.class);
		Animation stopped = mock(Animation.class);
		Animation animation = mock(Animation.class);
		
		when(animationFactory.from(any(Movement.class), anyInt(), eq(false))).thenReturn(animation);
		when(animationFactory.from(any(Movement.class), anyInt(), eq(true))).thenReturn(stopped);
		
		Movement movement = new Movement(animationFactory) {
			@Override
			public CollisionPolygon move(CollisionPolygon collisionPolygon,
					Float delta) {
				return null;
			}
		};
		
		assertSame(animation, movement.getAnimation());
	}
	
	@Test
	public void shouldGetStoppedAnimationWhenItIsStopped() throws Exception {
		AnimationFactory animationFactory = mock(AnimationFactory.class);
		Animation animation = mock(Animation.class);
		Animation stopped = mock(Animation.class);
		
		when(animationFactory.from(any(Movement.class), anyInt(), eq(false))).thenReturn(animation);
		when(animationFactory.from(any(Movement.class), anyInt(), eq(true))).thenReturn(stopped);
		
		Movement movement = new Movement(animationFactory) {
			@Override
			public CollisionPolygon move(CollisionPolygon collisionPolygon,
					Float delta) {
				return null;
			}
		};
		
		movement.stop();
		
		assertSame(stopped, movement.getAnimation());
	}

}
