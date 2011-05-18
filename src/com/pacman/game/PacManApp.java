package com.pacman.game;

import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.character.PacMan;
import com.pacman.entity.character.PacManKeyListener;
import com.pacman.entity.maze.Board;
import com.pacman.entity.maze.filter.CollidableTileFilter;
import com.pacman.entity.maze.filter.ConsumableTileFilter;
import com.pacman.entity.maze.tile.FoodTileFactory;
import com.pacman.entity.maze.tile.Tile;
import com.pacman.entity.maze.tile.WallTileFactory;
import com.pacman.entity.movement.MovementBuilder;
import com.pacman.entity.movement.strategy.BufferedMovementStrategy;
import com.pacman.geometry.CollisionPolygon;

public class PacManApp extends BasicGame {

	protected static final String MAP_PATH = "data/maze/1/complete.tmx";
	private static final int FOOD_LAYER = 2;
	private static final int WALL_LAYER = 0;

	private PacManGame game;

	public PacManApp(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {

		WallTileFactory wallFactory = new WallTileFactory(
				new CollidableTileFilter());
		FoodTileFactory foodFactory = new FoodTileFactory(
				new ConsumableTileFilter());

		MovementBuilder movementBuilder = new MovementBuilder();
		movementBuilder.buildMovements();

		TiledMap map = new TiledMap(MAP_PATH);
		List<Tile> walls = wallFactory.from(map, WALL_LAYER);
		List<Tile> food = foodFactory.from(map, FOOD_LAYER);
		Board board = new Board(map, walls, food);

		CollisionPolygon collisionPolygon = new CollisionPolygon(324.1f,
				574.1f, 26.85f);
		BufferedMovementStrategy movementStrategy = new BufferedMovementStrategy(
				board, movementBuilder, movementBuilder.nullMovement());
		PacMan pacMan = new PacMan(collisionPolygon, movementStrategy, board);

		game = new PacManGame(pacMan, board);

		gc.getInput().addKeyListener(new PacManKeyListener(pacMan, movementBuilder));
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		game.update(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		game.render(gc, g);
	}

}
