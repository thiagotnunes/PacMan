package com.pacman.entity.movement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.MovementAnimationFactory;

public class DownTest {

	@Test
	public void shouldMovePolygonToTheLeft() throws Exception {
		Float delta = 1f;
		MovementAnimationFactory animationFactory = mock(MovementAnimationFactory.class);
		CollisionPolygon polygon = mock(CollisionPolygon.class);
		CollisionPolygon response = mock(CollisionPolygon.class);

		when(polygon.translate(0, delta)).thenReturn(response);

		Movement movement = new Down(animationFactory);

		assertSame(response, movement.move(polygon, delta));

		verify(polygon).translate(0, delta);
	}
	
	@Test
	public void shouldBeEquals() throws Exception {
		MovementAnimationFactory animationFactory = mock(MovementAnimationFactory.class);
		Down down = new Down(animationFactory);
		Down other = new Down(animationFactory);
	
		assertEquals(down, other);
		assertEquals(down.hashCode(), other.hashCode());
	}
	
	@Test
	public void shouldNotBeEquals() throws Exception {
		MovementAnimationFactory animationFactory = mock(MovementAnimationFactory.class);
		Down down = new Down(animationFactory);
		Up other = new Up(animationFactory);
	
		assertFalse(down.equals(other));
		assertFalse(down.hashCode() == other.hashCode());
	}
}
