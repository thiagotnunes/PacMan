package com.pacman.entity.maze.filter;

import org.newdawn.slick.tiled.TiledMap;

import com.pacman.game.properties.TileProperties;

public abstract class TileFilter {

	protected final TileProperties property;

	public TileFilter(TileProperties tileProperty) {
		this.property = tileProperty;
	}

	public Boolean isValid(Integer id, TiledMap map) {
		return Boolean.valueOf(map.getTileProperty(id, property.property(),
				property.defaultValue()));
	}

}
