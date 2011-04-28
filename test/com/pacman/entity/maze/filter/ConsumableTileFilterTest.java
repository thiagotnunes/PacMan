package com.pacman.entity.maze.filter;

import static com.pacman.game.properties.TileProperties.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class ConsumableTileFilterTest {

	@Test
	public void shouldConsumableProperty() throws Exception {
		ConsumableTileFilter filter = new ConsumableTileFilter();
		
		assertEquals(CONSUMABLE, filter.property);
	}
	
}
