package com.pacman.entity.maze;

import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.Collidable;
import com.pacman.entity.Point;
import com.pacman.geometry.SquarePolygon;
import com.pacman.renderer.Renderable;

public class Board implements Renderable, Collidable {

	private final TiledMap map;
	private List<Block> blocks;

	protected Board(TiledMap map, List<Block> blocks) {
		this.map = map;
		this.blocks = blocks;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	@Override
	public void draw(Graphics g) {
		map.render(0, 0);
		// for (Block block : blocks) {
		// g.draw(block.getPolygon());
		// }
	}

	@Override
	public Point getPosition() {
		return new Point(0f, 0f);
	}

	@Override
	public boolean isCollidingWith(SquarePolygon collidable) {
		for (Block block : blocks) {
			if (block.isCollidingWith(collidable)) {
				return true;
			}
		}
		return false;
	}

}
