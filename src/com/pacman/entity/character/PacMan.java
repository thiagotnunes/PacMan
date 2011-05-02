package com.pacman.entity.character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.direction.Direction;
import com.pacman.entity.direction.DirectionBuilder;
import com.pacman.entity.direction.NullDirection;
import com.pacman.entity.maze.Board;
import com.pacman.entity.maze.tile.FoodTile;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.geometry.Point;
import com.pacman.renderer.Renderable;

public class PacMan implements Renderable {

	public static final Float SPEED = 0.5f;

	private final DirectionBuilder directionBuilder;
	private CollisionPolygon currentCollisionPolygon;
	private Direction currentDirection;
	protected Direction bufferedDirection;

	public PacMan(CollisionPolygon collisionPolygon,
			DirectionBuilder directionBuilder) throws SlickException {
		currentCollisionPolygon = collisionPolygon;
		this.directionBuilder = directionBuilder;
		this.directionBuilder.buildDirectionMap();
		currentDirection = directionBuilder.defaultDirection();
		bufferedDirection = currentDirection;
	}

	public void draw(Graphics g) {
		Polygon polygon = currentCollisionPolygon.getPolygon();
		Animation animation = currentDirection.getAnimation();
		animation.draw(0, 0, polygon.getWidth(), polygon.getHeight());
//		g.draw(polygon);
//		g.drawString(currentCollisionPolygon.toString(), polygon.getCenterX(),
//				polygon.getCenterY());
	}

	public void move(GameContainer gc, int delta, Board board) {
		Direction nextDirection = directionBuilder.from(gc.getInput());

		if (nextDirection.canMove(currentCollisionPolygon, PacMan.SPEED, board)) {
			move(nextDirection);
		} else if (bufferedDirection.canMove(currentCollisionPolygon, PacMan.SPEED, board)) {
			move(bufferedDirection);
		} else if (currentDirection.canMove(currentCollisionPolygon, PacMan.SPEED, board)) {
			move(currentDirection);
		}

		if (!(nextDirection instanceof NullDirection)) {
			bufferedDirection = nextDirection;
		}
	}

	private void move(Direction direction) {
		currentCollisionPolygon = direction.move(
				currentCollisionPolygon, SPEED);
		currentDirection = direction;
	}

	public void eatFoodFrom(Board board) {
		FoodTile food = board.isCollidingWithFood(currentCollisionPolygon);
		food.consume();
	}

	public Direction currentDirection() {
		return currentDirection;
	}

	public CollisionPolygon currentCollisionPolygon() {
		return currentCollisionPolygon;
	}

	@Override
	public Point getPosition() {
		return currentCollisionPolygon.getPosition();
	}

}
