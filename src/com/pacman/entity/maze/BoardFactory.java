package com.pacman.entity.maze;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.tile.ImageTileFactory;
import com.pacman.entity.maze.tile.TileFactory;
import com.pacman.entity.maze.tile.WallTileFactory;

public class BoardFactory {

	private static final int FOOD_LAYER = 2;
	private static final int WALL_LAYER = 0;
	private final MapFactory mapFactory;
	private final TileFactory wallFactory;
	private final ImageTileFactory foodFactory;

	public BoardFactory(MapFactory mapFactory, WallTileFactory wallFactory, ImageTileFactory foodFactory) {
		this.mapFactory = mapFactory;
		this.wallFactory = wallFactory;
		this.foodFactory = foodFactory;
	}

	public Board from(String path) throws SlickException {
		TiledMap map = mapFactory.from(path);
		return new Board(map, wallFactory.from(map, WALL_LAYER), foodFactory
				.from(map, FOOD_LAYER));
	}
}
