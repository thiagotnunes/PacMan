package com.pacman.entity;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;


public class BoardFactoryTest {

	@Test
	public void shouldCreateBoard() throws Exception {
		MapFactory mapFactory = mock(MapFactory.class);
		BoardFactory boardFactory = new BoardFactory(mapFactory);
		
		boardFactory.create("path");
		
		verify(mapFactory).create(any(String.class));
	}
	
}
