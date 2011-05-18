package com.pacman.entity.movement.strategy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.pacman.entity.character.PacMan;
import com.pacman.entity.maze.Board;
import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.MovementFactory;
import com.pacman.entity.movement.NullMovement;
import com.pacman.entity.movement.Stopped;
import com.pacman.geometry.CollisionPolygon;


public class BufferedMovementStrategyTest {

	private Board board;
	private Float speed;
	private CollisionPolygon collisionPolygon;
	private BufferedMovementStrategy movementStrategy;
	private Movement nextMovement;
	private Movement bufferedMovement;
	private Movement currentMovement;
	private MovementFactory movementFactory;

	@Before
	public void setUp() {
		board = mock(Board.class);
		nextMovement = mock(Movement.class);
		bufferedMovement = mock(Movement.class);
		currentMovement = mock(Movement.class);
		speed = PacMan.SPEED;
		collisionPolygon = mock(CollisionPolygon.class);
		movementFactory = mock(MovementFactory.class);
		
		when(movementFactory.defaultMovement()).thenReturn(currentMovement);
		
		movementStrategy = new BufferedMovementStrategy(board, movementFactory, bufferedMovement);
		
		verify(movementFactory).defaultMovement();
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
	public void shouldUpdateCurrentDirectionToNext() throws Exception {
		when(nextMovement.canMove(collisionPolygon, speed, board)).thenReturn(true);
		
		movementStrategy.update(nextMovement, collisionPolygon, speed);
		
		assertSame(nextMovement, movementStrategy.currentMovement());
		assertSame(bufferedMovement, movementStrategy.bufferedMovement);
	}
	
	@Test
	public void shouldBufferDirectionWhenThereIsCollisionToNext() throws Exception {
		when(nextMovement.canMove(collisionPolygon, speed, board)).thenReturn(false);
		
		movementStrategy.update(nextMovement, collisionPolygon, speed);
		
		assertSame(currentMovement, movementStrategy.currentMovement());
		assertSame(nextMovement, movementStrategy.bufferedMovement);
	}
	
	@Test
	public void shouldReturnBufferedMovementIfCanMoveThere() throws Exception {
		NullMovement nullMovement = mock(NullMovement.class);
		
		when(bufferedMovement.canMove(collisionPolygon, speed, board)).thenReturn(true);
		when(movementFactory.nullMovement()).thenReturn(nullMovement);
		
		Movement result = movementStrategy.availableMovement(collisionPolygon, speed);
		
		verify(movementFactory).nullMovement();
		
		assertSame(bufferedMovement, result);
		assertSame(nullMovement, movementStrategy.bufferedMovement);
	}
	
	@Test
	public void shouldReturnCurrentDirectionIfCanMoveThere() throws Exception {
		when(bufferedMovement.canMove(collisionPolygon, speed, board)).thenReturn(false);
		when(currentMovement.canMove(collisionPolygon, speed, board)).thenReturn(true);
		
		Movement result = movementStrategy.availableMovement(collisionPolygon, speed);
		
		assertSame(currentMovement, result);
	}
	
	@Test
	public void shouldReturnStoppedCorrespondenceIfCantMoveAnywhere() throws Exception {
		Stopped stopped = mock(Stopped.class);
		NullMovement nullMovement = mock(NullMovement.class);

		when(bufferedMovement.canMove(collisionPolygon, speed, board)).thenReturn(false);
		when(currentMovement.canMove(collisionPolygon, speed, board)).thenReturn(false);
		when(movementFactory.stoppedFrom(currentMovement)).thenReturn(stopped);
		when(movementFactory.nullMovement()).thenReturn(nullMovement);
		
		Movement result = movementStrategy.availableMovement(collisionPolygon, speed);
		
		verify(movementFactory).nullMovement();
		
		assertSame(stopped, result);
		assertSame(nullMovement, movementStrategy.bufferedMovement);
	}
	
}
