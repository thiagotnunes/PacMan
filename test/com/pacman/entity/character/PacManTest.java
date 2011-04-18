package com.pacman.entity.character;

import static com.pacman.entity.character.Direction.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.Point;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Polygon;

import com.pacman.geometry.SquarePolygon;
import com.pacman.renderer.Renderable;

public class PacManTest {

	private PacMan pacMan;
	private Map<Direction, Animation> animationMap;
	private Animation leftAnimation;
	private Animation rightAnimation;
	private Animation upAnimation;
	private Animation downAnimation;
	private SquarePolygon squarePolygon;

	@Before
	public void setUp() {
		animationMap = new HashMap<Direction, Animation>();
		leftAnimation = mock(Animation.class);
		rightAnimation = mock(Animation.class);
		upAnimation = mock(Animation.class);
		downAnimation = mock(Animation.class);
		squarePolygon = mock(SquarePolygon.class);
		animationMap.put(LEFT, leftAnimation);
		animationMap.put(RIGHT, rightAnimation);
		animationMap.put(UP, upAnimation);
		animationMap.put(DOWN, downAnimation);

		pacMan = new PacMan(squarePolygon, animationMap, LEFT);
	}

	@Test
	public void currentAnimationShouldBeLeft() throws Exception {
		assertSame(leftAnimation, pacMan.currentAnimation);
	}

	@Test
	public void shouldSetInitialDirectionAndDrawPacMan() throws Exception {
		Point position = new Point(20, 20);
		Polygon polygon = mock(Polygon.class);
		
		when(squarePolygon.getPosition()).thenReturn(position);
		when(squarePolygon.getPolygon()).thenReturn(polygon);
		
		pacMan.draw(null);

		verify(leftAnimation).draw(0, 0, polygon.getWidth(), polygon.getHeight());
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
		Renderable renderable = new PacMan(squarePolygon, animationMap, LEFT);
		Point position = new Point(10, 20);

		when(squarePolygon.getPosition()).thenReturn(position);
		
		assertSame(position, renderable.getPosition());
	}

	private void testDirectionUpdate(int key, Direction direction) {
		Input input = createInput(key);

		PacMan pacMan = new PacMan(null, animationMap, DOWN);
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