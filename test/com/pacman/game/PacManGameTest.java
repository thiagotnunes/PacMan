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

import com.pacman.entity.character.PacMan;
import com.pacman.entity.character.PacManFactory;
import com.pacman.entity.character.PacManKeyListener;
import com.pacman.entity.character.PacManKeyListenerFactory;
import com.pacman.entity.direction.Direction;
import com.pacman.entity.direction.DirectionBuilder;
import com.pacman.entity.maze.Board;
import com.pacman.entity.maze.BoardFactory;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.renderer.Renderer;

public class PacManGameTest {

	private BasicGame pacManGame;
	private PacManFactory pacManFactory;
	private PacMan pacMan;
	private Renderer renderer;
	private Board board;
	private BoardFactory boardFactory;
	private PacManKeyListener keyListener;
	private GameContainer gameContainer;
	private Input input;
	private PacManKeyListenerFactory keyListenerFactory;
	private DirectionBuilder directionBuilder;

	@Before
	public void setUp() throws SlickException {
		pacMan = mock(PacMan.class);
		pacManFactory = mock(PacManFactory.class);
		boardFactory = mock(BoardFactory.class);
		renderer = mock(Renderer.class);
		board = mock(Board.class);
		keyListener = mock(PacManKeyListener.class);
		keyListenerFactory = mock(PacManKeyListenerFactory.class);
		directionBuilder = mock(DirectionBuilder.class);
		pacManGame = new PacManGame("PacMan", pacManFactory, boardFactory,
				renderer, keyListenerFactory, directionBuilder);

		when(
				pacManFactory.from(any(CollisionPolygon.class),
						any(Direction.class), eq(board))).thenReturn(pacMan);
		when(boardFactory.from(eq(PacManGame.MAP_PATH))).thenReturn(board);
		when(keyListenerFactory.from(pacMan, directionBuilder)).thenReturn(keyListener);

		gameContainer = mock(GameContainer.class);
		input = mock(Input.class);

		when(gameContainer.getInput()).thenReturn(input);

		pacManGame.init(gameContainer);
	}

	@After
	public void tearDown() throws SlickException {
		verify(directionBuilder).buildDirections();
		verify(pacManFactory).from(any(CollisionPolygon.class),
				any(Direction.class), eq(board));
		verify(boardFactory).from(eq(PacManGame.MAP_PATH));
		verify(keyListenerFactory).from(pacMan, directionBuilder);
		verify(gameContainer).getInput();
		verify(input).addKeyListener(keyListener);
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
	public void shouldUpdatePacMan() throws Exception {
		int delta = 1;
		GameContainer gc = mock(GameContainer.class);

		pacManGame.update(gc, delta);

		verify(pacMan).move();
		verify(pacMan).eat(board);
	}
}
