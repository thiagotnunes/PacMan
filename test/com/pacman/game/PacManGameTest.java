package com.pacman.game;

import static com.pacman.entity.character.Direction.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.character.Direction;
import com.pacman.entity.character.PacMan;
import com.pacman.entity.character.PacManFactory;
import com.pacman.entity.maze.Board;
import com.pacman.entity.maze.BoardFactory;
import com.pacman.geometry.SquarePolygon;
import com.pacman.renderer.Renderer;

public class PacManGameTest {

	private BasicGame pacManGame;
	private PacManFactory pacManFactory;
	private PacMan pacMan;
	private Renderer renderer;
	private Board board;
	private BoardFactory boardFactory;

	@Before
	public void setUp() throws SlickException {
		pacMan = mock(PacMan.class);
		pacManFactory = mock(PacManFactory.class);
		boardFactory = mock(BoardFactory.class);
		renderer = mock(Renderer.class);
		board = mock(Board.class);
		pacManGame = new PacManGame("PacMan", pacManFactory, boardFactory,
				renderer);

		when(pacManFactory.create()).thenReturn(pacMan);
		when(boardFactory.from(eq(PacManGame.MAP_PATH))).thenReturn(board);

		pacManGame.init(null);
	}

	@After
	public void tearDown() throws SlickException {
		verify(pacManFactory).create();
		verify(boardFactory).from(eq(PacManGame.MAP_PATH));
	}

	@Test
	public void shouldRenderPacMan() throws Exception {
		Graphics g = mock(Graphics.class);
		pacManGame.render(null, g);

		verify(renderer).render(pacMan, g);
	}

	@Test
	public void shouldRenderBoard() throws Exception {
		Graphics g = mock(Graphics.class);
		pacManGame.render(null, g);

		verify(renderer).render(board, g);
	}

	@Test
	public void shouldMovePacManIfWillNotCollideWithBoard() throws Exception {
		Direction nextDirection = DOWN;
		SquarePolygon collisionPolygon = mock(SquarePolygon.class);

		int delta = 1;
		GameContainer gc = mock(GameContainer.class);
		Input input = mock(Input.class);
		Polygon polygon = mock(Polygon.class);

		when(gc.getInput()).thenReturn(input);
		when(pacMan.currentDirection()).thenReturn(LEFT);
		when(input.isKeyDown(eq(KEY_DOWN))).thenReturn(true);
		when(pacMan.translate(eq(PacMan.SPEED), eq(nextDirection)))
				.thenReturn(collisionPolygon);
		when(collisionPolygon.getPolygon()).thenReturn(polygon);
		when(board.isCollidingWith(eq(polygon))).thenReturn(false);

		pacManGame.update(gc, delta);

		verify(pacMan).updateCollisionPolygon(eq(collisionPolygon));
		verify(pacMan).updateDirection(eq(nextDirection));
	}

	@Test
	public void shouldNotMovePacManIfWillColideWithBoard() throws Exception {
		Direction currentDirection = LEFT;
		int delta = 1;
		GameContainer gc = mock(GameContainer.class);
		Input input = mock(Input.class);
		
		final SquarePolygon collisionPolygon = mock(SquarePolygon.class);
		Polygon polygonForNextDirection = mock(Polygon.class);
		
		final SquarePolygon collisionPolygonForCurrentDirection = mock(SquarePolygon.class);
		Polygon polygonForCurrentDirection = mock(Polygon.class);
		
		Answer<SquarePolygon> collisionPolygonAnswer = new Answer<SquarePolygon>() {
			private int invocationCount;
			
			public SquarePolygon answer(InvocationOnMock invocation) throws Throwable {
				if (invocationCount == 0) {
					invocationCount++;
					return collisionPolygon;
				}
				return collisionPolygonForCurrentDirection;
			}
		};
		
		when(gc.getInput()).thenReturn(input);
		when(pacMan.currentDirection()).thenReturn(currentDirection);
		when(input.isKeyDown(eq(KEY_DOWN))).thenReturn(true);
		when(pacMan.translate(eq(PacMan.SPEED), any(Direction.class)))
				.thenAnswer(collisionPolygonAnswer);
		when(collisionPolygon.getPolygon()).thenReturn(polygonForNextDirection);
		when(board.isCollidingWith(eq(polygonForNextDirection))).thenReturn(true);

		when(collisionPolygonForCurrentDirection.getPolygon()).thenReturn(
				polygonForCurrentDirection);
		when(board.isCollidingWith(eq(polygonForCurrentDirection))).thenReturn(false);

		pacManGame.update(gc, delta);

		verify(pacMan).updateCollisionPolygon(eq(collisionPolygonForCurrentDirection));
		verify(pacMan).updateDirection(eq(currentDirection));
	}
}
