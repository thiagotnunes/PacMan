package com.pacman.entity.maze;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import com.pacman.entity.Collidable;
import com.pacman.geometry.SquarePolygon;

public class BlockTest {

	@Test
	public void shouldCreateSquareForBlock() throws Exception {
		SquarePolygon squarePolygon = mock(SquarePolygon.class);
		Block block = new Block(squarePolygon);

		block.getPolygon();

		verify(squarePolygon).getPolygon();
	}

	@Test
	public void shouldDrawItSelf() throws Exception {
		SquarePolygon squarePolygon = mock(SquarePolygon.class);
		Block block = new Block(squarePolygon);
		Graphics g = mock(Graphics.class);
		
		block.draw(g);

		verify(squarePolygon).draw(eq(g));
	}

	@Test
	public void shouldBeCollidingWithIntersectingShape() throws Exception {
		SquarePolygon squarePolygon = mock(SquarePolygon.class);
		Collidable block = new Block(squarePolygon);
		Shape shape = mock(Shape.class);

		when(squarePolygon.isCollidingWith(eq(shape))).thenReturn(true);

		assertTrue(block.isCollidingWith(shape));
	}

	@Test
	public void shouldNotBeCollidingWithNonIntersectingShape() throws Exception {
		SquarePolygon squarePolygon = mock(SquarePolygon.class);
		Collidable block = new Block(squarePolygon);
		Shape shape = mock(Shape.class);

		when(squarePolygon.isCollidingWith(eq(shape))).thenReturn(false);

		assertFalse(block.isCollidingWith(shape));
	}
}
