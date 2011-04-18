package com.pacman.entity.character;

import static org.junit.Assert.assertNotNull;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.AdditionalMatchers.aryEq;

import java.io.File;

import org.junit.Test;


public class PacManFactoryTest {

	@Test
	public void shouldCreateAPacManWithAnimationsForAllDirections()
			throws Exception {
		AnimationFactory animationFactory = mock(AnimationFactory.class);
		PacMan pacMan = new PacManFactory(animationFactory).create();

		verify(animationFactory).createFromPath(eq(70),
				aryEq(createPathsFrom(Direction.DOWN.toString().toLowerCase())));
		verify(animationFactory).createFromPath(eq(70),
				aryEq(createPathsFrom(Direction.LEFT.toString().toLowerCase())));
		verify(animationFactory).createFromPath(eq(70),
				aryEq(createPathsFrom(Direction.RIGHT.toString().toLowerCase())));
		verify(animationFactory).createFromPath(eq(70),
				aryEq(createPathsFrom(Direction.UP.toString().toLowerCase())));
		
		assertNotNull(pacMan);
	}

	private String[] createPathsFrom(String direction) {
		String prefix = "data" + File.separator + "pacman"
				+ File.separator + direction + File.separator + "Pacman_" + direction + "-";
		String extension = ".png";
		
		String[] paths = new String[4];
		for (int i=0; i<paths.length; i++) {
			paths[i] = prefix + i + extension;
		}
		return paths;
	}

}
