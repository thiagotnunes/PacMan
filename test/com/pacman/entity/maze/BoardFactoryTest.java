package com.pacman.entity.maze;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.tiled.TiledMap;

public class BoardFactoryTest {

	@Test
	public void shouldCreateBoard() throws Exception {
		String path = "path";
		MapFactory mapFactory = mock(MapFactory.class);
		BlockFactory blockFactory = mock(BlockFactory.class);
		TiledMap map = mock(TiledMap.class);

		when(mapFactory.from(eq(path))).thenReturn(map);

		BoardFactory boardFactory = new BoardFactory(mapFactory, blockFactory);
		boardFactory.from(path);

		verify(mapFactory).from(eq(path));
		verify(blockFactory).from(eq(map));
	}
}
