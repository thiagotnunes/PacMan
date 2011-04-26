package com.pacman.entity.direction;

import static org.junit.Assert.*;

import org.junit.Test;

public class NullDirectionTest {

	@Test
	public void should() throws Exception {
		Direction nullDirection = new NullDirection();
		fail();
	}
	
}
