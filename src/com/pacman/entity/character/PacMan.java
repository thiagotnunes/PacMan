package com.pacman.entity.character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.direction.Direction;
import com.pacman.entity.direction.NullDirection;
import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.Drawable;

public class PacMan implements Drawable {

	public static final Float SPEED = 0.5f;

	protected final Board board;
	protected CollisionPolygon currentCollisionPolygon;
	protected Direction currentDirection;
	protected Direction bufferedDirection;

	public PacMan(CollisionPolygon collisionPolygon, Direction direction,
			Board board) {
		currentCollisionPolygon = collisionPolygon;
		currentDirection = direction;
		bufferedDirection = new NullDirection();
		this.board = board;
	}

	@Override
	public void draw(Graphics g) {
		Polygon polygon = currentCollisionPolygon.getPolygon();
		Animation animation = currentDirection.getAnimation();
		animation.draw(polygon.getX(), polygon.getY(), polygon.getWidth(),
				polygon.getHeight());
	}

	public void move() {
		if (bufferedDirection.canMove(currentCollisionPolygon, SPEED, board)) {
			currentCollisionPolygon = bufferedDirection.move(
					currentCollisionPolygon, SPEED);
			currentDirection = bufferedDirection;
			bufferedDirection = new NullDirection();
		} else if (currentDirection.canMove(currentCollisionPolygon, SPEED,
				board)) {
			currentCollisionPolygon = currentDirection.move(
					currentCollisionPolygon, SPEED);
		}
	}

	public void updateDirection(Direction direction) {
		if (direction.canMove(currentCollisionPolygon, SPEED, board)) {
			currentDirection = direction;
		} else {
			bufferedDirection = direction;
		}
	}

	public void eat(Board board) {
		board.consume(currentCollisionPolygon);
	}

	public CollisionPolygon currentCollisionPolygon() {
		return currentCollisionPolygon;
	}
}
