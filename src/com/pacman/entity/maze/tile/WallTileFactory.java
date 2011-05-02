package com.pacman.entity.maze.tile;

import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.collision.PolygonFactory;
import com.pacman.entity.maze.filter.TileFilter;
import com.pacman.geometry.CollisionPolygon;

public class WallTileFactory extends TileFactory {

	private final PolygonFactory polygonFactory;

	public WallTileFactory(TileFilter filter, PolygonFactory polygonFactory) {
		super(filter);
		this.polygonFactory = polygonFactory;
	}

	@Override
	protected Tile createTile(Integer x, Integer y, TiledMap map) {
		Float width = (float) map.getTileWidth();
		CollisionPolygon collisionPolygon = polygonFactory.from((float) x,
				(float) y, width);
		return new Tile(collisionPolygon);
	}
}
