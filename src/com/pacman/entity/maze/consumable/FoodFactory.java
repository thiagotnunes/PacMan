package com.pacman.entity.maze.consumable;

import java.util.ArrayList;
import java.util.List;

import com.pacman.entity.maze.tile.ImageTile;
import com.pacman.sound.SoundPlayer;

public class FoodFactory {

	public List<Food> from(List<ImageTile> imageTiles, SoundPlayer soundPlayer) {
		List<Food> food = new ArrayList<Food>();
		for (ImageTile imageTile : imageTiles) {
			food.add(new Food(imageTile, soundPlayer));
		}
		return food;
	}

}
