package com.pacman.entity.maze.consumable;

import java.util.List;

import org.newdawn.slick.Graphics;

import com.pacman.entity.collision.CollisionDetector;
import com.pacman.geometry.CollisionPolygon;

public class Consumables {

	private final CollisionDetector detector;
	protected final List<Food> food;

	public Consumables(List<Food> food, CollisionDetector detector) {
		this.food = food;
		this.detector = detector;
	}

	public void consume(CollisionPolygon collisionPolygon) {
		Food foodPiece = detector.getCollidable(food, collisionPolygon);
		if (foodPiece != null) {
			foodPiece.play();
		}
		food.remove(foodPiece);
	}

	public void draw(Graphics g) {
		for (Food foodPiece : food) {
			foodPiece.draw(g);
		}
	}
}
