package com.pacman.entity.character;

import static com.pacman.entity.Direction.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Input;

import com.pacman.entity.Direction;
import com.pacman.entity.PositionedImage;
import com.pacman.entity.character.PacMan;

public class PacManTest {

	private PositionedImage image;
	private PacMan pacMan;

	@Before
	public void setUp() {
		image = mock(PositionedImage.class);
		pacMan = new PacMan(image, LEFT);
	}

	@Test
	public void shouldSetInitialDirectionAndDrawPacMan() throws Exception {
		pacMan.draw();

		verify(image).draw();
	}

	@Test
	public void shouldUpdateDirectionAccordinglyToTheGivenArrowKey()
			throws Exception {
		testDirectionUpdate(KEY_UP, UP);
		testDirectionUpdate(KEY_DOWN, DOWN);
		testDirectionUpdate(KEY_RIGHT, RIGHT);
		testDirectionUpdate(KEY_LEFT, LEFT);
	}

	@Test
	public void shouldMoveIfBoundaryHasNotBeenAchieved() throws Exception {
		pacMan.move(1);

		verify(image).move(LEFT, 1, 1);
	}

	private void testDirectionUpdate(int key, Direction direction) {
		Input input = createInput(key);

		PacMan pacMan = new PacMan(null, DOWN);
		pacMan.updateDirectionIfRequested(input);

		assertSame(direction, pacMan.currentDirection);
	}

	private Input createInput(int key) {
		Input input = mock(Input.class);
		when(input.isKeyDown(key)).thenReturn(true);
		return input;
	}
}
