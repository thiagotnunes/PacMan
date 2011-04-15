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

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;

import com.pacman.entity.Direction;
import com.pacman.renderer.Renderable;

public class PacManTest {

	private static final float SPEED = 0.1f;

	private PacMan pacMan;
	private Map<Direction, Animation> animationMap;
	private Animation leftAnimation;
	private Animation rightAnimation;
	private Animation upAnimation;
	private Animation downAnimation;
	private Point position;
	private Dimension dimension;

	@Before
	public void setUp() {
		animationMap = new HashMap<Direction, Animation>();
		leftAnimation = mock(Animation.class);
		rightAnimation = mock(Animation.class);
		upAnimation = mock(Animation.class);
		downAnimation = mock(Animation.class);
		animationMap.put(LEFT, leftAnimation);
		animationMap.put(RIGHT, rightAnimation);
		animationMap.put(UP, upAnimation);
		animationMap.put(DOWN, downAnimation);
		position = new Point(10, 10);
		dimension = new Dimension(20, 20);

		pacMan = new PacMan(position, dimension, animationMap, LEFT);
	}

	@Test
	public void currentAnimationShouldBeLeft() throws Exception {
		assertSame(leftAnimation, pacMan.currentAnimation);
	}

	@Test
	public void shouldSetInitialDirectionAndDrawPacMan() throws Exception {
		pacMan.draw(null);

		verify(leftAnimation).draw(position.getX() * SPEED,
				position.getY() * SPEED, dimension.getWidth(),
				dimension.getHeight());
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
	public void shouldReturnCurrentPosition() throws Exception {
		Point position = new Point(10, 10);
		Renderable renderable = new PacMan(position, dimension, animationMap,
				LEFT);

		assertEquals(position, renderable.getPosition());
	}

	@Test
	public void shouldMoveAccordinglyToTheDirection() throws Exception {
		validatePosition(UP, new Point(0, -1));
		validatePosition(DOWN, new Point(0, 1));
		validatePosition(LEFT, new Point(-1, 0));
		validatePosition(RIGHT, new Point(1, 0));
	}

	private void validatePosition(Direction direction, Point expectedPosition) {
		PacMan pacMan = new PacMan(new Point(0, 0), dimension, animationMap,
				direction);
		pacMan.move(1);
		assertEquals(expectedPosition, pacMan.getPosition());
	}

	private void testDirectionUpdate(int key, Direction direction) {
		Input input = createInput(key);

		PacMan pacMan = new PacMan(null, null, animationMap, DOWN);
		pacMan.updateDirectionIfRequested(input);

		assertSame(direction, pacMan.currentDirection);
		assertSame(animationMap.get(direction), pacMan.currentAnimation);
	}

	private Input createInput(int key) {
		Input input = mock(Input.class);
		when(input.isKeyDown(key)).thenReturn(true);
		return input;
	}
}