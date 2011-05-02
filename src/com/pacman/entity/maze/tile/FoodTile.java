package com.pacman.entity.maze.tile;

import com.pacman.geometry.CollisionPolygon;

public class FoodTile extends Tile {

	protected boolean consumed;

	public FoodTile(CollisionPolygon collisionPolygon) {
		super(collisionPolygon);
		this.consumed = false;
	}

	public boolean wasConsumed() {
		return consumed;
	}
	
	public void consume() {
		this.consumed = true;
	}
}
