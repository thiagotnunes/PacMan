package com.pacman.entity;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

public class BoardTest {

	@Test
	public void shouldCreateBlocksFromTiledMap() throws Exception {
		TiledMap map = mock(TiledMap.class);
		when(map.getWidth()).thenReturn(1);
		when(map.getHeight()).thenReturn(1);
		when(map.getTileId(anyInt(), anyInt(), eq(0))).thenReturn(1);

		Board board = new Board(map);
		
		assertEquals(1, board.getBlocks().size());
	}
	
	@Test
	public void shouldRenderAllBlocksAndMap() throws Exception {
		Graphics g = mock(Graphics.class);
		
		TiledMap map = mock(TiledMap.class);
		Board board = new Board(map);
		
		board.draw(g);
		
		verify(map).render(0, 0);
	}
	
}
