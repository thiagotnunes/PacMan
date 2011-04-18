package com.pacman.entity.maze;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.BoardFactory;
import com.pacman.entity.maze.MapFactory;

public class BoardFactoryTest {

	@Test
	public void shouldCreateBoard() throws Exception {
		String path = "path";
		MapFactory mapFactory = mock(MapFactory.class);
		TiledMap map = mock(TiledMap.class);

		when(mapFactory.create(eq(path))).thenReturn(map);

		BoardFactory boardFactory = new BoardFactory(mapFactory);
		boardFactory.create(path);

		verify(mapFactory).create(eq(path));
	}
}
