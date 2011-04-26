package com.pacman.entity.direction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.geometry.SquarePolygon;

public class RightTest {

	@Test
	public void shouldMovePolygonToTheLeft() throws Exception {
		Direction direction = new Right();
		
		Float delta = 1f;
		SquarePolygon polygon = mock(SquarePolygon.class);
		SquarePolygon response = mock(SquarePolygon.class);
		
		when(polygon.translate(-delta, 0)).thenReturn(response);
		
		assertSame(response, direction.move(polygon, delta));
		
		verify(polygon).translate(-delta, 0);
	}
	
}
