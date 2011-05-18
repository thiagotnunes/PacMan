package com.pacman.game;

import org.newdawn.slick.AppGameContainer;

public class Main {

	public static void main(String[] args) throws Exception {
		AppGameContainer container = new AppGameContainer(new PacManApp("--- PacMan ---"));
		container.setDisplayMode(700, 800, false);
		
		container.start();
	}

}
