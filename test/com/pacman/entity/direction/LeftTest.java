package com.pacman.entity.direction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Animation;

import com.pacman.entity.character.AnimationFactory;
import com.pacman.geometry.SquarePolygon;

public class LeftTest {

	@Test
	public void shouldMovePolygonToTheLeft() throws Exception {
		Float delta = 1f;
		AnimationFactory animationFactory = mock(AnimationFactory.class);
		Animation animation = mock(Animation.class);
		SquarePolygon polygon = mock(SquarePolygon.class);
		SquarePolygon response = mock(SquarePolygon.class);

		when(animationFactory.from("left", Direction.ANIMATION_DELAY))
				.thenReturn(animation);
		when(polygon.translate(-delta, 0)).thenReturn(response);

		Direction direction = new Left(animationFactory);

		assertSame(response, direction.move(polygon, delta));
		assertSame(animation, direction.getAnimation());

		verify(polygon).translate(-delta, 0);
	}

}
