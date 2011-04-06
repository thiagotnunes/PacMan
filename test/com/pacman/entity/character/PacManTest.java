package com.pacman.entity.character;

import static com.pacman.entity.Direction.DOWN;
import static com.pacman.entity.Direction.LEFT;
import static com.pacman.entity.Direction.RIGHT;
import static com.pacman.entity.Direction.UP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.newdawn.slick.Input.KEY_DOWN;
import static org.newdawn.slick.Input.KEY_LEFT;
import static org.newdawn.slick.Input.KEY_RIGHT;
import static org.newdawn.slick.Input.KEY_UP;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.Point;
import org.newdawn.slick.Input;

import com.pacman.entity.Direction;
import com.pacman.entity.PositionedImage;
import com.pacman.renderer.Renderable;

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
	
	@Test
	public void shouldReturnCurrentPosition() throws Exception {
		Point position = new Point(10, 10);
		PositionedImage image = mock(PositionedImage.class);
		Renderable renderable = new PacMan(image, LEFT);
		
		when(image.getPosition()).thenReturn(position);
		
		assertEquals(position, renderable.getPosition());
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