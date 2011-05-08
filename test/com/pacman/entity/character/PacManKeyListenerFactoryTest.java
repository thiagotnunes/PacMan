package com.pacman.entity.character;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.direction.DirectionBuilder;


public class PacManKeyListenerFactoryTest {
	
	@Test
	public void shouldCreateListenerForPacMan() throws Exception {
		DirectionBuilder directionBuilder = mock(DirectionBuilder.class);
		PacMan pacMan = mock(PacMan.class);
		PacManKeyListenerFactory factory = new PacManKeyListenerFactory(directionBuilder);
		
		PacManKeyListener listener = factory.from(pacMan);
		
		verify(directionBuilder).buildDirections();
		
		assertSame(pacMan, listener.pacMan);
		assertSame(directionBuilder, listener.directionBuilder);
	}

}
