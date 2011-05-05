package com.pacman.entity.character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.direction.Direction;
import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.geometry.Point;
import com.pacman.renderer.Renderable;

public class PacMan implements Renderable {

	public static final Float SPEED = 0.5f;

	protected final Board board;
	protected CollisionPolygon currentCollisionPolygon;
	protected Direction currentDirection;
	protected Direction bufferedDirection;

	public PacMan(CollisionPolygon collisionPolygon, Direction direction,
			Board board) {
		currentCollisionPolygon = collisionPolygon;
		currentDirection = direction;
		bufferedDirection = direction;
		this.board = board;
	}

	public void draw(Graphics g) {
		Polygon polygon = currentCollisionPolygon.getPolygon();
		Animation animation = currentDirection.getAnimation();
		animation.draw(0, 0, polygon.getWidth(), polygon.getHeight());
		// g.draw(polygon);
		// g.drawString(currentCollisionPolygon.toString(),
		// polygon.getCenterX(),
		// polygon.getCenterY());
	}

	public void move() {
		if (currentDirection.canMove(currentCollisionPolygon, SPEED, board)) {
			currentCollisionPolygon = currentDirection.move(
					currentCollisionPolygon, SPEED);
		}
	}

	public void eat(Board board) {
		board.consume(currentCollisionPolygon);
	}

	public void updateDirection(Direction direction) {
		if (direction.canMove(currentCollisionPolygon, SPEED, board)) {
			currentDirection = direction;
		} else {
			bufferedDirection = direction;
		}
	}

	public CollisionPolygon currentCollisionPolygon() {
		return currentCollisionPolygon;
	}

	@Override
	public Point getPosition() {
		return currentCollisionPolygon.getPosition();
	}

}
