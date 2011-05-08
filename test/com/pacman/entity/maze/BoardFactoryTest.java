package com.pacman.entity.maze;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.tile.ImageTileFactory;
import com.pacman.entity.maze.tile.WallTileFactory;

public class BoardFactoryTest {

	@Test
	public void shouldCreateBoard() throws Exception {
		String path = "path";
		MapFactory mapFactory = mock(MapFactory.class);
		WallTileFactory wallFactory = mock(WallTileFactory.class);
		ImageTileFactory foodFactory = mock(ImageTileFactory.class);
		TiledMap map = mock(TiledMap.class);

		when(mapFactory.from(eq(path))).thenReturn(map);

		BoardFactory boardFactory = new BoardFactory(mapFactory, wallFactory, foodFactory);
		boardFactory.from(path);

		verify(mapFactory).from(path);
		verify(wallFactory).from(map, 0);
		verify(foodFactory).from(map, 2);
	}
}
