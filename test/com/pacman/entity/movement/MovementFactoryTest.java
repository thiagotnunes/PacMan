package com.pacman.entity.movement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

public class MovementFactoryTest {

	private Map<Integer, Movement> movements;
	private Map<Movement, Stopped> stoppedMovements;
	private Movement up;
	private MovementFactory movementFactory;
	private Stopped stoppedUp;

	@Before
	public void setUp() throws SlickException {
		up = mock(Movement.class);
		stoppedUp = mock(Stopped.class);
		
		movements = new HashMap<Integer, Movement>();
		movements.put(KEY_UP, up);
		
		stoppedMovements = new HashMap<Movement, Stopped>();
		stoppedMovements.put(up, stoppedUp);
		
		movementFactory = new MovementFactory(up, movements, stoppedMovements);
	}

	@Test
	public void shouldReturnDirectionFromMappedKey() throws Exception {
		assertSame(up, movementFactory.from(KEY_UP));
	}
	
	@Test
	public void shouldReturnNullFromUnmappedKey() throws Exception {
		assertNull(movementFactory.from(KEY_A));
	}
	
	@Test
	public void shouldReturnDefaultMovement() throws Exception {
		assertSame(up, movementFactory.defaultMovement());
	}
	
	@Test
	public void shouldReturnStopped() throws Exception {
		assertSame(stoppedUp , movementFactory.stoppedFrom(up));
	}
	
	@Test
	public void shouldReturnNullMovement() throws Exception {
		assertTrue(movementFactory.nullMovement() instanceof NullMovement);
	}
	
}
