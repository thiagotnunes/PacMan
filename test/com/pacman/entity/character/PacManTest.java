package com.pacman.entity.character;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.direction.Direction;
import com.pacman.entity.maze.Board;
import com.pacman.geometry.CollisionPolygon;

public class PacManTest {

	private PacMan pacMan;
	private CollisionPolygon collisionPolygon;
	private Direction initialDirection;
	private Board board;

	@Before
	public void setUp() throws SlickException {
		collisionPolygon = mock(CollisionPolygon.class);
		initialDirection = mock(Direction.class);
		board = mock(Board.class);


		pacMan = new PacMan(collisionPolygon, initialDirection, board);
	}
	
	@Test
	public void shouldSetCurrentDirectionAsDefault() throws Exception {
		assertSame(initialDirection, pacMan.currentDirection);
	}

	@Test
	public void shouldDrawPacMan() throws Exception {
		Float x = 20f;
		Float y = 30f;
		Polygon polygon = mock(Polygon.class);
		Graphics g = mock(Graphics.class);
		Animation animation = mock(Animation.class);

		when(collisionPolygon.getPolygon()).thenReturn(polygon);
		when(polygon.getX()).thenReturn(x);
		when(polygon.getY()).thenReturn(y);
		when(initialDirection.getAnimation()).thenReturn(animation);

		pacMan.draw(g);

		verify(animation).draw(x, y, polygon.getWidth(), polygon.getHeight());
	}

	@Test
	public void shouldUpdateDirectionWhenThereIsNoCollision() throws Exception {
		Direction direction = mock(Direction.class);
		
		when(direction.canMove(collisionPolygon, PacMan.SPEED, board)).thenReturn(true);

		pacMan.updateDirection(direction);
		
		assertSame(direction, pacMan.currentDirection);
	}
	
	@Test
	public void shouldBufferDirectionWhenThereIsCollision() throws Exception {
		Direction direction = mock(Direction.class);
		
		when(direction.canMove(collisionPolygon, PacMan.SPEED, board)).thenReturn(false);
		
		pacMan.updateDirection(direction);
		
		assertSame(initialDirection, pacMan.currentDirection);
		assertSame(direction, pacMan.bufferedDirection);
	}
	
	@Test
	public void shouldMoveToBufferedIfPossible() throws Exception {
		CollisionPolygon movedCollisionPolygon = mock(CollisionPolygon.class);
		Direction bufferedDirection = mock(Direction.class);
		pacMan.bufferedDirection = bufferedDirection;
		
		when(bufferedDirection.canMove(collisionPolygon, PacMan.SPEED, board)).thenReturn(true);
		when(bufferedDirection.move(collisionPolygon, PacMan.SPEED)).thenReturn(movedCollisionPolygon);
		
		pacMan.move();
		
		verify(bufferedDirection).canMove(collisionPolygon, PacMan.SPEED, board);
		verify(bufferedDirection).move(collisionPolygon, PacMan.SPEED);
		
		assertSame(pacMan.currentCollisionPolygon, movedCollisionPolygon);
		assertSame(pacMan.currentDirection, bufferedDirection);
	}
	
	@Test
	public void shouldMoveToCurrentIfCantMoveToBuffered() throws Exception {
		CollisionPolygon movedCollisionPolygon = mock(CollisionPolygon.class);
		Direction bufferedDirection = mock(Direction.class);
		pacMan.bufferedDirection = bufferedDirection;
		
		when(bufferedDirection.canMove(collisionPolygon, PacMan.SPEED, board)).thenReturn(false);
		when(initialDirection.canMove(collisionPolygon, PacMan.SPEED, board)).thenReturn(true);
		when(initialDirection.move(collisionPolygon, PacMan.SPEED)).thenReturn(movedCollisionPolygon);
		
		pacMan.move();
		
		verify(bufferedDirection).canMove(collisionPolygon, PacMan.SPEED, board);
		verify(initialDirection).canMove(collisionPolygon, PacMan.SPEED, board);
		verify(initialDirection).move(collisionPolygon, PacMan.SPEED);
		
		assertSame(pacMan.currentCollisionPolygon, movedCollisionPolygon);
	}
	
	@Test
	public void shouldNotMoveIfItIsColliding() throws Exception {
		Direction bufferedDirection = mock(Direction.class);
		pacMan.bufferedDirection = bufferedDirection;
		
		when(bufferedDirection.canMove(collisionPolygon, PacMan.SPEED, board)).thenReturn(false);
		when(initialDirection.canMove(collisionPolygon, PacMan.SPEED, board)).thenReturn(false);
		
		pacMan.move();
		
		verify(bufferedDirection).canMove(collisionPolygon, PacMan.SPEED, board);
		verify(bufferedDirection, never()).move(any(CollisionPolygon.class), anyFloat());
		verify(initialDirection).canMove(collisionPolygon, PacMan.SPEED, board);
		verify(initialDirection, never()).move(any(CollisionPolygon.class), anyFloat());
		
		assertSame(pacMan.currentCollisionPolygon, collisionPolygon);
	}

	@Test
	public void shouldEat() throws Exception {
		Board board = mock(Board.class);
		
		pacMan.eat(board);
		
		verify(board).consume(collisionPolygon);
	}
}