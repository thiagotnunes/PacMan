package com.pacman.entity.movement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.Left;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.AnimationFactory;

public class LeftTest {

	@Test
	public void shouldMovePolygonToTheLeft() throws Exception {
		Float delta = 1f;
		CollisionPolygon polygon = mock(CollisionPolygon.class);
		CollisionPolygon response = mock(CollisionPolygon.class);

		when(polygon.translate(-delta, 0)).thenReturn(response);

		Movement movement = new Left(mock(AnimationFactory.class));

		assertSame(response, movement.move(polygon, delta));

		verify(polygon).translate(-delta, 0);
	}

}
