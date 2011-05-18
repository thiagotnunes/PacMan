package com.pacman.entity.maze;

import static com.pacman.game.properties.LayerProperties.*;

import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.tile.Tile;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.Drawable;

public class Board implements Drawable {

	private final TiledMap map;
	private final List<Tile> walls;
	private final List<Tile> food;

	public Board(TiledMap map, List<Tile> walls, List<Tile> food) {
		this.map = map;
		this.walls = walls;
		this.food = food;
	}

	@Override
	public void draw(Graphics g) {
		drawMap();
		drawFood(g);
	}

	private void drawMap() {
		for (int i = 0; i < map.getLayerCount(); i++) {
			if (isLayerVisible(i)) {
				map.render(0, 0, i);
			}
		}
	}

	private void drawFood(Graphics g) {
		for (Tile tile : food) {
			tile.draw(g);
		}
	}

	public boolean isCollidingWithWall(CollisionPolygon collidable) {
		return collidingTile(collidable, walls) != null;
	}

	public void consume(CollisionPolygon collidable) {
		Tile tile = collidingTile(collidable, food);
		food.remove(tile);
	}

	private Tile collidingTile(CollisionPolygon collidable, List<Tile> tiles) {
		for (Tile tile : tiles) {
			if (tile.isCollidingWith(collidable)) {
				return tile;
			}
		}
		return null;
	}

	private Boolean isLayerVisible(int layerIndex) {
		return Boolean.valueOf(map.getLayerProperty(layerIndex, VISIBLE
				.property(), VISIBLE.defaultValue()));
	}

}
