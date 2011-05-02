package com.pacman.entity.maze;

import static com.pacman.game.properties.LayerProperties.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.tile.FoodTile;
import com.pacman.entity.maze.tile.NullFoodTile;
import com.pacman.entity.maze.tile.Tile;
import com.pacman.geometry.CollisionPolygon;

public class BoardTest {

	@Test
	public void shouldOnlyRenderVisibleLayersAndNotConsumedFood() throws Exception {
		Graphics g = mock(Graphics.class);
		TiledMap map = mock(TiledMap.class);
		FoodTile firstFood = mock(FoodTile.class);
		FoodTile secondFood = mock(FoodTile.class);
		ArrayList<Tile> food = new ArrayList<Tile>();
		food.add(firstFood);
		food.add(secondFood);
		Polygon firstPolygon = mock(Polygon.class);
		
		when(map.getLayerCount()).thenReturn(3);
		when(map.getLayerProperty(0, VISIBLE.property(), VISIBLE.defaultValue())).thenReturn("true");
		when(map.getLayerProperty(1, VISIBLE.property(), VISIBLE.defaultValue())).thenReturn("false");
		when(map.getLayerProperty(2, VISIBLE.property(), VISIBLE.defaultValue())).thenReturn("true");
		when(firstFood.wasConsumed()).thenReturn(false);
		when(secondFood.wasConsumed()).thenReturn(true);
		when(firstFood.getPolygon()).thenReturn(firstPolygon);
		
		Board board = new Board(map, null, food);

		board.draw(g);

		verify(map, times(4)).getLayerCount();
		verify(map, times(3)).getLayerProperty(anyInt(), eq(VISIBLE.property()), eq(VISIBLE.defaultValue()));
		verify(map).render(0, 0, 0);
		verify(map, never()).render(0, 0, 1);
		verify(map).render(0, 0, 2);
		verify(g).draw(firstPolygon);
		verify(secondFood, never()).getPolygon();
	}

	@Test
	public void shouldBeCollidingWhenAtLeastOneOfTheWallsIsColliding()
			throws Exception {
		TiledMap map = mock(TiledMap.class);
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		List<Tile> walls = new ArrayList<Tile>();
		Tile firstBlock = mock(Tile.class);
		walls.add(firstBlock);
		Tile secondBlock = firstBlock;
		when(secondBlock.isCollidingWith(collisionPolygon)).thenReturn(true);
		walls.add(secondBlock);

		Board board = new Board(map, walls, null);

		assertTrue(board.isCollidingWithWall(collisionPolygon));

		verify(firstBlock).isCollidingWith(collisionPolygon);
		verify(secondBlock).isCollidingWith(collisionPolygon);
	}

	@Test
	public void shouldNotBeCollidingWhenNoWallsAreCollidingWithPacMan() throws Exception {
		TiledMap map = mock(TiledMap.class);
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		List<Tile> walls = new ArrayList<Tile>();
		Tile firstBlock = mock(Tile.class);
		Tile secondBlock = mock(Tile.class);
		walls.add(firstBlock);
		walls.add(secondBlock);

		Board board = new Board(map, walls, null);

		assertFalse(board.isCollidingWithWall(collisionPolygon));

		verify(firstBlock).isCollidingWith(collisionPolygon);
		verify(secondBlock).isCollidingWith(collisionPolygon);
	}
	
	@Test
	public void shouldBeCollidingWithFood() throws Exception {
		TiledMap map = mock(TiledMap.class);
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		List<Tile> food = new ArrayList<Tile>();
		FoodTile firstBlock = mock(FoodTile.class);
		food.add(firstBlock);
		FoodTile secondBlock = mock(FoodTile.class);
		when(secondBlock.isCollidingWith(collisionPolygon)).thenReturn(true);
		food.add(secondBlock);

		Board board = new Board(map, null, food);

		assertNotNull(board.isCollidingWithFood(collisionPolygon));

		verify(firstBlock).isCollidingWith(collisionPolygon);
		verify(secondBlock).isCollidingWith(collisionPolygon);
	}
	
	@Test
	public void shouldNotBeCollidingWhenNoFoodIsCollidingWithPacMan() throws Exception {
		TiledMap map = mock(TiledMap.class);
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		List<Tile> food = new ArrayList<Tile>();
		FoodTile firstBlock = mock(FoodTile.class);
		FoodTile secondBlock = mock(FoodTile.class);
		food.add(firstBlock);
		food.add(secondBlock);

		Board board = new Board(map, null, food);

		assertTrue(board.isCollidingWithFood(collisionPolygon) instanceof NullFoodTile);

		verify(firstBlock).isCollidingWith(collisionPolygon);
		verify(secondBlock).isCollidingWith(collisionPolygon);
	}
}
