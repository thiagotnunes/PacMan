package com.pacman.entity.maze;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import com.pacman.entity.Collidable;

public class BlockTest {

	@Test
	public void shouldCreateSquareForBlock() throws Exception {
		Block block = new Block(0, 0);

		Polygon polygon = block.getPolygon();

		float[] expected = new float[] { 0, 0, 24, 0, 24, 24, 0, 24 };
		float[] actual = polygon.getPoints();

		assertEquals(4, polygon.getPointCount());
		for (int i = 0; i < expected.length; i++)
			assertEquals(expected[i], actual[i], 2);
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

	@Test
	public void shouldBeCollidingWithIntersectingShape() throws Exception {
		Polygon polygon = mock(Polygon.class);
		Collidable block = new Block(polygon);
		Shape shape = mock(Shape.class);

		when(polygon.intersects(shape)).thenReturn(true);

		assertTrue(block.isCollidingWith(shape));
	}

	@Test
	public void shouldNotBeCollidingWithNonIntersectingShape() throws Exception {
		Polygon polygon = mock(Polygon.class);
		Collidable block = new Block(polygon);
		Shape shape = mock(Shape.class);

		when(polygon.intersects(shape)).thenReturn(false);

		assertFalse(block.isCollidingWith(shape));
	}
}
