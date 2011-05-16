package com.pacman.entity.movement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.Up;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.AnimationFactory;

public class UpTest {

	@Test
	public void shouldMovePolygonToTheLeft() throws Exception {
		Float delta = 1f;
		CollisionPolygon polygon = mock(CollisionPolygon.class);
		CollisionPolygon response = mock(CollisionPolygon.class);

		when(polygon.translate(0, -delta)).thenReturn(response);

		Movement movement = new Up(mock(AnimationFactory.class));

		assertSame(response, movement.move(polygon, delta));

		verify(polygon).translate(0, -delta);
	}

}
