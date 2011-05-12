package com.pacman.entity.maze.tile;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.graphics.ImageFactory;

public class FoodTileFactoryTest {

	@Test
	public void shouldCreateFoodTile() throws Exception {
		TiledMap map = mock(TiledMap.class);
		ImageFactory imageFactory = mock(ImageFactory.class);

		TileFactory factory = new FoodTileFactory(null,
				imageFactory, FoodTileFactory.FOOD_PATH);

		when(map.getTileWidth()).thenReturn(25);

		factory.createTile(1, 1, map);

		verify(map).getTileWidth();
		verify(imageFactory).from(FoodTileFactory.FOOD_PATH);
	}
}
