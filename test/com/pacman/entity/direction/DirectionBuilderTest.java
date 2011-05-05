package com.pacman.entity.direction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

public class DirectionBuilderTest {

	private Map<Integer, Direction> directions;
	private Direction up;
	private DirectionBuilder directionBuilder;

	@Before
	public void setUp() throws SlickException {
		directions = new HashMap<Integer, Direction>();
		up = mock(Direction.class);
		directions.put(KEY_UP, up);
		
		directionBuilder = new DirectionBuilder(up, directions);
	}

	@Test
	public void shouldReturnDirectionFromMappedKey() throws Exception {
		assertSame(up, directionBuilder.from(KEY_UP));
	}
	
	@Test
	public void shouldReturnNullFromUnmappedKey() throws Exception {
		assertNull(directionBuilder.from(KEY_A));
	}
	
	@Test
	public void shouldReturnDefaultDirection() throws Exception {
		assertSame(up, directionBuilder.defaultDirection());
	}

}
