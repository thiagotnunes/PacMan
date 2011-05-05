package com.pacman.entity.character;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;

import org.junit.Test;
import org.newdawn.slick.KeyListener;

import com.pacman.entity.direction.Direction;
import com.pacman.entity.direction.DirectionBuilder;

public class PacManKeyListenerTest {

	@Test
	public void shouldBeAcceptingInput() throws Exception {
		PacManKeyListener listener = new PacManKeyListener(null, null);
		
		assertTrue(listener.isAcceptingInput());
	}
	
	
	@Test
	public void shouldCallUpdatePacManDirectionBasedOnInput() throws Exception {
		PacMan pacMan = mock(PacMan.class);
		DirectionBuilder directionBuilder = mock(DirectionBuilder.class);
		Direction direction = mock(Direction.class);
		
		KeyListener keyListener = new PacManKeyListener(pacMan, directionBuilder);

		when(directionBuilder.from(KEY_UP)).thenReturn(direction);
		
		keyListener.keyPressed(KEY_UP, 'a');
		
		verify(directionBuilder).from(KEY_UP);
		verify(pacMan).updateDirection(direction);
	}
	
	@Test
	public void shouldNotCallUpdatePacManDirectionIfKeyIsInvalid() throws Exception {
		PacMan pacMan = mock(PacMan.class);
		DirectionBuilder directionBuilder = mock(DirectionBuilder.class);
		
		PacManKeyListener keyListener = new PacManKeyListener(pacMan, directionBuilder);
		
		when(directionBuilder.from(KEY_A)).thenReturn(null);
		
		keyListener.keyPressed(KEY_A, 'a');
		
		verify(directionBuilder).from(KEY_A);
		verify(pacMan, never()).updateDirection(any(Direction.class));
	}
	
}
