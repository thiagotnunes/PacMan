package com.pacman.entity.maze.tile;

import static java.io.File.*;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.filter.TileFilter;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.ImageFactory;

public class FoodTileFactory extends TileFactory {

	private static final float Y_DISPLACEMENT = 10f;
	private static final float X_DISPLACEMENT = 10f;
	private static final float FOOD_WIDTH = 6f;

	public static final String FOOD_PATH = "data" + separator + "maze"
			+ separator + "food" + separator + "normalFood.png";

	private final ImageFactory imageFactory;
	private final String path;

	public FoodTileFactory(TileFilter filter, ImageFactory imageFactory,
			String path) {
		super(filter);
		this.imageFactory = imageFactory;
		this.path = path;
	}

	@Override
	protected Tile createTile(Integer x, Integer y, TiledMap map)
			throws SlickException {
		Float width = (float) map.getTileWidth();
		return new ImageTile(new CollisionPolygon(x * width + X_DISPLACEMENT, y
				* width + Y_DISPLACEMENT, FOOD_WIDTH), imageFactory.from(path));
	}

}
