package com.pacman.entity.direction;

import static org.junit.Assert.*;

import org.junit.Test;

public class NullDirectionTest {

	@Test
	public void shouldNotMovePolygon() throws Exception {
		assertNull(new NullDirection().move(null, 0f));
	}

	@Test
	public void animationShouldBeNull() throws Exception {
		assertNull(new NullDirection().getAnimation());
	}
	
	@Test
	public void shouldNotBeAbleToMove() throws Exception {
		assertFalse(new NullDirection().canMove(null, null, null));
	}

}
