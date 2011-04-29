package com.pacman.entity.maze;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Graphics;

import com.pacman.entity.collision.Collidable;
import com.pacman.geometry.SquarePolygon;

public class TileTest {

	@Test
	public void shouldCreateSquareForBlock() throws Exception {
		SquarePolygon squarePolygon = mock(SquarePolygon.class);
		Tile tile = new Tile(squarePolygon);

		tile.getPolygon();

		verify(squarePolygon).getPolygon();
	}

	@Test
	public void shouldDrawItSelf() throws Exception {
		SquarePolygon squarePolygon = mock(SquarePolygon.class);
		Tile tile = new Tile(squarePolygon);
		Graphics g = mock(Graphics.class);

		tile.draw(g);

		verify(squarePolygon).draw(eq(g));
	}

	@Test
	public void shouldBeCollidingWithIntersectingShape() throws Exception {
		SquarePolygon squarePolygon = mock(SquarePolygon.class);
		Collidable tile = new Tile(squarePolygon);
		SquarePolygon collisionPolygon = mock(SquarePolygon.class);

		when(squarePolygon.isCollidingWith(collisionPolygon)).thenReturn(true);

		assertTrue(tile.isCollidingWith(collisionPolygon));
	}

	@Test
	public void shouldNotBeCollidingWithNonIntersectingShape() throws Exception {
		SquarePolygon squarePolygon = mock(SquarePolygon.class);
		Collidable tile = new Tile(squarePolygon);
		SquarePolygon collisionPolygon = mock(SquarePolygon.class);

		when(squarePolygon.isCollidingWith(collisionPolygon)).thenReturn(false);

		assertFalse(tile.isCollidingWith(collisionPolygon));
	}
}
