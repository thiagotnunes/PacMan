package com.pacman.entity.collision;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.geom.Polygon;

import com.pacman.geometry.CollisionPolygon;

public class FullPolygonFactoryTest {

	@Test
	public void shouldCreatePolygonWithFullWidth() throws Exception {
		PolygonFactory factory = new FullPolygonFactory();

		Float x = 5f;
		Float y = 2f;
		Float width = 3f;
		CollisionPolygon collisionPolygon = factory.from(x, y, width);
		Polygon polygon = collisionPolygon.getPolygon();

		assertEquals(x, polygon.getMinX(), 10);
		assertEquals(y, polygon.getMinY(), 6);
		assertEquals((x * width) + width, polygon.getMaxX(), 2);
		assertEquals((y * width) + width, polygon.getMaxY(), 2);
	}

}
