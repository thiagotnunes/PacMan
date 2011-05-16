package com.pacman.entity.character;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.maze.Board;
import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.MovementBuilder;
import com.pacman.geometry.CollisionPolygon;

public class PacManFactoryTest {

	@Test
	public void shouldCreatePacManUsingPolygonDirectionAndBoard() throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		MovementBuilder movementBuilder = mock(MovementBuilder.class);
		Movement movement = mock(Movement.class);
		Board board = mock(Board.class);
		
		when(movementBuilder.defaultDirection()).thenReturn(movement);
		
		PacMan pacMan = new PacManFactory(movementBuilder).from(collisionPolygon, board);
		
		verify(movementBuilder).buildMovements();
		verify(movementBuilder).defaultDirection();
		
		assertSame(collisionPolygon, pacMan.currentCollisionPolygon);
		assertSame(movement, pacMan.currentMovement);
		assertSame(board, pacMan.board);
	}

}
