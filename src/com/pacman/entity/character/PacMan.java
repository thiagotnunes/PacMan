package com.pacman.entity.character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.Point;
import com.pacman.entity.direction.Direction;
import com.pacman.entity.direction.DirectionBuilder;
import com.pacman.entity.direction.NullDirection;
import com.pacman.entity.maze.Board;
import com.pacman.geometry.SquarePolygon;
import com.pacman.renderer.Renderable;

public class PacMan implements Renderable {

	public static final Float SPEED = 1f;

	private final DirectionBuilder directionBuilder;
	private SquarePolygon currentCollisionPolygon;
	private Direction currentDirection;
	private Direction bufferedDirection;

	public PacMan(SquarePolygon collisionPolygon,
			DirectionBuilder directionBuilder) throws SlickException {
		currentCollisionPolygon = collisionPolygon;
		this.directionBuilder = directionBuilder;
		this.directionBuilder.buildDirectionMap();
		currentDirection = directionBuilder.defaultDirection();
		bufferedDirection = new NullDirection();
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

	public void update(GameContainer gc, int delta, Board board) {
		Direction nextDirection = directionBuilder.from(gc.getInput());

		if (canMove(nextDirection, board)) {
			move(nextDirection, board);
		} else if (canMove(bufferedDirection, board)) {
			move(bufferedDirection, board);
		} else if (canMove(currentDirection, board)) {
			move(currentDirection, board);
		}

		if (!(nextDirection instanceof NullDirection)) {
			bufferedDirection = nextDirection;
		}
	}

	private boolean canMove(Direction direction, Board board) {
		SquarePolygon movedCollisionPolygon = direction.move(
				currentCollisionPolygon, SPEED);
		return movedCollisionPolygon == null ? false : !board
				.isCollidingWith(movedCollisionPolygon);
	}

	private void move(Direction direction, Board board) {
		SquarePolygon movedCollisionPolygon = direction.move(
				currentCollisionPolygon, SPEED);

		currentCollisionPolygon = movedCollisionPolygon;
		currentDirection = direction;
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
