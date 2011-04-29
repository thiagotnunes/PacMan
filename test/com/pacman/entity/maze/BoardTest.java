package com.pacman.entity.maze;

import static com.pacman.game.properties.LayerProperties.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.collision.Collidable;
import com.pacman.geometry.SquarePolygon;

public class BoardTest {

	@Test
	public void shouldOnlyRenderVisibleLayers() throws Exception {
		Graphics g = mock(Graphics.class);
		TiledMap map = mock(TiledMap.class);
		
		when(map.getLayerCount()).thenReturn(3);
		when(map.getLayerProperty(0, VISIBLE.property(), VISIBLE.defaultValue())).thenReturn("true");
		when(map.getLayerProperty(1, VISIBLE.property(), VISIBLE.defaultValue())).thenReturn("false");
		when(map.getLayerProperty(2, VISIBLE.property(), VISIBLE.defaultValue())).thenReturn("true");
		
		Board board = new Board(map, null);

		board.draw(g);

		verify(map, times(4)).getLayerCount();
		verify(map, times(3)).getLayerProperty(anyInt(), eq(VISIBLE.property()), eq(VISIBLE.defaultValue()));
		verify(map).render(0, 0, 0);
		verify(map, never()).render(0, 0, 1);
		verify(map).render(0, 0, 2);
	}

	@Test
	public void shouldBeCollidingWhenAtLeastOneOfTheBlocksIsColliding()
			throws Exception {
		TiledMap map = mock(TiledMap.class);
		SquarePolygon collisionPolygon = mock(SquarePolygon.class);
		List<Tile> blocks = new ArrayList<Tile>();
		Tile firstBlock = mock(Tile.class);
		blocks.add(firstBlock);
		Tile secondBlock = firstBlock;
		when(secondBlock.isCollidingWith(collisionPolygon)).thenReturn(true);
		blocks.add(secondBlock);

		Collidable board = new Board(map, blocks);

		assertTrue(board.isCollidingWith(collisionPolygon));

		verify(firstBlock).isCollidingWith(collisionPolygon);
		verify(secondBlock).isCollidingWith(collisionPolygon);
	}

	@Test
	public void shouldNotBeCollidingWhenNoBlocksAreColliding() throws Exception {
		TiledMap map = mock(TiledMap.class);
		SquarePolygon collisionPolygon = mock(SquarePolygon.class);
		List<Tile> blocks = new ArrayList<Tile>();
		Tile firstBlock = mock(Tile.class);
		Tile secondBlock = mock(Tile.class);
		blocks.add(firstBlock);
		blocks.add(secondBlock);

		Collidable board = new Board(map, blocks);

		assertFalse(board.isCollidingWith(collisionPolygon));

		verify(firstBlock).isCollidingWith(collisionPolygon);
		verify(secondBlock).isCollidingWith(collisionPolygon);
	}
}
