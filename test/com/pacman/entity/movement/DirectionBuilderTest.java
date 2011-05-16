package com.pacman.entity.movement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.MovementBuilder;

public class DirectionBuilderTest {

	private Map<Integer, Movement> movements;
	private Movement up;
	private MovementBuilder movementBuilder;

	@Before
	public void setUp() throws SlickException {
		movements = new HashMap<Integer, Movement>();
		up = mock(Movement.class);
		movements.put(KEY_UP, up);
		
		movementBuilder = new MovementBuilder(up, movements);
	}

	@Test
	public void shouldReturnDirectionFromMappedKey() throws Exception {
		assertSame(up, movementBuilder.from(KEY_UP));
	}
	
	@Test
	public void shouldReturnNullFromUnmappedKey() throws Exception {
		assertNull(movementBuilder.from(KEY_A));
	}
	
	@Test
	public void shouldReturnDefaultDirection() throws Exception {
		assertSame(up, movementBuilder.defaultDirection());
	}

}
