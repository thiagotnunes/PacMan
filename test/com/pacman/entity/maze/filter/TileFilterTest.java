package com.pacman.entity.maze.filter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.pacman.game.properties.TileProperties.*;

import org.junit.Test;
import org.newdawn.slick.tiled.TiledMap;


public class TileFilterTest {

	@Test
	public void shouldCheckIfItIsValidWithGivenTileProperty() throws Exception {
		int id = 1;
		TileFilter tileFilter = new TileFilter(COLLIDABLE) {};
		TiledMap map = mock(TiledMap.class);
		
		when(map.getTileProperty(id, COLLIDABLE.property(), COLLIDABLE.defaultValue())).thenReturn("true");
		
		assertTrue(tileFilter.isValid(id, map));
		
		verify(map).getTileProperty(id, COLLIDABLE.property(), COLLIDABLE.defaultValue());
	}
	
}
