package com.pacman.entity.character;

import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.Point;
import com.pacman.entity.maze.Board;
import com.pacman.geometry.SquarePolygon;
import com.pacman.renderer.Renderable;

public class PacMan implements Renderable {

	public static final Float SPEED = 0.5f;

	private final Map<Direction, Animation> animationMap;
	private SquarePolygon currentCollisionPolygon;
	private Animation currentAnimation;
	private Direction currentDirection;

	public PacMan(SquarePolygon squarePolygon,
			Map<Direction, Animation> animationMap, Direction currentDirection) {
		this.currentCollisionPolygon = squarePolygon;
		this.animationMap = animationMap;
		this.currentDirection = currentDirection;
		updateAnimationWith(currentDirection);
	}

	public void draw(Graphics g) {
		Polygon polygon = currentCollisionPolygon.getPolygon();
		currentAnimation.draw(0, 0, polygon.getWidth(), polygon.getHeight());
		// g.draw(polygon);
		// g.drawString(currentCollisionPolygon.toString(),
		// polygon.getCenterX(),
		// polygon.getCenterY());
	}

	public void update(GameContainer gc, int delta, Board board) {
		Direction nextDirection = currentDirection.next(gc.getInput());

		if (!move(board, nextDirection)) {
			move(board, currentDirection);
		}
	}

	private boolean move(Board board, Direction direction) {
		SquarePolygon movedCollisionPolygon = direction.move(
				currentCollisionPolygon, SPEED);

		if (board.isCollidingWith(movedCollisionPolygon.getPolygon())) {
			return false;
		}

		updateCollisionPolygon(direction, movedCollisionPolygon);

		return true;
	}

	private void updateCollisionPolygon(Direction direction,
			SquarePolygon collisionPolygon) {
		currentCollisionPolygon = collisionPolygon;
		currentDirection = direction;
		updateAnimationWith(direction);
	}

	private void updateAnimationWith(Direction currentDirection) {
		currentAnimation = animationMap.get(currentDirection);
	}

	public Animation currentAnimation() {
		return currentAnimation;
	}

	public Direction currentDirection() {
		return currentDirection;
	}

	public SquarePolygon currentCollisionPolygon() {
		return currentCollisionPolygon;
	}

	@Override
	public Point getPosition() {
		return currentCollisionPolygon.getPosition();
	}
}
