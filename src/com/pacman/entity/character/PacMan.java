package com.pacman.entity.character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.maze.Board;
import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.strategy.MovementStrategy;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.Drawable;

public class PacMan implements Drawable {

	public static final Float SPEED = 0.25f;

	protected final Board board;
	protected CollisionPolygon currentCollisionPolygon;
	protected Movement currentMovement;

	private final MovementStrategy movementStrategy;

	public PacMan(CollisionPolygon collisionPolygon, MovementStrategy movementStrategy,
			Board board) {
		currentCollisionPolygon = collisionPolygon;
		currentMovement = movementStrategy.currentMovement();
		this.movementStrategy = movementStrategy;
		this.board = board;
	}

	@Override
	public void draw(Graphics g) {
		Polygon polygon = currentCollisionPolygon.getPolygon();
		Animation animation = currentMovement.getAnimation();
		animation.draw(polygon.getX(), polygon.getY(), polygon.getWidth(),
				polygon.getHeight());
	}

	public void move() {
		currentMovement = movementStrategy.availableMovement(currentCollisionPolygon, SPEED);
		currentCollisionPolygon = currentMovement.move(currentCollisionPolygon, SPEED);
	}

	public void updateDirection(Movement movement) {
		movementStrategy.update(movement, currentCollisionPolygon, SPEED);
	}

	public void eat(Board board) {
		board.consume(currentCollisionPolygon);
	}

	public CollisionPolygon currentCollisionPolygon() {
		return currentCollisionPolygon;
	}
}
