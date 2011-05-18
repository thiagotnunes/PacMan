package com.pacman.entity.character;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.MovementFactory;

public class PacManKeyListener implements KeyListener {

	protected final PacMan pacMan;
	protected final MovementFactory movementFactory;

	public PacManKeyListener(PacMan pacMan, MovementFactory movementFactory) {
		this.pacMan = pacMan;
		this.movementFactory = movementFactory;
	}

	@Override
	public void keyPressed(int key, char character) {
		Movement movement = movementFactory.from(key);
		if (isValid(movement)) {
			pacMan.updateDirection(movement);
		}
	}

	private boolean isValid(Movement movement) {
		return movement != null;
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
