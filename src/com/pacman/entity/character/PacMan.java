package com.pacman.entity.character;

import org.newdawn.slick.Input;

import com.pacman.entity.Direction;
import com.pacman.entity.PositionedImage;

public class PacMan {

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

	public void setImage(PositionedImage positionedImage) {
		this.positionedImage = positionedImage;
	}
}
