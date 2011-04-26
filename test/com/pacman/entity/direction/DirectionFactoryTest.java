package com.pacman.entity.direction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Input;

public class DirectionFactoryTest {

	private DirectionFactory factory;
	
	@Before
	public void setUp() {
		factory = new DirectionFactory();
	}
	
	@Test
	public void shouldReturnDirectionFromGivenInput() throws Exception {
		validateFromInput(KEY_DOWN, new Down());
		validateFromInput(KEY_UP, new Up());
		validateFromInput(KEY_RIGHT, new Right());
		validateFromInput(KEY_LEFT, new Left());
	}

	@Test
	public void shouldReturnSameDirectionFromInvalidInput() throws Exception {
		validateFromInput(KEY_0, new NullDirection());
		validateFromInput(KEY_A, new NullDirection());
		validateFromInput(KEY_APOSTROPHE, new NullDirection());
	}
	
	private void validateFromInput(int key, Direction expectedDirection) {
		Input input = mock(Input.class);
		when(input.isKeyDown(key)).thenReturn(true);
		assertEquals(expectedDirection.getClass(), factory.from(input).getClass());
	}
	
}
