package com.pacman.entity.maze;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.Collidable;

public class BoardTest {

	@Test
	public void shouldRenderMap() throws Exception {
		Graphics g = mock(Graphics.class);

		TiledMap map = mock(TiledMap.class);
		Board board = new Board(map, null);

		board.draw(g);

		verify(map).render(0, 0);
	}

	@Test
	public void shouldBeCollidingWhenAtLeastOneOfTheBlocksIsColliding()
			throws Exception {
		TiledMap map = mock(TiledMap.class);
		Shape shape = mock(Shape.class);
		List<Block> blocks = new ArrayList<Block>();
		Block firstBlock = mock(Block.class);
		blocks.add(firstBlock);
		Block secondBlock = firstBlock;
		when(secondBlock.isCollidingWith(shape)).thenReturn(true);
		blocks.add(secondBlock);

		Collidable board = new Board(map, blocks);

		assertTrue(board.isCollidingWith(shape));

		verify(firstBlock).isCollidingWith(eq(shape));
		verify(secondBlock).isCollidingWith(eq(shape));
	}

	@Test
	public void shouldNotBeCollidingWhenNoBlocksAreColliding() throws Exception {
		TiledMap map = mock(TiledMap.class);
		Shape shape = mock(Shape.class);
		List<Block> blocks = new ArrayList<Block>();
		Block firstBlock = mock(Block.class);
		Block secondBlock = mock(Block.class);
		blocks.add(firstBlock);
		blocks.add(secondBlock);

		Collidable board = new Board(map, blocks);

		assertFalse(board.isCollidingWith(shape));

		verify(firstBlock).isCollidingWith(eq(shape));
		verify(secondBlock).isCollidingWith(eq(shape));
	}
}
