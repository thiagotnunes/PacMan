package com.pacman.entity.direction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.pacman.entity.character.AnimationFactory;

public class DirectionBuilderTest {

	private DirectionBuilder directionBuilder;
	private AnimationFactory animationFactory;

	@Before
	public void setUp() throws SlickException {
		animationFactory = mock(AnimationFactory.class);
		directionBuilder = new DirectionBuilder(animationFactory);
		
		directionBuilder.buildDirectionMap();
	}

	@Test
	public void defaultDirectionShouldBeLeft() throws Exception {
		assertEquals(new Left(animationFactory).getClass(), directionBuilder
				.defaultDirection().getClass());
	}

	@Test
	public void shouldReturnDirectionFromGivenInput() throws Exception {
		validateFromInput(KEY_DOWN, new Down(animationFactory));
		validateFromInput(KEY_UP, new Up(animationFactory));
		validateFromInput(KEY_RIGHT, new Right(animationFactory));
		validateFromInput(KEY_LEFT, new Left(animationFactory));
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
		assertEquals(expectedDirection.getClass(), directionBuilder.from(input)
				.getClass());
	}

}
