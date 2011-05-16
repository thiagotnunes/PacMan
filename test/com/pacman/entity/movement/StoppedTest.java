package com.pacman.entity.movement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;


public class StoppedTest {

	@Test
	public void shouldNotBeAbleToMove() throws Exception {
		Movement movement = new Stopped();
		
		assertFalse(movement.canMove(mock(CollisionPolygon.class), 1f, mock(Board.class)));
	}
	
	@Test
	public void shouldReturnSamePolygonWhenMoving() throws Exception {
		Stopped movement = new Stopped();
		
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		assertSame(collisionPolygon, movement.move(collisionPolygon, 1f));
	}
}
