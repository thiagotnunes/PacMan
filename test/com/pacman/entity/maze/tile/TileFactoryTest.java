package com.pacman.entity.maze.tile;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.filter.TileFilter;
import com.pacman.entity.maze.tile.Tile;
import com.pacman.entity.maze.tile.TileFactory;

public class TileFactoryTest {

	@Test
	public void shouldCreateTilesFromTiledMap() throws Exception {
		TileFactory<Tile> factory = mock(TileFactory.class);
		TileFilter filter = mock(TileFilter.class);
		TiledMap map = mock(TiledMap.class);
		Tile tile = mock(Tile.class);
		Integer layer = 0;
		factory.filter = filter;

		when(map.getTileId(anyInt(), anyInt(), eq(layer))).thenReturn(3);
		when(filter.isValid(anyInt(), eq(map))).thenReturn(true);
		when(factory.createTile(anyInt(), anyInt(), eq(map))).thenReturn(tile);
		when(factory.from(any(TiledMap.class), anyInt())).thenCallRealMethod();
		when(map.getWidth()).thenReturn(1);
		when(map.getHeight()).thenReturn(2);

		List<Tile> tiles = factory.from(map, layer);

		verify(map).getWidth();
		verify(map).getHeight();
		verify(map, times(2)).getTileId(anyInt(), anyInt(), eq(layer));
		verify(filter, times(2)).isValid(anyInt(), eq(map));

		assertEquals(2, tiles.size());
	}
}
