package com.pacman.game;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.pacman.entity.character.PacMan;
import com.pacman.entity.character.PacManFactory;
import com.pacman.entity.character.PacManKeyListenerFactory;
import com.pacman.entity.maze.Board;
import com.pacman.entity.maze.BoardFactory;
import com.pacman.geometry.CollisionPolygon;

public class PacManGame extends BasicGame {

	protected static final String MAP_PATH = "data/maze/1/complete.tmx";

	private PacMan pacMan;
	private Board board;
	private final PacManFactory pacManFactory;
	private final BoardFactory boardFactory;
	private final PacManKeyListenerFactory listenerFactory;

	public PacManGame(String title, PacManFactory pacManFactory,
			BoardFactory boardFactory,
			PacManKeyListenerFactory listenerFactory) {
		super(title);
		this.pacManFactory = pacManFactory;
		this.boardFactory = boardFactory;
		this.listenerFactory = listenerFactory;
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		board = boardFactory.from(MAP_PATH);
		pacMan = pacManFactory.from(
				new CollisionPolygon(324.1f, 574.1f, 26.85f), board);

		gc.getInput().addKeyListener(listenerFactory.from(pacMan));
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		pacMan.move();
		pacMan.eat(board);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		board.draw(g);
		pacMan.draw(g);
	}

}
