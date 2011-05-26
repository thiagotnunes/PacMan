package com.pacman.game;

import static org.newdawn.slick.Input.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.character.PacMan;
import com.pacman.entity.character.PacManKeyListener;
import com.pacman.entity.collision.CollisionDetector;
import com.pacman.entity.maze.Board;
import com.pacman.entity.maze.consumable.Consumables;
import com.pacman.entity.maze.consumable.FoodFactory;
import com.pacman.entity.maze.filter.CollidableTileFilter;
import com.pacman.entity.maze.filter.ConsumableTileFilter;
import com.pacman.entity.maze.tile.FoodTileFactory;
import com.pacman.entity.maze.tile.ImageTile;
import com.pacman.entity.maze.tile.Tile;
import com.pacman.entity.maze.tile.WallTileFactory;
import com.pacman.entity.movement.Down;
import com.pacman.entity.movement.Left;
import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.MovementFactory;
import com.pacman.entity.movement.Right;
import com.pacman.entity.movement.Stopped;
import com.pacman.entity.movement.Up;
import com.pacman.entity.movement.strategy.BufferedMovementStrategy;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.MovementAnimationFactory;
import com.pacman.graphics.StoppedAnimationFactory;
import com.pacman.sound.SoundPlayer;

public class PacManApp extends BasicGame {

	private static final int WALL_LAYER = 0;
	private static final int FOOD_LAYER = 2;
	protected static final String MAP_PATH = "data/maze/1/complete.tmx";

	private PacManGame game;

	public PacManApp(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Board board = createBoard();
		PacMan pacMan = createPacMan(board);
		game = new PacManGame(pacMan, board);

		gc.getInput().addKeyListener(
				new PacManKeyListener(pacMan, createMovementBuilder()));
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		game.update(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		game.render(gc, g);
	}

	private PacMan createPacMan(Board board) throws SlickException {
		MovementFactory movementFactory = createMovementBuilder();
		CollisionPolygon collisionPolygon = new CollisionPolygon(324.1f,
				574.1f, 26.85f);
		BufferedMovementStrategy movementStrategy = new BufferedMovementStrategy(
				board, movementFactory, movementFactory.nullMovement());
		PacMan pacMan = new PacMan(collisionPolygon, movementStrategy, board);

		return pacMan;
	}

	private Board createBoard() throws SlickException {
		WallTileFactory wallFactory = new WallTileFactory(
				new CollidableTileFilter());
		FoodTileFactory foodTileFactory = new FoodTileFactory(
				new ConsumableTileFilter());
		TiledMap map = new TiledMap(MAP_PATH);
		List<Tile> walls = wallFactory.from(map, WALL_LAYER);
		List<ImageTile> food = foodTileFactory.from(map, FOOD_LAYER);
		FoodFactory foodFactory = new FoodFactory();
		Consumables consumables = new Consumables(foodFactory.from(food,
				new SoundPlayer("data/pacman/sounds/eating_original.wav")),
				new CollisionDetector());
		Board board = new Board(map, walls, consumables);

		//new SoundPlayer("data/pacman/sounds/eating.wav", (int) (25 / PacMan.SPEED) * 5))
		
		return board;
	}

	private MovementFactory createMovementBuilder() throws SlickException {
		MovementAnimationFactory animationFactory = new MovementAnimationFactory();
		Left left = new Left(animationFactory);
		Down down = new Down(animationFactory);
		Up up = new Up(animationFactory);
		Right right = new Right(animationFactory);
		MovementFactory movementFactory = new MovementFactory(left,
				buildMovements(left, down, up, right), buildStoppedMovements(
						left, down, up, right));

		return movementFactory;
	}

	private Map<Integer, Movement> buildMovements(Left left, Down down, Up up,
			Right right) {
		Map<Integer, Movement> movements = new HashMap<Integer, Movement>();
		movements.put(KEY_DOWN, down);
		movements.put(KEY_UP, up);
		movements.put(KEY_RIGHT, right);
		movements.put(KEY_LEFT, left);

		return movements;
	}

	private Map<Movement, Stopped> buildStoppedMovements(Left left, Down down,
			Up up, Right right) throws SlickException {
		StoppedAnimationFactory stoppedFactory = new StoppedAnimationFactory();
		Stopped stoppedUp = new Stopped(stoppedFactory, up);
		Stopped stoppedDown = new Stopped(stoppedFactory, down);
		Stopped stoppedRight = new Stopped(stoppedFactory, right);
		Stopped stoppedLeft = new Stopped(stoppedFactory, left);
		Map<Movement, Stopped> stoppedMovements = new HashMap<Movement, Stopped>();
		stoppedMovements.put(up, stoppedUp);
		stoppedMovements.put(down, stoppedDown);
		stoppedMovements.put(right, stoppedRight);
		stoppedMovements.put(left, stoppedLeft);
		stoppedMovements.put(stoppedUp, stoppedUp);
		stoppedMovements.put(stoppedDown, stoppedDown);
		stoppedMovements.put(stoppedRight, stoppedRight);
		stoppedMovements.put(stoppedLeft, stoppedLeft);

		return stoppedMovements;
	}

}
