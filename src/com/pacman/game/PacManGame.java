package com.pacman.game;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.pacman.entity.character.Direction;
import com.pacman.entity.character.PacMan;
import com.pacman.entity.character.PacManFactory;
import com.pacman.entity.maze.Board;
import com.pacman.entity.maze.BoardFactory;
import com.pacman.geometry.SquarePolygon;
import com.pacman.renderer.Renderer;

public class PacManGame extends BasicGame {

	protected static final String MAP_PATH = "data/maze/1/complete.tmx";

	private PacMan pacMan;
	private Board board;
	private final PacManFactory pacManFactory;
	private final Renderer renderer;
	private final BoardFactory boardFactory;

	public PacManGame(String title, PacManFactory pacManFactory,
			BoardFactory boardFactory, Renderer renderer) {
		super(title);
		this.pacManFactory = pacManFactory;
		this.boardFactory = boardFactory;
		this.renderer = renderer;
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		pacMan = pacManFactory.create();
		board = boardFactory.from(MAP_PATH);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		updatePacMan(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		renderer.render(board, g);
		renderer.render(pacMan, g);
	}

	private void updatePacMan(GameContainer gc, int delta) {
		Direction currentDirection = pacMan.currentDirection();
		Direction nextDirection = currentDirection.next(gc.getInput());
		
		if (!movePacMan(nextDirection)) {
			movePacMan(currentDirection);
		}
	}

	private boolean movePacMan(Direction direction) {
		SquarePolygon collisionPolygon = pacMan.translate(PacMan.SPEED,
				direction);
		if (!board.isCollidingWith(collisionPolygon.getPolygon())) {
			pacMan.updateCollisionPolygon(collisionPolygon);
			pacMan.updateDirection(direction);
			return true;
		}
		return false;
	}

}
