package com.pacman.entity.maze.filter;

import static com.pacman.game.properties.TileProperties.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class CollidableTileFilterTest {

	@Test
	public void shouldHaveCollidableProperty() throws Exception {
		CollidableTileFilter filter = new CollidableTileFilter();
		
		assertEquals(COLLIDABLE, filter.property);
	}
	
}
