package com.pacman.entity.maze.tile;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.filter.TileFilter;

public class WallTileFactoryTest {

	@Test
	public void shouldCreateTileWithFullPolygon() throws Exception {
		TiledMap map = mock(TiledMap.class);
		TileFactory<Tile> wallFactory = new WallTileFactory(mock(TileFilter.class));
		Float x = 1f;
		Float y = 2f;
		Float width = 25f;

		when(map.getTileWidth()).thenReturn(width.intValue());

		wallFactory.createTile(x.intValue(), y.intValue(), map);

		verify(map).getTileWidth();
	}

}
