package com.pacman.entity.maze;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.filter.TileFilter;

public class TileFactoryTest {

	@Test
	public void shouldCreateTilesFromTiledMap() throws Exception {
		TileFactory factory = new TileFactory();
		TiledMap map = mock(TiledMap.class);
		TileFilter filter = mock(TileFilter.class);
		Integer firstTileId = 1;
		Integer secondTileId = 2;
		Integer layer = 0;

		when(map.getTileWidth()).thenReturn(10);
		when(map.getWidth()).thenReturn(1);
		when(map.getHeight()).thenReturn(2);
		when(map.getTileId(0, 0, layer)).thenReturn(firstTileId);
		when(map.getTileId(0, 1, layer)).thenReturn(secondTileId);
		when(filter.isValid(firstTileId, map)).thenReturn(true);
		when(filter.isValid(secondTileId, map)).thenReturn(false);
		
		List<Tile> tiles = factory.from(map, layer, filter);

		verify(map).getTileWidth();
		verify(map).getWidth();
		verify(map).getHeight();
		verify(map, never()).getTileHeight();
		verify(filter).isValid(firstTileId, map);
		verify(filter).isValid(secondTileId, map);

		assertEquals(1, tiles.size());
	}

}
