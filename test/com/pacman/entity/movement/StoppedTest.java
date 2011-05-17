package com.pacman.entity.movement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.StoppedAnimationFactory;

public class StoppedTest {

	private Movement movement;
	private Movement enclosingMovement;

	@Before
	public void setUp() throws SlickException {
		enclosingMovement = mock(Movement.class);
		movement = new Stopped(mock(StoppedAnimationFactory.class), enclosingMovement);
	}
	
	@Test
	public void shouldNotBeAbleToMove() throws Exception {
		assertFalse(movement.canMove(mock(CollisionPolygon.class), 1f, mock(Board.class)));
	}
	
	@Test
	public void shouldReturnSamePolygonWhenMoving() throws Exception {
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		assertSame(collisionPolygon, movement.move(collisionPolygon, 1f));
	}
}
