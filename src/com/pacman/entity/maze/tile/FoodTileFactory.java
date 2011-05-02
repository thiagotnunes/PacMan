package com.pacman.entity.maze.tile;

import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.collision.PolygonFactory;
import com.pacman.entity.maze.filter.TileFilter;

public class FoodTileFactory extends TileFactory {

	private static final float Y_DISPLACEMENT = 10f;
	private static final float X_DISPLACEMENT = 10f;
	private static final float FOOD_WIDTH = 6f;

	private final PolygonFactory polygonFactory;

	public FoodTileFactory(TileFilter filter, PolygonFactory polygonFactory) {
		super(filter);
		this.polygonFactory = polygonFactory;
	}

	@Override
	protected FoodTile createTile(Integer x, Integer y, TiledMap map) {
		Float width = (float) map.getTileWidth();
		return new FoodTile(polygonFactory.from(x * width + X_DISPLACEMENT, y
				* width + Y_DISPLACEMENT, FOOD_WIDTH));
	}

}
