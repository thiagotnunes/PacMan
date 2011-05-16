package com.pacman.entity.character;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.movement.MovementBuilder;


public class PacManKeyListenerFactoryTest {
	
	@Test
	public void shouldCreateListenerForPacMan() throws Exception {
		MovementBuilder movementBuilder = mock(MovementBuilder.class);
		PacMan pacMan = mock(PacMan.class);
		PacManKeyListenerFactory factory = new PacManKeyListenerFactory(movementBuilder);
		
		PacManKeyListener listener = factory.from(pacMan);
		
		verify(movementBuilder).buildMovements();
		
		assertSame(pacMan, listener.pacMan);
		assertSame(movementBuilder, listener.movementBuilder);
	}

}
