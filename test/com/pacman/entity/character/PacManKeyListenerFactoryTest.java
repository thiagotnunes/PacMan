package com.pacman.entity.character;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.direction.DirectionBuilder;


public class PacManKeyListenerFactoryTest {
	
	@Test
	public void shouldCreateListenerForPacMan() throws Exception {
		PacManKeyListenerFactory factory = new PacManKeyListenerFactory();
		DirectionBuilder directionBuilder = mock(DirectionBuilder.class);
		PacMan pacMan = mock(PacMan.class);
		
		PacManKeyListener listener = factory.from(pacMan, directionBuilder);
		
		assertSame(pacMan, listener.pacMan);
		assertSame(directionBuilder, listener.directionBuilder);
	}

}
