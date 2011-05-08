package com.pacman.entity.maze.tile;

import static java.io.File.separator;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.collision.PolygonFactory;
import com.pacman.entity.maze.filter.TileFilter;
import com.pacman.graphics.ImageFactory;

public class ImageTileFactory extends TileFactory {

	private static final float Y_DISPLACEMENT = 10f;
	private static final float X_DISPLACEMENT = 10f;
	private static final float FOOD_WIDTH = 6f;

	public static final String FOOD_PATH = "data" + separator + "maze"
			+ separator + "food" + separator + "normalFood.png";

	private final PolygonFactory polygonFactory;
	private final ImageFactory imageFactory;
	private final String path;

	public ImageTileFactory(TileFilter filter, PolygonFactory polygonFactory,
			ImageFactory imageFactory, String path) {
		super(filter);
		this.polygonFactory = polygonFactory;
		this.imageFactory = imageFactory;
		this.path = path;
	}

	@Override
	protected Tile createTile(Integer x, Integer y, TiledMap map)
			throws SlickException {
		Float width = (float) map.getTileWidth();
		return new ImageTile(polygonFactory.from(x * width + X_DISPLACEMENT, y
				* width + Y_DISPLACEMENT, FOOD_WIDTH), imageFactory.from(path));
	}

}
