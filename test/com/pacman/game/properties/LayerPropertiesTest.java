package com.pacman.game.properties;

import static org.junit.Assert.*;
import static com.pacman.game.properties.LayerProperties.*;

import org.junit.Test;


public class LayerPropertiesTest {

	@Test
	public void propertyShouldVisibleAndDefaultValueTrue() throws Exception {
		assertEquals("visible", VISIBLE.property());
		assertEquals("true", VISIBLE.defaultValue());
	}
	
}
