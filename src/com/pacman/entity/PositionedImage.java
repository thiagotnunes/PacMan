package com.pacman.entity;

import static com.pacman.entity.Direction.*;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;
import org.lwjgl.util.ReadablePoint;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Image;

import com.pacman.renderer.Renderable;

public class PositionedImage implements Renderable {
	private Image image;
	private Rectangle rectangle;

	public PositionedImage(ReadablePoint position, ReadableDimension dimension,
			Image image) {
		this(new Rectangle(position, dimension), image);
	}

	public PositionedImage(Rectangle rectangle, Image image) {
		this.rectangle = rectangle;
		this.image = image;
	}

	public void draw() {
		image.draw(0, 0, rectangle.getWidth(), rectangle.getHeight());
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

	@Override
	public Point getPosition() {
		return new Point(rectangle.getX(), rectangle.getY());
	}

}