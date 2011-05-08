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
		Image image = mock(Image.class);
		Tile tile = new ImageTile(collisionPolygon, image);

		when(collisionPolygon.getPolygon()).thenReturn(polygon);
		when(polygon.getWidth()).thenReturn(width);
		when(polygon.getHeight()).thenReturn(height);

		tile.draw(mock(Graphics.class));

		verify(image).draw(0, 0, width, height);
	}

}
