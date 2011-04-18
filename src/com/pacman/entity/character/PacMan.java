package com.pacman.entity.character;

import java.util.Map;

import org.lwjgl.util.Point;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import com.pacman.geometry.SquarePolygon;
import com.pacman.renderer.Renderable;

public class PacMan implements Renderable {

	public static final Float SPEED = 0.1f;
	protected Direction currentDirection;
	private final Map<Direction, Animation> animationMap;
	protected Animation currentAnimation;
	private SquarePolygon squarePolygon;

	public PacMan(SquarePolygon squarePolygon,
			Map<Direction, Animation> animationMap, Direction currentDirection) {
		this.squarePolygon = squarePolygon;
		this.animationMap = animationMap;
		this.currentDirection = currentDirection;
		updateAnimation(currentDirection);
	}

	public void draw(Graphics g) {
		Point position = squarePolygon.getPosition();
		Polygon polygon = squarePolygon.getPolygon();
		currentAnimation.draw(position.getX() * SPEED, position.getY() * SPEED,
				polygon.getWidth(), polygon.getHeight());
	}

	public void updateDirectionIfRequested(Input input) {
		currentDirection = currentDirection.fromInput(input);
		updateAnimation(currentDirection);
	}

	private void updateAnimation(Direction currentDirection) {
		currentAnimation = animationMap.get(currentDirection);
	}

	public void move(int delta) {
		squarePolygon = currentDirection.move(squarePolygon, delta);
	}

	@Override
	public Point getPosition() {
		return squarePolygon.getPosition();
	}

	public Shape updatedShape(int delta) {
		return currentDirection.move(squarePolygon, delta).getPolygon();
	}

}
