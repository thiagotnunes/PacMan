package com.pacman.game;

import static com.pacman.entity.character.Direction.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

		verify(renderer).render(pacMan, g, PacMan.SPEED);
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

		mockPacManCollision(nextDirection, false, collisionPolygon);

		verify(pacMan).updateCollisionPolygon(eq(collisionPolygon));
		verify(pacMan).updateDirection(eq(nextDirection));
	}

	@Test
	public void shouldNotMovePacManIfWillColideWithBoard() throws Exception {
		Direction nextDirection = DOWN;
		SquarePolygon collisionPolygon = mock(SquarePolygon.class);

		mockPacManCollision(nextDirection, true, collisionPolygon);

		verify(pacMan, never()).updateCollisionPolygon(eq(collisionPolygon));
		verify(pacMan, never()).updateDirection(eq(nextDirection));
	}

	private void mockPacManCollision(Direction nextDirection,
			boolean isColliding, SquarePolygon collisionPolygon)
			throws SlickException {
		int delta = 1;
		GameContainer gc = mock(GameContainer.class);
		Input input = mock(Input.class);
		Polygon polygon = mock(Polygon.class);

		when(gc.getInput()).thenReturn(input);
		when(pacMan.currentDirection()).thenReturn(LEFT);
		when(input.isKeyDown(eq(KEY_DOWN))).thenReturn(true);
		when(pacMan.translate(eq(delta * PacMan.SPEED), eq(nextDirection)))
				.thenReturn(collisionPolygon);
		when(collisionPolygon.getPolygon()).thenReturn(polygon);
		when(board.isCollidingWith(eq(polygon))).thenReturn(isColliding);

		pacManGame.update(gc, delta);
	}

}
