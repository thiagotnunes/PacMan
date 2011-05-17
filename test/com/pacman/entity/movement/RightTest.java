package com.pacman.entity.movement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.Right;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.MovementAnimationFactory;

public class RightTest {

	@Test
	public void shouldMovePolygonToTheLeft() throws Exception {
		Float delta = 1f;
		CollisionPolygon polygon = mock(CollisionPolygon.class);
		CollisionPolygon response = mock(CollisionPolygon.class);

		when(polygon.translate(delta, 0)).thenReturn(response);

		Movement movement = new Right(mock(MovementAnimationFactory.class));

		assertSame(response, movement.move(polygon, delta));

		verify(polygon).translate(delta, 0);
	}

}
