package com.pacman.game;

import org.newdawn.slick.AppGameContainer;

import com.pacman.entity.character.AnimationFactory;
import com.pacman.entity.character.PacManFactory;
import com.pacman.entity.maze.BlockFactory;
import com.pacman.entity.maze.BoardFactory;
import com.pacman.entity.maze.MapFactory;
import com.pacman.renderer.DefaultRenderer;

public class Main {

	public static void main(String[] args) throws Exception {
		String title = "--- PacMan ---";

		DefaultRenderer renderer = new DefaultRenderer();
		BoardFactory boardFactory = new BoardFactory(new MapFactory(),
				new BlockFactory());
		PacManFactory pacManFactory = new PacManFactory(new AnimationFactory());

		PacManGame pacManGame = new PacManGame(title, pacManFactory,
				boardFactory, renderer);

		AppGameContainer container = new AppGameContainer(pacManGame);
		container.setDisplayMode(700, 800, false);

		container.start();
	}

}
