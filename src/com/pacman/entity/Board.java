package com.pacman.entity;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.renderer.Renderable;
import com.pacman.renderer.Renderer;

public class Board implements Renderable {

	private static final int COLLISION_LAYER = 0;
	private List<Block> blocks;
	private final Dimension dimension;
	private final Renderer renderer;

	public Board(TiledMap map, Dimension dimension, Renderer renderer) {
		this.renderer = renderer;
		blocks = createBlocksFrom(map);
		this.dimension = dimension;
	}
	
	protected Board(TiledMap map, Dimension dimension, Renderer renderer,
			List<Block> blocks) {
		this(map, dimension, renderer);
		this.blocks = blocks;
	}

	private List<Block> createBlocksFrom(TiledMap map) {
		List<Block> blocks = new ArrayList<Block>();
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				if (map.getTileId(x, y, COLLISION_LAYER) == 1) {
					blocks.add(new Block(x, y));
				}
			}
		}
		return blocks;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public Dimension getDimension() {
		return dimension;
	}

	@Override
	public void draw(Graphics g) {
		for (Block block: blocks) {
			renderer.render(block, g);
		}
	}

	@Override
	public Point getPosition() {
		return new Point(0, 0);
	}

}
