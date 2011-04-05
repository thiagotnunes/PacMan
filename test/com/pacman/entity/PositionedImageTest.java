package com.pacman.entity;

import static com.pacman.entity.Direction.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.lwjgl.util.ReadableDimension;
import org.lwjgl.util.ReadablePoint;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Image;

public class PositionedImageTest {

	@Test
	public void shouldDrawImage() throws Exception {
		Image image = mock(Image.class);
		ReadablePoint position = mock(ReadablePoint.class);
		ReadableDimension dimension = mock(ReadableDimension.class);
		new PositionedImage(position, dimension, image).draw();

		verify(image).draw(position.getX(), position.getY(),
				(float) dimension.getWidth(), (float) dimension.getWidth());
	}

	@Test
	public void shouldUpdatePosition() throws Exception {
		validateUpdatePosition(UP, 0, -1);
		validateUpdatePosition(DOWN, 0, 1);
		validateUpdatePosition(RIGHT, 1, 0);
		validateUpdatePosition(LEFT, -1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionForIllegalDirection() throws Exception {
		validateUpdatePosition(null, 0, 0);
	}

	private void validateUpdatePosition(Direction direction, int x, int y) {
		int currentX = 0;
		int currentY = 0;
		Rectangle rectangle = new Rectangle(currentX, currentY, 100, 100);
		PositionedImage image = new PositionedImage(rectangle, null);

		image.move(direction, 1, 1);

		assertEquals(currentX + x, rectangle.getX());
		assertEquals(currentY + y, rectangle.getY());
	}
}
