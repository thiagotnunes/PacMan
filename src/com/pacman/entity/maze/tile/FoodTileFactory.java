package com.pacman.entity.maze.tile;

import static java.io.File.separator;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.collision.PolygonFactory;
import com.pacman.entity.maze.filter.TileFilter;
import com.pacman.graphics.ImageFactory;

public class FoodTileFactory extends TileFactory {

	private static final String FOOD_PATH = "data" + separator + "maze" + separator + "food.png";
	private static final float Y_DISPLACEMENT = 10f;
	private static final float X_DISPLACEMENT = 10f;
	private static final float FOOD_WIDTH = 6f;

	private final PolygonFactory polygonFactory;
	private final ImageFactory imageFactory;

	public FoodTileFactory(TileFilter filter, PolygonFactory polygonFactory, ImageFactory imageFactory) {
		super(filter);
		this.polygonFactory = polygonFactory;
		this.imageFactory = imageFactory;
	}

	@Override
	protected Tile createTile(Integer x, Integer y, TiledMap map) throws SlickException {
		Float width = (float) map.getTileWidth();
		return new Tile(polygonFactory.from(x * width + X_DISPLACEMENT, y
				* width + Y_DISPLACEMENT, FOOD_WIDTH), imageFactory.from(FOOD_PATH));
	}

}
