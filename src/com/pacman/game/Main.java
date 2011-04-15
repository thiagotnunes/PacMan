package com.pacman.game;

import org.newdawn.slick.AppGameContainer;

import com.pacman.entity.AnimationFactory;
import com.pacman.entity.BoardFactory;
import com.pacman.entity.MapFactory;
import com.pacman.entity.character.PacManFactory;
import com.pacman.renderer.DefaultRenderer;

public class Main {

	public static void main(String[] args) throws Exception {
		String title = "--- PacMan ---";

		DefaultRenderer renderer = new DefaultRenderer();		
		BoardFactory boardFactory = new BoardFactory(new MapFactory());
		PacManFactory pacManFactory = new PacManFactory(new AnimationFactory());
		
		PacManGame pacManGame = new PacManGame(title, pacManFactory, boardFactory, renderer);

		AppGameContainer container = new AppGameContainer(pacManGame);
		container.setDisplayMode(800, 600, false);

		container.start();
	}

}
