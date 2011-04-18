package com.pacman.entity.maze;

import org.newdawn.slick.SlickException;

public class BoardFactory {

	private final MapFactory mapFactory;

	public BoardFactory(MapFactory mapFactory) {
		this.mapFactory = mapFactory;
	}

	public Board create(String path, Integer blockSize) throws SlickException {
		return new Board(mapFactory.create(path), blockSize);
	}

}
