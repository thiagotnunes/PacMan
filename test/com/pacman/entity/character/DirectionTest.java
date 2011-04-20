package com.pacman.entity.character;

import org.junit.Test;

import org.newdawn.slick.Input;

import com.pacman.geometry.SquarePolygon;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;
import static com.pacman.entity.character.Direction.*;

public class DirectionTest {

	@Test
	public void shouldReturnDirectionFromGivenInput() throws Exception {
		validateFromInput(KEY_DOWN, DOWN, UP);
		validateFromInput(KEY_UP, UP, DOWN);
		validateFromInput(KEY_RIGHT, RIGHT, LEFT);
		validateFromInput(KEY_LEFT, LEFT, RIGHT);
	}

	@Test
	public void shouldReturnSameDirectionFromInvalidInput() throws Exception {
		validateFromInput(KEY_0, DOWN, DOWN);
		validateFromInput(KEY_A, DOWN, DOWN);
		validateFromInput(KEY_APOSTROPHE, DOWN, DOWN);
	}

	@Test
	public void shouldUpdateSquarePositionUp() throws Exception {
		Direction direction = UP;
		int delta = 1;
		SquarePolygon expected = mock(SquarePolygon.class);

		direction.move(expected, delta);

		verify(expected).translate(0, -delta);
	}

	@Test
	public void shouldUpdateSquarePositionDown() throws Exception {
		Direction direction = DOWN;
		int delta = 1;
		SquarePolygon expected = mock(SquarePolygon.class);

		direction.move(expected, delta);

		verify(expected).translate(0, delta);
	}

	@Test
	public void shouldUpdateSquarePositionRight() throws Exception {
		Direction direction = RIGHT;
		int delta = 1;
		SquarePolygon expected = mock(SquarePolygon.class);

		direction.move(expected, delta);

		verify(expected).translate(delta, 0);
	}

	@Test
	public void shouldUpdateSquarePositionLeft() throws Exception {
		Direction direction = LEFT;
		int delta = 1;
		SquarePolygon expected = mock(SquarePolygon.class);

		direction.move(expected, delta);

		verify(expected).translate(-delta, 0);
	}

	private void validateFromInput(int key, Direction expectedDirection,
			Direction actualDirection) {
		Input input = mock(Input.class);
		when(input.isKeyDown(key)).thenReturn(true);
		assertEquals(expectedDirection, actualDirection.next(input));
	}

}
