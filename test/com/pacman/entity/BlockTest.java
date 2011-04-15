package com.pacman.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

public class BlockTest {

	@Test
	public void shouldCreateSquareForBlock() throws Exception {
		Block block = new Block(0, 0);

		Polygon polygon = block.getPolygon();

		float[] expected = new float[] { 0, 0, 24, 0, 24, 24, 0, 24 };
		float[] actual = polygon.getPoints();

		assertEquals(4, polygon.getPointCount());
		for (int i = 0; i < expected.length; i++)
			assertEquals(expected[i], actual[i], 1);
	}
	
	@Test
	public void shouldDrawItSelf() throws Exception {
		Block block = new Block(0, 0);
		Graphics g = mock(Graphics.class);
		
		block.draw(g);
		Polygon polygon = block.getPolygon();
		
		verify(g).draw(eq(polygon));
		assertEquals(0, block.getPosition().getX(), 1);
		assertEquals(0, block.getPosition().getY(), 1);
	}

}
