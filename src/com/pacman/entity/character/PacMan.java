package com.pacman.entity.character;

import java.util.Map;

import org.lwjgl.util.Point;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import com.pacman.geometry.SquarePolygon;
import com.pacman.renderer.Renderable;

public class PacMan implements Renderable {

	public static final Float SPEED = 0.50f;
	private final Map<Direction, Animation> animationMap;
	private Direction currentDirection;
	private Animation currentAnimation;
	private SquarePolygon collisionPolygon;

	public PacMan(SquarePolygon squarePolygon,
			Map<Direction, Animation> animationMap, Direction currentDirection) {
		this.collisionPolygon = squarePolygon;
		this.animationMap = animationMap;
		this.currentDirection = currentDirection;
		updateAnimation(currentDirection);
	}

	public void draw(Graphics g) {
		Polygon polygon = collisionPolygon.getPolygon();
		currentAnimation.draw(0, 0, polygon.getWidth(), polygon.getHeight());
	}

	public void updateDirection(Direction direction) {
		currentDirection = direction;
		updateAnimation(direction);
	}

	private void updateAnimation(Direction currentDirection) {
		currentAnimation = animationMap.get(currentDirection);
	}

	public void updateCollisionPolygon(SquarePolygon squarePolygon) {
		this.collisionPolygon = squarePolygon;
	}

	public SquarePolygon translate(float delta, Direction direction) {
		return direction.move(collisionPolygon, delta);
	}

	public Direction currentDirection() {
		return currentDirection;
	}

	public Animation currentAnimation() {
		return currentAnimation;
	}

	@Override
	public Point getPosition() {
		return collisionPolygon.getPosition();
	}
}
