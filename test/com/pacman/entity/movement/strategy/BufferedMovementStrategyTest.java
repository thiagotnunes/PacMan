package com.pacman.entity.movement.strategy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.pacman.entity.character.PacMan;
import com.pacman.entity.maze.Board;
import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.MovementBuilder;
import com.pacman.entity.movement.NullMovement;
import com.pacman.geometry.CollisionPolygon;


public class BufferedMovementStrategyTest {

	private Board board;
	private Float speed;
	private CollisionPolygon collisionPolygon;
	private BufferedMovementStrategy movementStrategy;
	private Movement nextMovement;
	private Movement bufferedMovement;
	private Movement currentMovement;

	@Before
	public void setUp() {
		board = mock(Board.class);
		nextMovement = mock(Movement.class);
		bufferedMovement = mock(Movement.class);
		currentMovement = mock(Movement.class);
		speed = PacMan.SPEED;
		collisionPolygon = mock(CollisionPolygon.class);
		MovementBuilder movementBuilder = mock(MovementBuilder.class);
		
		when(movementBuilder.defaultMovement()).thenReturn(currentMovement);
		
		movementStrategy = new BufferedMovementStrategy(board, movementBuilder, bufferedMovement);
		
		verify(movementBuilder).defaultMovement();
	}
	
	@Test
	public void shouldBeAMovementStrategy() throws Exception {
		assertTrue(movementStrategy instanceof MovementStrategy);
	}
	
	@Test
	public void shouldReturnInitialMovement() throws Exception {
		assertSame(currentMovement, movementStrategy.currentMovement());
	}
	
	@Test
	public void shouldReturnUpdatedDirectionIfThereIsNoCollisionWithNext() throws Exception {
		when(nextMovement.canMove(collisionPolygon, speed, board)).thenReturn(true);
		
		Movement nextDirection = movementStrategy.next(nextMovement, collisionPolygon, speed);
		
		assertSame(nextMovement, nextDirection);
		assertSame(nextMovement, movementStrategy.currentMovement);
		assertTrue(movementStrategy.bufferedMovement instanceof NullMovement);
	}
	
	@Test
	public void shouldReturnBufferedDirectionWhenCollidesWithNextButNotWithBuffered() throws Exception {
		when(nextMovement.canMove(collisionPolygon, speed, board)).thenReturn(false);
		when(bufferedMovement.canMove(collisionPolygon, speed, board)).thenReturn(true);
		
		Movement nextDirection = movementStrategy.next(nextMovement, collisionPolygon, speed);
		
		assertSame(bufferedMovement, nextDirection);
		assertSame(bufferedMovement, movementStrategy.currentMovement);
		assertTrue(movementStrategy.bufferedMovement instanceof NullMovement);
	}
	
	@Test
	public void shouldReturnCurrentDirectionWhenCollidesWithNextAndBufferedButNotWithCurrent() throws Exception {
		when(nextMovement.canMove(collisionPolygon, speed, board)).thenReturn(false);
		when(bufferedMovement.canMove(collisionPolygon, speed, board)).thenReturn(false);
		
		Movement nextDirection = movementStrategy.next(bufferedMovement, collisionPolygon, speed);
		
		assertSame(nextDirection, currentMovement);
		assertSame(bufferedMovement, movementStrategy.bufferedMovement);
	}
	
}
