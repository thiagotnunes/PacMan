package com.pacman.entity.character;

import static com.pacman.entity.Direction.DOWN;
import static com.pacman.entity.Direction.LEFT;
import static com.pacman.entity.Direction.RIGHT;
import static com.pacman.entity.Direction.UP;

import java.util.Map;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.newdawn.slick.Animation;
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

	public void draw() {
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
		int moveDelta = delta;
		if (currentDirection == UP) {
			position.translate(0, -moveDelta);
		} else if (currentDirection == DOWN) {
			position.translate(0, moveDelta);
		} else if (currentDirection == RIGHT) {
			position.translate(moveDelta, 0);
		} else if (currentDirection == LEFT) {
			position.translate(-moveDelta, 0);
		}
	}

	@Override
	public Point getPosition() {
		return position;
	}

}
