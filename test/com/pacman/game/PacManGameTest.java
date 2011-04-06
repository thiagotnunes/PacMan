package com.pacman.game;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.Dimension;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.pacman.entity.character.PacMan;
import com.pacman.entity.character.PacManFactory;
import com.pacman.renderer.Renderer;

public class PacManGameTest {

	private BasicGame pacManGame;
	private Dimension dimension;
	private PacManFactory pacManFactory;
	private PacMan pacMan;
	private Renderer renderer;

	@Before
	public void setUp() throws SlickException {
		pacMan = mock(PacMan.class);
		dimension = new Dimension(100, 100);
		pacManFactory = mock(PacManFactory.class);
		renderer = mock(Renderer.class);
		pacManGame = new PacManGame("PacMan", pacManFactory, dimension, renderer);
		
		when(pacManFactory.create()).thenReturn(pacMan);
		
		pacManGame.init(null);
	}
	
	@After
	public void tearDown() throws SlickException {
		verify(pacManFactory).create();
	}
	
	@Test
	public void shouldRenderPacMan() throws Exception {
		Graphics g = mock(Graphics.class);
		pacManGame.render(null, g);
		
		verify(renderer).render(pacMan, g, PacMan.SPEED);
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
