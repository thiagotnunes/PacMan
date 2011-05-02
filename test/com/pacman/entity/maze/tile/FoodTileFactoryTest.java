package com.pacman.entity.maze.tile;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.collision.PolygonFactory;
import com.pacman.geometry.CollisionPolygon;


public class FoodTileFactoryTest {

	@Test
	public void shouldCreateFoodTile() throws Exception {
		PolygonFactory polygonFactory = mock(PolygonFactory.class);
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		TiledMap map = mock(TiledMap.class);
		
		TileFactory factory = new FoodTileFactory(null, polygonFactory);
		
		when(map.getTileWidth()).thenReturn(25);
		when(polygonFactory.from(35f, 35f, 6f)).thenReturn(collisionPolygon);
		
		Tile foodTile = factory.createTile(1, 1, map);
		
		assertTrue(foodTile instanceof FoodTile);
		
		verify(map).getTileWidth();
		verify(polygonFactory).from(35f, 35f, 6f);
	}
	
}
