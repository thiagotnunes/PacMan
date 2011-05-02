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
		Integer x = 1;
		Integer y = 2;
		Integer width = 25;
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);

		when(map.getTileWidth()).thenReturn(width);
		when(factory.from((float) x, (float) y, (float) width)).thenReturn(
				collisionPolygon);

		wallFactory.createTile(x, y, map);

		verify(map).getTileWidth();
		verify(factory).from((float) x, (float) y, (float) width);
	}

}
