package com.pacman.game;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.Dimension;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import com.pacman.entity.character.PacMan;

public class PacManGameTest {

	private PacMan pacMan;
	private BasicGame pacManGame;
	private Dimension dimension;

	@Before
	public void setUp() {
		pacMan = mock(PacMan.class);
		dimension = new Dimension(100, 100);
		pacManGame = new PacManGame("PacMan", pacMan, dimension);
	}

	@Test
	public void shouldRenderPacMan() throws Exception {
		pacManGame.render(null, null);

		verify(pacMan).draw();
	}

	@Test
	public void shouldUpdatePacManAccordinglyToInput() throws Exception {
		GameContainer gc = mock(GameContainer.class);
		Input input = mock(Input.class);
		when(gc.getInput()).thenReturn(input);

		int delta = 0;
		pacManGame.update(gc, delta);

		verify(pacMan).updateDirectionIfRequested(input);
		verify(pacMan).move(delta);
	}
}
