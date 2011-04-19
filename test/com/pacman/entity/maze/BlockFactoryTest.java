package com.pacman.entity.maze;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.newdawn.slick.tiled.TiledMap;

public class BlockFactoryTest {

	@Test
	public void shouldCreateBlocksFromTiledMap() throws Exception {
		BlockFactory factory = new BlockFactory();
		TiledMap map = mock(TiledMap.class);

		when(map.getTileWidth()).thenReturn(10);
		when(map.getWidth()).thenReturn(1);
		when(map.getHeight()).thenReturn(1);
		when(map.getTileId(0, 0, 0)).thenReturn(1);

		List<Block> blocks = factory.from(map);

		verify(map).getTileWidth();
		verify(map).getWidth();
		verify(map).getHeight();
		verify(map, never()).getTileHeight();

		assertEquals(1, blocks.size());
	}

}
