package com.pacman.entity.direction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.geometry.SquarePolygon;

public class UpTest {

	@Test
	public void shouldMovePolygonToTheLeft() throws Exception {
		Direction direction = new Up();
		
		Float delta = 1f;
		SquarePolygon polygon = mock(SquarePolygon.class);
		SquarePolygon response = mock(SquarePolygon.class);
		
		when(polygon.translate(0, -delta)).thenReturn(response);
		
		assertSame(response, direction.move(polygon, delta));
		
		verify(polygon).translate(0, -delta);
	}
	
}
