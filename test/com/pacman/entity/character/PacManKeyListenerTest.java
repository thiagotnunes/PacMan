package com.pacman.entity.character;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;

import org.junit.Test;
import org.newdawn.slick.KeyListener;

import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.MovementFactory;

public class PacManKeyListenerTest {

	@Test
	public void shouldBeAcceptingInput() throws Exception {
		PacManKeyListener listener = new PacManKeyListener(null, null);
		
		assertTrue(listener.isAcceptingInput());
	}
	
	
	@Test
	public void shouldCallUpdatePacManDirectionBasedOnInput() throws Exception {
		PacMan pacMan = mock(PacMan.class);
		MovementFactory movementFactory = mock(MovementFactory.class);
		Movement movement = mock(Movement.class);
		
		KeyListener keyListener = new PacManKeyListener(pacMan, movementFactory);

		when(movementFactory.from(KEY_UP)).thenReturn(movement);
		
		keyListener.keyPressed(KEY_UP, 'a');
		
		verify(movementFactory).from(KEY_UP);
		verify(pacMan).updateDirection(movement);
	}
	
	@Test
	public void shouldNotCallUpdatePacManDirectionIfKeyIsInvalid() throws Exception {
		PacMan pacMan = mock(PacMan.class);
		MovementFactory movementFactory = mock(MovementFactory.class);
		
		PacManKeyListener keyListener = new PacManKeyListener(pacMan, movementFactory);
		
		when(movementFactory.from(KEY_A)).thenReturn(null);
		
		keyListener.keyPressed(KEY_A, 'a');
		
		verify(movementFactory).from(KEY_A);
		verify(pacMan, never()).updateDirection(any(Movement.class));
	}
	
}
