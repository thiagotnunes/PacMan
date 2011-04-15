package com.pacman.entity.character;

import java.util.Map;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.pacman.entity.Direction;
import com.pacman.renderer.Renderable;

public class PacMan implements Renderable {

	public static final Float SPEED = 0.1f;
	protected Direction currentDirection;
	private final Point position;
	private final Map<Direction, Animation> animationMap;
	protected Animation currentAnimation;
	private final Dimension dimension;


	public PacMan(Point position, Dimension dimension, Map<Direction, Animation> animationMap, Direction currentDirection) {
		this.position = position;
		this.dimension = dimension;
		this.animationMap = animationMap;
		this.currentDirection = currentDirection;
		updateAnimation(currentDirection);
	}

	public void draw(Graphics g) {
		currentAnimation.draw(position.getX() * SPEED, position.getY() * SPEED, dimension.getWidth(), dimension.getHeight());
	}

	public void updateDirectionIfRequested(Input input) {
		currentDirection = currentDirection.fromInput(input);
		updateAnimation(currentDirection);
	}
	
	private void updateAnimation(Direction currentDirection) {
		currentAnimation = animationMap.get(currentDirection);
	}

	public void move(int delta) {
		currentDirection.movePoint(position, delta);
	}

	@Override
	public Point getPosition() {
		return position;
	}

}
