package com.pacman.entity.character;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.direction.DirectionBuilder;
import com.pacman.geometry.CollisionPolygon;

public class PacManFactoryTest {

	@Test
	public void shouldCreatePacManUsingPolygon() throws Exception {
		PacManFactory pacManFactory = new PacManFactory(
				mock(DirectionBuilder.class));

		assertNotNull(pacManFactory.from(mock(CollisionPolygon.class)));
	}

}
