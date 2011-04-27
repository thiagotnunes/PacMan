package com.pacman.entity.direction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.geometry.SquarePolygon;

public class NullDirectionTest {

	@Test
	public void shouldNotMovePolygon() throws Exception {
		Direction direction = new NullDirection();
		SquarePolygon polygon = mock(SquarePolygon.class);

		assertSame(polygon, direction.move(polygon, 0));
	}

	@Test
	public void animationShouldBeNull() throws Exception {
		NullDirection direction = new NullDirection();
		assertNull(direction.getAnimation());
	}

}
