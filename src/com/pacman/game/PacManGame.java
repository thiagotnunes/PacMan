package com.pacman.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.pacman.entity.character.PacMan;
import com.pacman.entity.maze.Board;

public class PacManGame {

	private PacMan pacMan;
	private Board board;

	public PacManGame(PacMan pacMan,
			Board board) {
		this.pacMan = pacMan;
		this.board = board;
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		pacMan.move();
		pacMan.eat(board);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		board.draw(g);
		pacMan.draw(g);
	}

}
