package com.pacman.entity.maze.tile;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.collision.Collidable;
import com.pacman.geometry.CollisionPolygon;

public class TileTest {

	@Test
	public void shouldCreateSquareForBlock() throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		Tile tile = new Tile(collisionPolygon);

		tile.getPolygon();

		verify(collisionPolygon).getPolygon();
	}

	@Test
	public void shouldDrawItSelf() throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		Image image = mock(Image.class);
		Tile tile = new Tile(collisionPolygon, image);
		Graphics g = mock(Graphics.class);
		float width = 3f;
		float height = 5f;
		Polygon polygon = mock(Polygon.class);
		
		when(collisionPolygon.getPolygon()).thenReturn(polygon);
		when(polygon.getWidth()).thenReturn(width);
		when(polygon.getHeight()).thenReturn(height);

		tile.draw(g);

		verify(collisionPolygon).draw(g);
		verify(image).draw(0, 0, width , height);
	}

	@Test
	public void shouldBeCollidingWithIntersectingShape() throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		Collidable tile = new Tile(collisionPolygon);
		CollisionPolygon otherPolygon = mock(CollisionPolygon.class);

		when(collisionPolygon.isCollidingWith(otherPolygon)).thenReturn(true);

		assertTrue(tile.isCollidingWith(otherPolygon));
	}

	@Test
	public void shouldNotBeCollidingWithNonIntersectingShape() throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		Collidable tile = new Tile(collisionPolygon);
		CollisionPolygon otherPolygon = mock(CollisionPolygon.class);

		when(collisionPolygon.isCollidingWith(otherPolygon)).thenReturn(false);

		assertFalse(tile.isCollidingWith(otherPolygon));
	}
}
