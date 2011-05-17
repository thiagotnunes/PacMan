package com.pacman.entity.character;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.maze.Board;
import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.strategy.MovementStrategy;
import com.pacman.geometry.CollisionPolygon;

public class PacManTest {

	private PacMan pacMan;
	private CollisionPolygon collisionPolygon;
	private Board board;
	private MovementStrategy strategy;
	private Movement initialMovement;

	@Before
	public void setUp() throws SlickException {
		collisionPolygon = mock(CollisionPolygon.class);
		strategy = mock(MovementStrategy.class);
		board = mock(Board.class);
		initialMovement = mock(Movement.class);

		when(strategy.currentMovement()).thenReturn(initialMovement);

		pacMan = new PacMan(collisionPolygon, strategy, board);
		
		verify(strategy).currentMovement();
	}

	@Test
	public void shouldSetInitialMovement() throws Exception {
		assertSame(initialMovement, pacMan.currentMovement);
	}
	
	@Test
	public void shouldDrawPacMan() throws Exception {
		Float x = 20f;
		Float y = 30f;
		Polygon polygon = mock(Polygon.class);
		Graphics g = mock(Graphics.class);
		Animation animation = mock(Animation.class);

		when(initialMovement.getAnimation()).thenReturn(animation);
		when(collisionPolygon.getPolygon()).thenReturn(polygon);
		when(polygon.getX()).thenReturn(x);
		when(polygon.getY()).thenReturn(y);

		pacMan.draw(g);

		verify(animation).draw(x, y, polygon.getWidth(), polygon.getHeight());
	}

	@Test
	public void shouldUpdateDirection() throws Exception {
		Movement movement = mock(Movement.class);

		pacMan.updateDirection(movement);

		verify(strategy).update(movement, collisionPolygon, PacMan.SPEED);
	}
	
	@Test
	public void shouldMoveUsingAvailableMovement() throws Exception {
		Movement nextMovement = mock(Movement.class);
		CollisionPolygon movedPolygon = mock(CollisionPolygon.class);
		
		when(strategy.availableMovement(collisionPolygon, PacMan.SPEED)).thenReturn(nextMovement);
		when(nextMovement.move(collisionPolygon, PacMan.SPEED)).thenReturn(movedPolygon);
		
		pacMan.move();
		
		verify(strategy).availableMovement(collisionPolygon, PacMan.SPEED);
		verify(nextMovement).move(collisionPolygon, PacMan.SPEED);
		
		assertSame(movedPolygon, pacMan.currentCollisionPolygon);
	}

	@Test
	public void shouldEat() throws Exception {
		Board board = mock(Board.class);

		pacMan.eat(board);

		verify(board).consume(collisionPolygon);
	}
}