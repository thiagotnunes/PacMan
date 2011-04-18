package com.pacman.game;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import com.pacman.entity.character.PacMan;
import com.pacman.entity.character.PacManFactory;
import com.pacman.entity.maze.Board;
import com.pacman.entity.maze.BoardFactory;
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
		when(boardFactory.create(eq(PacManGame.MAP_PATH), any(Integer.class))).thenReturn(board);

		pacManGame.init(null);
	}

	@After
	public void tearDown() throws SlickException {
		verify(pacManFactory).create();
		verify(boardFactory).create(eq(PacManGame.MAP_PATH), any(Integer.class));
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
	public void shouldUpdatePacManAccordinglyToInput() throws Exception {
		GameContainer gc = mock(GameContainer.class);
		Input input = mock(Input.class);
		when(gc.getInput()).thenReturn(input);

		int delta = 0;
		pacManGame.update(gc, delta);

		verify(pacMan).updateDirectionIfRequested(input);
	}

	@Test
	public void shouldMovePacManIfWillNotCollideWithBoard() throws Exception {
		int delta = 1;

		validatePacManCollissionWithMaze(delta, false);

		verify(pacMan).move(eq(delta));
	}

	@Test
	public void shouldNotMovePacManIfWillColideWithBoard() throws Exception {
		int delta = 1;

		validatePacManCollissionWithMaze(delta, true);

		verify(pacMan, never()).move(eq(delta));
	}

	private void validatePacManCollissionWithMaze(int delta, boolean isColliding)
			throws SlickException {
		GameContainer gc = mock(GameContainer.class);
		Shape shape = mock(Shape.class);

		when(pacMan.updatedShape(eq(delta))).thenReturn(shape);
		when(board.isCollidingWith(shape)).thenReturn(isColliding);

		pacManGame.update(gc, delta);

		verify(board).isCollidingWith(shape);
	}
}
