package com.pacman.entity.character;

import static com.pacman.entity.character.Direction.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
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
		createAnimationMap();
		squarePolygon = mock(SquarePolygon.class);

		pacMan = new PacMan(squarePolygon, animationMap, LEFT);
	}

	private void createAnimationMap() {
		animationMap = new HashMap<Direction, Animation>();
		leftAnimation = mock(Animation.class);
		rightAnimation = mock(Animation.class);
		upAnimation = mock(Animation.class);
		downAnimation = mock(Animation.class);
		animationMap.put(LEFT, leftAnimation);
		animationMap.put(RIGHT, rightAnimation);
		animationMap.put(UP, upAnimation);
		animationMap.put(DOWN, downAnimation);
	}

	@Test
	public void currentAnimationAndDirectionShouldBeLeft() throws Exception {
		assertSame(LEFT, pacMan.currentDirection());
		assertSame(leftAnimation, pacMan.currentAnimation());
	}

	@Test
	public void shouldSetInitialDirectionAndDrawPacMan() throws Exception {
		int x = 20;
		int y = 30;
		Point position = new Point(x, y);
		Polygon polygon = mock(Polygon.class);
		Graphics g = mock(Graphics.class);
		
		when(squarePolygon.getPosition()).thenReturn(position);
		when(squarePolygon.getPolygon()).thenReturn(polygon);

		pacMan.draw(g);

		verify(leftAnimation).draw(0, 0, polygon.getWidth(),
				polygon.getHeight());
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

	@Test
	public void shouldTranslatePacMan() throws Exception {
		float delta = 1;
		SquarePolygon expected = mock(SquarePolygon.class);

		when(squarePolygon.translate(-delta, 0)).thenReturn(expected);

		assertSame(expected, pacMan.translate(delta, LEFT));
	}

	private void testDirectionUpdate(int key, Direction direction) {
		PacMan pacMan = new PacMan(null, animationMap, DOWN);

		pacMan.updateDirection(direction);

		assertSame(direction, pacMan.currentDirection());
		assertSame(animationMap.get(direction), pacMan.currentAnimation());
	}

}