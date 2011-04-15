package com.pacman.entity;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.lwjgl.util.Dimension;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.renderer.Renderer;

public class BoardTest {

	@Test
	public void shouldCreateBlocksFromTiledMap() throws Exception {
		TiledMap map = mock(TiledMap.class);
		when(map.getWidth()).thenReturn(1);
		when(map.getHeight()).thenReturn(1);
		when(map.getTileId(anyInt(), anyInt(), eq(0))).thenReturn(1);

		Board board = new Board(map, null, null);
		
		assertEquals(1, board.getBlocks().size());
	}
	
	@Test
	public void shouldHaveGivenDimension() throws Exception {
		Dimension dimension = new Dimension(100, 100);
		Board board = new Board(mock(TiledMap.class), dimension, null);
		
		assertSame(dimension, board.getDimension());
	}
	
	@Test
	public void shouldRenderAllBlocks() throws Exception {
		Renderer renderer = mock(Renderer.class);
		Graphics g = mock(Graphics.class);
		List<Block> blocks = new ArrayList<Block>();
		Block block1 = mock(Block.class);
		Block block2 = mock(Block.class);
		blocks.add(block1);
		blocks.add(block2);
		
		Board board = new Board(mock(TiledMap.class), new Dimension(100, 100), renderer, blocks);
		
		board.draw(g);
		
		verify(renderer).render(eq(block1), eq(g));
		verify(renderer).render(eq(block2), eq(g));
	}
	
}
