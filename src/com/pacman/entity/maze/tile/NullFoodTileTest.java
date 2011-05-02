package com.pacman.entity.maze.tile;

import static org.junit.Assert.*;

import org.junit.Test;


public class NullFoodTileTest {

	@Test
	public void shouldAlwaysBeConsumed() throws Exception {
		FoodTile tile = new NullFoodTile();
		
		assertTrue(tile.wasConsumed());
		tile.consume();
		assertTrue(tile.wasConsumed());
	}
	
}
