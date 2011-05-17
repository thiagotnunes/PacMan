package com.pacman.entity.movement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.Left;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.MovementAnimationFactory;

public class LeftTest {

	@Test
	public void shouldMovePolygonToTheLeft() throws Exception {
		Float delta = 1f;
		CollisionPolygon polygon = mock(CollisionPolygon.class);
		CollisionPolygon response = mock(CollisionPolygon.class);

		when(polygon.translate(-delta, 0)).thenReturn(response);

		Movement movement = new Left(mock(MovementAnimationFactory.class));

		assertSame(response, movement.move(polygon, delta));

		verify(polygon).translate(-delta, 0);
	}
	
	@Test
	public void shouldBeEquals() throws Exception {
		MovementAnimationFactory animationFactory = mock(MovementAnimationFactory.class);
		Left left = new Left(animationFactory);
		Left other = new Left(animationFactory);
	
		assertEquals(left, other);
		assertEquals(left.hashCode(), other.hashCode());
	}
	
	@Test
	public void shouldNotBeEquals() throws Exception {
		MovementAnimationFactory animationFactory = mock(MovementAnimationFactory.class);
		Left left = new Left(animationFactory);
		Right other = new Right(animationFactory);
	
		assertFalse(left.equals(other));
		assertFalse(left.hashCode() == other.hashCode());
	}

}
