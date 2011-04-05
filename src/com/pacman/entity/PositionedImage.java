package com.pacman.entity;

import static com.pacman.entity.Direction.*;

import org.lwjgl.util.ReadableDimension;
import org.lwjgl.util.ReadablePoint;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Image;

public class PositionedImage {
	private static final float SPEED = 0.1f;
	private Image image;
	private Rectangle rectangle;

	public PositionedImage(ReadablePoint position, ReadableDimension dimension,
			Image image) {
		this(new Rectangle(position, dimension), image);
	}

	protected PositionedImage(Rectangle rectangle, Image image) {
		this.rectangle = rectangle;
		this.image = image;
	}

	public void draw() {
		image.draw(rectangle.getX() * SPEED, rectangle.getY() * SPEED,
				(float) rectangle.getWidth(), (float) rectangle.getHeight());
	}

	public void move(Direction direction, int increment, int delta) {
		int moveDelta = increment * delta;
		if (direction == UP) {
			rectangle.translate(0, -moveDelta);
		} else if (direction == DOWN) {
			rectangle.translate(0, moveDelta);
		} else if (direction == RIGHT) {
			rectangle.translate(moveDelta, 0);
		} else if (direction == LEFT) {
			rectangle.translate(-moveDelta, 0);
		} else {
			throw new IllegalArgumentException("Illegal direction");
		}
	}

}