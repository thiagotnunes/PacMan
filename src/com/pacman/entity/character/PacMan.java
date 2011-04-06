package com.pacman.entity.character;

import org.lwjgl.util.Point;
import org.newdawn.slick.Input;

import com.pacman.entity.Direction;
import com.pacman.entity.PositionedImage;
import com.pacman.renderer.Renderable;

public class PacMan implements Renderable {

	public static final Float SPEED = 0.1f;
	private PositionedImage positionedImage;
	protected Direction currentDirection;

	public PacMan(PositionedImage positionedImage, Direction direction) {
		this.positionedImage = positionedImage;
		this.currentDirection = direction;
	}

	public void draw() {
		positionedImage.draw();
	}

	public void updateDirectionIfRequested(Input input) {
		currentDirection = currentDirection.fromInput(input);
	}

	public void move(int delta) {
		positionedImage.move(currentDirection, 1, delta);
	}

	@Override
	public Point getPosition() {
		return positionedImage.getPosition();
	}

}
