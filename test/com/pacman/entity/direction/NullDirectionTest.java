package com.pacman.entity.direction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;


public class NullDirectionTest {

	@Test
	public void shouldNotBeAbleToMove() throws Exception {
		Direction direction = new NullDirection();
		
		assertFalse(direction.canMove(mock(CollisionPolygon.class), 1f, mock(Board.class)));
	}
	
}
