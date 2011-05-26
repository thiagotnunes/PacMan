package com.pacman.entity.maze.consumable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Graphics;

import com.pacman.entity.collision.Collidable;
import com.pacman.entity.maze.tile.ImageTile;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.Drawable;
import com.pacman.sound.Sonorous;
import com.pacman.sound.SoundPlayer;

public class FoodTest {

	private ImageTile image;
	private SoundPlayer sound;
	private Food food;

	@Before
	public void setUp() {
		image = mock(ImageTile.class);
		sound = mock(SoundPlayer.class);
		food = new Food(image, sound);
	}
	
	@Test
	public void shouldPlaySound() throws Exception {
		food.play();
		
		assertTrue(food instanceof Sonorous);
		verify(sound).play();
	}
	
	@Test
	public void shouldDrawImage() throws Exception {
		Graphics g = mock(Graphics.class);
		
		food.draw(g);

		assertTrue(food instanceof Drawable);
		verify(image).draw(g);
	}
	
	@Test
	public void shouldBeCollidable() throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		
		when(image.isCollidingWith(collisionPolygon)).thenReturn(true);
		
		assertTrue(food.isCollidingWith(collisionPolygon));
		verify(image).isCollidingWith(collisionPolygon);
		assertTrue(food instanceof Collidable);
	}
	
}
