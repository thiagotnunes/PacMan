package com.pacman.game;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.pacman.entity.character.PacMan;
import com.pacman.entity.maze.Board;

public class PacManGameTest {

	private PacManGame pacManGame;
	private PacMan pacMan;
	private Board board;
	private GameContainer gameContainer;
	private Input input;

	@Before
	public void setUp() throws SlickException {
		pacMan = mock(PacMan.class);
		board = mock(Board.class);
		pacManGame = new PacManGame(pacMan, board);

		gameContainer = mock(GameContainer.class);
		input = mock(Input.class);

		when(gameContainer.getInput()).thenReturn(input);
	}

	@Test
	public void shouldRenderPacMan() throws Exception {
		Graphics g = mock(Graphics.class);
		pacManGame.render(null, g);

		verify(pacMan).draw(g);
	}

	@Test
	public void shouldRenderBoard() throws Exception {
		Graphics g = mock(Graphics.class);
		pacManGame.render(null, g);

		verify(board).draw(g);
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
