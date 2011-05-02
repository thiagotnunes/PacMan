package com.pacman.entity.character;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

import com.pacman.entity.direction.Direction;
import com.pacman.entity.direction.DirectionBuilder;
import com.pacman.entity.direction.NullDirection;
import com.pacman.entity.maze.Board;
import com.pacman.entity.maze.tile.FoodTile;
import com.pacman.geometry.Point;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.renderer.Renderable;

public class PacManTest {

	private PacMan pacMan;
	private CollisionPolygon collisionPolygon;
	private DirectionBuilder directionFactory;
	private Direction direction;

	@Before
	public void setUp() throws SlickException {
		collisionPolygon = mock(CollisionPolygon.class);
		directionFactory = mock(DirectionBuilder.class);

		direction = mock(Direction.class);
		when(directionFactory.defaultDirection()).thenReturn(direction);

		pacMan = new PacMan(collisionPolygon, directionFactory);

		verify(directionFactory).buildDirectionMap();
	}

	@Test
	public void shouldSetCurrentDirectionAsDefault() throws Exception {
		verify(directionFactory).defaultDirection();

		assertSame(direction, pacMan.currentDirection());
	}

	@Test
	public void shouldDrawPacMan() throws Exception {
		Float x = 20f;
		Float y = 30f;
		Point position = new Point(x, y);
		Polygon polygon = mock(Polygon.class);
		Graphics g = mock(Graphics.class);
		Animation animation = mock(Animation.class);

		when(collisionPolygon.getPosition()).thenReturn(position);
		when(collisionPolygon.getPolygon()).thenReturn(polygon);
		when(direction.getAnimation()).thenReturn(animation);

		pacMan.draw(g);

		verify(animation).draw(0, 0, polygon.getWidth(), polygon.getHeight());
	}

	@Test
	public void shouldReturnCurrentPosition() throws Exception {
		Point position = new Point(10f, 20f);

		when(collisionPolygon.getPosition()).thenReturn(position);

		assertTrue(pacMan instanceof Renderable);
		assertSame(position, pacMan.getPosition());
	}

	@Test
	public void shouldMovePacManIfWillNotCollideWithBoard() throws Exception {
		int delta = 1;
		GameContainer gc = mock(GameContainer.class);
		Board board = mock(Board.class);
		Input input = mock(Input.class);
		CollisionPolygon movedCollisionPolygon = mock(CollisionPolygon.class);
		Direction nextDirection = mock(Direction.class);

		when(gc.getInput()).thenReturn(input);
		when(directionFactory.from(input)).thenReturn(nextDirection);
		when(nextDirection.canMove(collisionPolygon, PacMan.SPEED, board)).thenReturn(true);
		when(nextDirection.move(collisionPolygon, PacMan.SPEED)).thenReturn(
				movedCollisionPolygon);

		pacMan.move(gc, delta, board);

		assertSame(movedCollisionPolygon, pacMan.currentCollisionPolygon());
		assertSame(nextDirection, pacMan.currentDirection());
	}

	@Test
	public void shouldNotMovePacManIfWillCollideWithBoard() throws Exception {
		int delta = 1;
		GameContainer gc = mock(GameContainer.class);
		Board board = mock(Board.class);
		Input input = mock(Input.class);
		CollisionPolygon currentDirectionPolygon = mock(CollisionPolygon.class);
		Direction nextDirection = mock(Direction.class);

		when(gc.getInput()).thenReturn(input);
		when(directionFactory.from(input)).thenReturn(nextDirection);
		when(nextDirection.canMove(collisionPolygon, PacMan.SPEED, board)).thenReturn(false);
		when(direction.canMove(collisionPolygon, PacMan.SPEED, board)).thenReturn(true);
		when(direction.move(collisionPolygon, PacMan.SPEED)).thenReturn(
				currentDirectionPolygon);

		pacMan.move(gc, delta, board);

		assertSame(currentDirectionPolygon, pacMan.currentCollisionPolygon());
		assertSame(direction, pacMan.currentDirection());
		assertSame(nextDirection, pacMan.bufferedDirection);
	}
	
	@Test
	public void directionShouldNotBeBufferedForNullDirection() throws Exception {
		int delta = 1;
		GameContainer gc = mock(GameContainer.class);
		Board board = mock(Board.class);
		Input input = mock(Input.class);
		CollisionPolygon currentDirectionPolygon = mock(CollisionPolygon.class);
		Direction nextDirection = new NullDirection();
		
		when(gc.getInput()).thenReturn(input);
		when(directionFactory.from(input)).thenReturn(nextDirection);
		when(direction.canMove(collisionPolygon, PacMan.SPEED, board)).thenReturn(true);
		when(direction.move(collisionPolygon, PacMan.SPEED)).thenReturn(
				currentDirectionPolygon);

		pacMan.move(gc, delta, board);

		assertSame(currentDirectionPolygon, pacMan.currentCollisionPolygon());
		assertSame(direction, pacMan.currentDirection());
		assertSame(direction, pacMan.bufferedDirection);
	}
	
	@Test
	public void shouldEat() throws Exception {
		Board board = mock(Board.class);
		FoodTile foodTile = mock(FoodTile.class);
		
		when(board.isCollidingWithFood(collisionPolygon)).thenReturn(foodTile);
		
		pacMan.eatFoodFrom(board);
		
		verify(foodTile).consume();
	}
}