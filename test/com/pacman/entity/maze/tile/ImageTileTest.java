package com.pacman.entity.maze.tile;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;

import com.pacman.geometry.CollisionPolygon;

public class ImageTileTest {

	@Test
	public void shouldDrawImage() throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		Polygon polygon = mock(Polygon.class);
		float width = 6.0f;
		float height = 7.0f;
		float x = 11f;
		float y = 12f;
		Image image = mock(Image.class);
		Tile tile = new ImageTile(collisionPolygon, image);

		when(collisionPolygon.getPolygon()).thenReturn(polygon);
		when(polygon.getWidth()).thenReturn(width);
		when(polygon.getHeight()).thenReturn(height);
		when(polygon.getX()).thenReturn(x);
		when(polygon.getY()).thenReturn(y);

		tile.draw(mock(Graphics.class));

		verify(image).draw(x, y, width, height);
	}

}
