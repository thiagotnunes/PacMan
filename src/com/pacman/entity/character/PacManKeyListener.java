package com.pacman.entity.character;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import com.pacman.entity.direction.Direction;
import com.pacman.entity.direction.DirectionBuilder;

public class PacManKeyListener implements KeyListener {

	protected final PacMan pacMan;
	protected final DirectionBuilder directionBuilder;

	public PacManKeyListener(PacMan pacMan, DirectionBuilder directionBuilder) {
		this.pacMan = pacMan;
		this.directionBuilder = directionBuilder;
	}

	@Override
	public void keyPressed(int key, char character) {
		Direction direction = directionBuilder.from(key);
		if (isValid(direction)) {
			pacMan.updateDirection(direction);
		}
	}

	private boolean isValid(Direction direction) {
		return direction != null;
	}

	@Override
	public void keyReleased(int key, char character) {
	}

	@Override
	public void inputEnded() {
	}

	@Override
	public void inputStarted() {
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void setInput(Input input) {
	}

}
