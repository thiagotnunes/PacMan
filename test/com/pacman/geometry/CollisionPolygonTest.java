package com.pacman.geometry;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.collision.Collidable;

public class CollisionPolygonTest {

	@Test
	public void shouldCreateSquarePolygon() throws Exception {
		float x = 10f;
		float y = 20f;
		float width = 25f;
		CollisionPolygon square = new CollisionPolygon(x, y, width);

		Polygon expectedPolygon = new Polygon(new float[] { x, y,
				x + width - 1, y, x + width - 1, y + width - 1, x,
				y + width - 1 });

		Polygon actualPolygon = square.getPolygon();

		assertEquals(expectedPolygon.getPointCount(), actualPolygon
				.getPointCount());
		assertTrue(actualPolygon.closed());
		validatePolygons(expectedPolygon, actualPolygon);
	}

	@Test
	public void shouldDrawItSelf() throws Exception {
		CollisionPolygon collisionPolygon = new CollisionPolygon(0f, 0f, 10f);
		Graphics g = mock(Graphics.class);

		collisionPolygon.draw(g);

		verify(g).draw(eq(collisionPolygon.getPolygon()));
	}

	@Test
	public void shouldBeCollidingWithIntersectingShape() throws Exception {
		Polygon polygon = mock(Polygon.class);
		Collidable squarePolygon = new CollisionPolygon(polygon);
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);

		when(collisionPolygon.getPolygon()).thenReturn(polygon);
		when(polygon.intersects(polygon)).thenReturn(true);

		assertTrue(squarePolygon.isCollidingWith(collisionPolygon));
	}

	@Test
	public void shouldNotBeCollidingWithNonIntersectingShape() throws Exception {
		Polygon polygon = mock(Polygon.class);
		Collidable squarePolygon = new CollisionPolygon(polygon);
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);

		when(collisionPolygon.getPolygon()).thenReturn(polygon);
		when(polygon.intersects(polygon)).thenReturn(false);

		assertFalse(squarePolygon.isCollidingWith(collisionPolygon));
	}

	@Test
	public void shouldTranslateSquarePolygon() throws Exception {
		Polygon polygon = mock(Polygon.class);
		CollisionPolygon collisionPolygon = new CollisionPolygon(polygon);

		when(polygon.copy()).thenReturn(polygon);

		Integer x = 1;
		Integer y = 1;
		CollisionPolygon translated = collisionPolygon.translate(x, y);

		assertEquals(x.doubleValue(), translated.getPolygon().getX(), 2);
		assertEquals(y.doubleValue(), translated.getPolygon().getY(), 2);

		verify(polygon).copy();
		verify(polygon, times(2)).getX();
		verify(polygon, times(2)).getY();
		verify(polygon).setLocation(x, y);
	}

	private void validatePolygons(Polygon expectedPolygon, Polygon actualPolygon) {
		for (int i = 0; i < expectedPolygon.getPointCount(); i++) {
			float[] expectedPoint = expectedPolygon.getPoint(i);
			float[] actualPoint = actualPolygon.getPoint(i);
			for (int j = 0; j < expectedPoint.length; j++) {
				assertEquals(expectedPoint[j], actualPoint[j], 2);
			}
		}
	}
}
