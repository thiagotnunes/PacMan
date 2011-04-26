package com.pacman.entity.direction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.geometry.SquarePolygon;


public class DownTest {

	@Test
	public void shouldMovePolygonToTheLeft() throws Exception {
		Direction direction = new Down();
		
		Float delta = 1f;
		SquarePolygon polygon = mock(SquarePolygon.class);
		SquarePolygon response = mock(SquarePolygon.class);
		
		when(polygon.translate(0, delta)).thenReturn(response);
		
		assertSame(response, direction.move(polygon, delta));
		
		verify(polygon).translate(0, delta);
	}
	
}
