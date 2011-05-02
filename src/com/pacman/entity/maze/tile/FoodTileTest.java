package com.pacman.entity.maze.tile;

import static org.junit.Assert.*;

import org.junit.Test;


public class FoodTileTest {

	@Test
	public void shouldInitializeAsNotConsumed() throws Exception {
		assertFalse(new FoodTile(null).wasConsumed());
	}
	
	@Test
	public void shouldConsumeFoodTile() throws Exception {
		FoodTile foodTile = new FoodTile(null);
		foodTile.consume();
		
		assertTrue(foodTile.wasConsumed());
	}
	
}
