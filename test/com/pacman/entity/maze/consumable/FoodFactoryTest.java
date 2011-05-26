package com.pacman.entity.maze.consumable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pacman.entity.maze.tile.ImageTile;
import com.pacman.sound.SoundPlayer;


public class FoodFactoryTest {

	@Test
	public void shouldCreateFoodList() throws Exception {
		SoundPlayer soundPlayer = mock(SoundPlayer.class);
		List<ImageTile> imageTiles = new ArrayList<ImageTile>();
		ImageTile firstImage = mock(ImageTile.class);
		ImageTile secondImage = mock(ImageTile.class);
		imageTiles.add(firstImage);
		imageTiles.add(secondImage);
		
		FoodFactory factory = new FoodFactory();
		
		List<Food> food = factory.from(imageTiles, soundPlayer);
		
		assertEquals(2, food.size());
		verify(food.get(0), soundPlayer, firstImage);
		verify(food.get(1), soundPlayer, secondImage);
	}

	private void verify(Food foodPiece, SoundPlayer soundPlayer, ImageTile imageTile) {
		assertSame(imageTile, foodPiece.imageTile);
		assertSame(soundPlayer, foodPiece.soundPlayer);
	}
	
}
