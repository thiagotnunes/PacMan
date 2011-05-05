package com.pacman.entity.character;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.direction.Direction;
import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;

public class PacManFactoryTest {

	@Test
	public void shouldCreatePacManUsingPolygonDirectionAndBoard() throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		Direction direction = mock(Direction.class);
		Board board = mock(Board.class);
		
		PacMan pacMan = new PacManFactory().from(collisionPolygon, direction, board);
		
		assertSame(collisionPolygon, pacMan.currentCollisionPolygon);
		assertSame(direction, pacMan.currentDirection);
		assertSame(board, pacMan.board);
	}

}
