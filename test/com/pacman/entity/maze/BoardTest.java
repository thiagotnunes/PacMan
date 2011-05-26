package com.pacman.entity.maze;

import static com.pacman.game.properties.LayerProperties.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.consumable.Consumables;
import com.pacman.entity.maze.tile.Tile;
import com.pacman.geometry.CollisionPolygon;

public class BoardTest {

	private Board board;
	private TiledMap map;
	private List<Tile> walls;
	private Tile firstWall;
	private Tile secondWall;
	private Consumables consumables;

	@Before
	public void setUp() {
		map = mock(TiledMap.class);
		
		walls = new ArrayList<Tile>();
		firstWall = mock(Tile.class);
		walls.add(firstWall);
		secondWall = mock(Tile.class);
		walls.add(secondWall);
		
		consumables = mock(Consumables.class);
		
		board = new Board(map, walls, consumables);
	}

	@Test
	public void shouldOnlyRenderVisibleLayersAndConsumables() throws Exception {
		Graphics g = mock(Graphics.class);
		
		when(map.getLayerCount()).thenReturn(3);
		when(map.getLayerProperty(0, VISIBLE.property(), VISIBLE.defaultValue())).thenReturn("true");
		when(map.getLayerProperty(1, VISIBLE.property(), VISIBLE.defaultValue())).thenReturn("false");
		when(map.getLayerProperty(2, VISIBLE.property(), VISIBLE.defaultValue())).thenReturn("true");
		
		board.draw(g);

		verify(map, times(4)).getLayerCount();
		verify(map, times(3)).getLayerProperty(anyInt(), eq(VISIBLE.property()), eq(VISIBLE.defaultValue()));
		verify(map).render(0, 0, 0);
		verify(map, never()).render(0, 0, 1);
		verify(map).render(0, 0, 2);
		verify(consumables).draw(g);
	}

	@Test
	public void shouldBeCollidingWhenAtLeastOneOfTheWallsIsColliding()
			throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		when(secondWall.isCollidingWith(collisionPolygon)).thenReturn(true);

		assertTrue(board.isCollidingWithWall(collisionPolygon));

		verify(firstWall).isCollidingWith(collisionPolygon);
		verify(secondWall).isCollidingWith(collisionPolygon);
	}

	@Test
	public void shouldNotBeCollidingWhenNoWallsAreCollidingWithPacMan() throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);

		assertFalse(board.isCollidingWithWall(collisionPolygon));

		verify(firstWall).isCollidingWith(collisionPolygon);
		verify(secondWall).isCollidingWith(collisionPolygon);
	}
	
	@Test
	public void shouldConsumeConsumables() throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		
		board.consume(collisionPolygon);
		
		verify(consumables).consume(collisionPolygon);
	}
	
}