package com.pacman.entity.maze.tile;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.tile.TileFactory;

public class FoodTileFactoryTest {

	@Test
	public void shouldCreateFoodTile() throws Exception {
		TiledMap map = mock(TiledMap.class);

		FoodTileFactory factory = new FoodTileFactory(null);

		assertTrue(factory instanceof TileFactory);
		
		when(map.getTileWidth()).thenReturn(25);

		factory.createTile(1, 1, map, mock(Image.class));

		verify(map).getTileWidth();
	}
}
