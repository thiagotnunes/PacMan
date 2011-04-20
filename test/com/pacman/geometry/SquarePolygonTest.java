package com.pacman.geometry;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import com.pacman.entity.Collidable;
import com.pacman.renderer.Renderable;

public class SquarePolygonTest {

	@Test
	public void shouldCreateSquarePolygon() throws Exception {
		float x = 10f;
		float y = 20f;
		float width = 25f;
		SquarePolygon square = new SquarePolygon(x, y, width);

		Polygon expectedPolygon = new Polygon(new float[] {
				x, y,
				x + width - 1, y,
				x + width - 1, y + width - 1,
				x, y + width - 1 });

		Polygon actualPolygon = square.getPolygon();

		assertEquals(expectedPolygon.getPointCount(), actualPolygon
				.getPointCount());
		assertTrue(actualPolygon.closed());
		validatePolygons(expectedPolygon, actualPolygon);
	}
	
	@Test
	public void shouldDrawItSelf() throws Exception {
		SquarePolygon squarePolygon = new SquarePolygon(0f, 0f, 10f);
		Graphics g = mock(Graphics.class);
		
		squarePolygon.draw(g);
		
		verify(g).draw(eq(squarePolygon.getPolygon()));
	}
	
	@Test
	public void shouldReturnCurrentPosition() throws Exception {
		Renderable squarePolygon = new SquarePolygon(2f, 1f, 10f);
		
		assertEquals(2, squarePolygon.getPosition().getX(), 1);
		assertEquals(1, squarePolygon.getPosition().getY(), 1);
	}
	
	@Test
	public void shouldBeCollidingWithIntersectingShape() throws Exception {
		Polygon polygon = mock(Polygon.class);
		Collidable squarePolygon = new SquarePolygon(polygon);
		Shape shape = mock(Shape.class);
		
		when(polygon.intersects(eq(shape))).thenReturn(true);
		
		assertTrue(squarePolygon.isCollidingWith(shape));
	}
	
	@Test
	public void shouldNotBeCollidingWithNonIntersectingShape() throws Exception {
		Polygon polygon = mock(Polygon.class);
		Collidable squarePolygon = new SquarePolygon(polygon);
		Shape shape = mock(Shape.class);
		
		when(polygon.intersects(eq(shape))).thenReturn(false);
		
		assertFalse(squarePolygon.isCollidingWith(shape));
	}

	@Test
	public void shouldTranslateSquarePolygon() throws Exception {
		Polygon polygon = mock(Polygon.class);
		SquarePolygon squarePolygon = new SquarePolygon(polygon);
		
		when(polygon.copy()).thenReturn(polygon);
		
		Integer x = 1;
		Integer y = 1;
		SquarePolygon translated = squarePolygon.translate(x, y);
		
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
