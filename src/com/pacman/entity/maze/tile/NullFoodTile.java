package com.pacman.entity.maze.tile;


public class NullFoodTile extends FoodTile {

	public NullFoodTile() {
		super(null);
		this.consumed = true;
	}
	
}
