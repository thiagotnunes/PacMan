package com.pacman.entity.maze.tile;

import static java.io.File.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.filter.TileFilter;
import com.pacman.geometry.CollisionPolygon;

public class FoodTileFactory extends TileFactory<ImageTile> {

	private static final float Y_DISPLACEMENT = 10f;
	private static final float X_DISPLACEMENT = 10f;
	private static final float FOOD_WIDTH = 6f;

	public static final String FOOD_PATH = "data" + separator + "maze"
			+ separator + "food" + separator + "normalFood.png";


	public FoodTileFactory(TileFilter filter) {
		super(filter);
	}

	@Override
	protected ImageTile createTile(Integer x, Integer y, TiledMap map)
			throws SlickException {
		return createTile(x, y, map, new Image(FOOD_PATH));
	}
	
	protected ImageTile createTile(Integer x, Integer y, TiledMap map, Image image) {
		Float width = (float) map.getTileWidth();
		return new ImageTile(new CollisionPolygon(x * width + X_DISPLACEMENT, y
				* width + Y_DISPLACEMENT, FOOD_WIDTH), image);
	}

}
