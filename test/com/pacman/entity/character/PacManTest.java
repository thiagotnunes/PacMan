package com.pacman.entity.character;

import static com.pacman.entity.character.Direction.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.Point;
import com.pacman.entity.maze.Board;
import com.pacman.geometry.SquarePolygon;
import com.pacman.renderer.Renderable;

public class PacManTest {

	private PacMan pacMan;
	private Map<Direction, Animation> animationMap;
	private Animation leftAnimation;
	private Animation rightAnimation;
	private Animation upAnimation;
	private Animation downAnimation;
	private SquarePolygon collisionPolygon;

	@Before
	public void setUp() {
		createAnimationMap();
		collisionPolygon = mock(SquarePolygon.class);

		pacMan = new PacMan(collisionPolygon, animationMap, LEFT);
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
		Float x = 20f;
		Float y = 30f;
		Point position = new Point(x, y);
		Polygon polygon = mock(Polygon.class);
		Graphics g = mock(Graphics.class);

		when(collisionPolygon.getPosition()).thenReturn(position);
		when(collisionPolygon.getPolygon()).thenReturn(polygon);

		pacMan.draw(g);

		verify(leftAnimation).draw(0, 0, polygon.getWidth(),
				polygon.getHeight());
	}

	@Test
	public void shouldReturnCurrentPosition() throws Exception {
		Renderable renderable = new PacMan(collisionPolygon, animationMap, LEFT);
		Point position = new Point(10f, 20f);

		when(collisionPolygon.getPosition()).thenReturn(position);

		assertSame(position, renderable.getPosition());
	}

	@Test
	public void shouldMovePacManIfWillNotCollideWithBoard() throws Exception {
		int delta = 1;
		GameContainer gc = mock(GameContainer.class);
		Board board = mock(Board.class);
		Input input = mock(Input.class);
		Polygon polygon = mock(Polygon.class);
		SquarePolygon movedCollisionPolygon = mock(SquarePolygon.class);

		when(gc.getInput()).thenReturn(input);
		when(input.isKeyDown(eq(KEY_DOWN))).thenReturn(true);
		when(collisionPolygon.getPolygon()).thenReturn(polygon);
		when(board.isCollidingWith(eq(polygon))).thenReturn(false);
		when(collisionPolygon.translate(0, 0.5f)).thenReturn(
				movedCollisionPolygon);

		pacMan.update(gc, delta, board);

		assertEquals(DOWN, pacMan.currentDirection());
		assertEquals(downAnimation, pacMan.currentAnimation());
		assertSame(movedCollisionPolygon, pacMan.currentCollisionPolygon());
	}

	@Test
	public void shouldNotMovePacManIfWillCollideWithBoard() throws Exception {
		int delta = 1;
		GameContainer gc = mock(GameContainer.class);
		Board board = mock(Board.class);
		Input input = mock(Input.class);
		Polygon polygon = mock(Polygon.class);
		final SquarePolygon movedCollisionPolygon = mock(SquarePolygon.class);

		when(gc.getInput()).thenReturn(input);
		when(input.isKeyDown(eq(KEY_DOWN))).thenReturn(true);
		when(collisionPolygon.translate(0, 0.5f)).thenReturn(
				movedCollisionPolygon);
		when(collisionPolygon.translate(-0.5f, 0)).thenReturn(collisionPolygon);
		when(movedCollisionPolygon.getPolygon()).thenReturn(polygon);
		when(collisionPolygon.getPolygon()).thenReturn(polygon);
		when(board.isCollidingWith(eq(polygon))).thenReturn(true);

		pacMan.update(gc, delta, board);

		assertEquals(LEFT, pacMan.currentDirection());
		assertEquals(leftAnimation, pacMan.currentAnimation());
		assertSame(collisionPolygon, pacMan.currentCollisionPolygon());
	}

}