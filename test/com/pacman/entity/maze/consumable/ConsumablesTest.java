package com.pacman.entity.maze.consumable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Graphics;

import com.pacman.entity.collision.CollisionDetector;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.Drawable;


public class ConsumablesTest {

	private List<Food> food;
	private Food foodPiece;
	private CollisionDetector detector;
	private Consumables consumables;

	@Before
	public void setUp() {
		food = new ArrayList<Food>();
		foodPiece = mock(Food.class);
		food.add(foodPiece);
		
		detector = mock(CollisionDetector.class);

		consumables = new Consumables(food, detector);
	}
	
	@Test
	public void shouldConsumeFood() throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		
		when(detector.getCollidable(food, collisionPolygon)).thenReturn(foodPiece);
		
		consumables.consume(collisionPolygon);
		
		verify(foodPiece).play();
		
		assertTrue(consumables.food.isEmpty());
	}
	
	@Test
	public void shouldRenderFood() throws Exception {
		Graphics g = mock(Graphics.class);
		
		consumables.draw(g);
		
		verify(foodPiece).draw(g);
		
		assertTrue(consumables instanceof Drawable);
	}
	
}
