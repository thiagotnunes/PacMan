package com.pacman.entity.movement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.maze.Board;
import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.NullMovement;
import com.pacman.geometry.CollisionPolygon;


public class NullDirectionTest {

	@Test
	public void shouldNotBeAbleToMove() throws Exception {
		Movement movement = new NullMovement();
		
		assertFalse(movement.canMove(mock(CollisionPolygon.class), 1f, mock(Board.class)));
	}
	
}
