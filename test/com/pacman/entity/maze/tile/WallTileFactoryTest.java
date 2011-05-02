package com.pacman.entity.maze.tile;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.collision.PolygonFactory;
import com.pacman.entity.maze.filter.TileFilter;
import com.pacman.geometry.CollisionPolygon;

public class WallTileFactoryTest {

	@Test
	public void shouldCreateTileWithFullPolygon() throws Exception {
		PolygonFactory factory = mock(PolygonFactory.class);
		TiledMap map = mock(TiledMap.class);
		TileFactory wallFactory = new WallTileFactory(mock(TileFilter.class),
				factory);
		Float x = 1f;
		Float y = 2f;
		Float width = 25f;
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);

		when(map.getTileWidth()).thenReturn(width.intValue());
		when(factory.from(x * width, y * width, width)).thenReturn(collisionPolygon);

		wallFactory.createTile(x.intValue(), y.intValue(), map);

		verify(map).getTileWidth();
		verify(factory).from(x * width, y * width, width);
	}

}
