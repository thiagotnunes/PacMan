package com.pacman.entity.character;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.MovementBuilder;

public class PacManKeyListener implements KeyListener {

	protected final PacMan pacMan;
	protected final MovementBuilder movementBuilder;

	public PacManKeyListener(PacMan pacMan, MovementBuilder movementBuilder) {
		this.pacMan = pacMan;
		this.movementBuilder = movementBuilder;
	}

	@Override
	public void keyPressed(int key, char character) {
		Movement movement = movementBuilder.from(key);
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
