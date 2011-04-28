package com.pacman.game.properties;

import static org.junit.Assert.*;
import static com.pacman.game.properties.TileProperties.*;

import org.junit.Test;


public class TilePropertiesTest {

	@Test
	public void propertyShouldBeCollidableAndDefaultValueFalse() throws Exception {
		assertEquals("collidable", COLLIDABLE.property());
		assertEquals("false", COLLIDABLE.defaultValue());
	}
	
	@Test
	public void propertyShouldBeConsumableAndDefaultValueFalse() throws Exception {
		assertEquals("consumable", CONSUMABLE.property());
		assertEquals("false", CONSUMABLE.defaultValue());
	}
	
}
