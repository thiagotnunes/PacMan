package com.pacman.entity.maze;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.Point;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.Collidable;
import com.pacman.geometry.SquarePolygon;
import com.pacman.renderer.Renderable;

public class Board implements Renderable, Collidable {

	private static final int COLLISION_LAYER = 0;
	private final TiledMap map;
	private List<Block> blocks;
	private Integer blockWidth;

	public Board(TiledMap map, Integer blockWidth) {
		this.map = map;
		this.blockWidth = blockWidth;
		blocks = createBlocksFrom(map);
	}

	protected Board(TiledMap map, List<Block> blocks) {
		this.map = map;
		this.blocks = blocks;
	}

	private List<Block> createBlocksFrom(TiledMap map) {
		List<Block> blocks = new ArrayList<Block>();
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				if (map.getTileId(x, y, COLLISION_LAYER) == 1) {
					blocks.add(new Block(new SquarePolygon(x * blockWidth, y * blockWidth, blockWidth)));
				}
			}
		}
		return blocks;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	@Override
	public void draw(Graphics g) {
		map.render(0, 0);
	}

	@Override
	public Point getPosition() {
		return new Point(0, 0);
	}

	@Override
	public boolean isCollidingWith(Shape shape) {
		for (Block block : blocks) {
			if (block.isCollidingWith(shape)) {
				return true;
			}
		}
		return false;
	}

}
