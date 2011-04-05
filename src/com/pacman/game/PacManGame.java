package com.pacman.game;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.pacman.entity.PositionedImage;
import com.pacman.entity.character.PacMan;

public class PacManGame extends BasicGame {

	private final PacMan pacMan;

	public PacManGame(String title, PacMan pacMan, Dimension dimension) {
		super(title);
		this.pacMan = pacMan;
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		pacMan.setImage(new PositionedImage(new Point(100, 100), new Dimension(
				20, 20), new Image("data/pacman.png")));
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		pacMan.updateDirectionIfRequested(gc.getInput());
		pacMan.move(delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		pacMan.draw();
	}

}
