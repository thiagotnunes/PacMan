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

public class MovementBuilderTest {

	private Map<Integer, Movement> movements;
	private Map<Movement, Stopped> stoppedMovements;
	private Movement up;
	private MovementBuilder movementBuilder;
	private Stopped stoppedUp;

	@Before
	public void setUp() throws SlickException {
		up = mock(Movement.class);
		stoppedUp = mock(Stopped.class);
		
		movements = new HashMap<Integer, Movement>();
		movements.put(KEY_UP, up);
		
		stoppedMovements = new HashMap<Movement, Stopped>();
		stoppedMovements.put(up, stoppedUp);
		
		movementBuilder = new MovementBuilder(up, movements, stoppedMovements);
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
	public void shouldReturnDefaultMovement() throws Exception {
		assertSame(up, movementBuilder.defaultMovement());
	}
	
	@Test
	public void shouldReturnStopped() throws Exception {
		assertSame(stoppedUp , movementBuilder.stoppedFrom(up));
	}
	
}
