package com.pacman.entity.character;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.direction.Direction;
import com.pacman.entity.direction.DirectionBuilder;
import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;

public class PacManFactoryTest {

	@Test
	public void shouldCreatePacManUsingPolygonDirectionAndBoard() throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		DirectionBuilder directionBuilder = mock(DirectionBuilder.class);
		Direction direction = mock(Direction.class);
		Board board = mock(Board.class);
		
		when(directionBuilder.defaultDirection()).thenReturn(direction);
		
		PacMan pacMan = new PacManFactory(directionBuilder).from(collisionPolygon, board);
		
		verify(directionBuilder).buildDirections();
		verify(directionBuilder).defaultDirection();
		
		assertSame(collisionPolygon, pacMan.currentCollisionPolygon);
		assertSame(direction, pacMan.currentDirection);
		assertSame(board, pacMan.board);
	}

}
