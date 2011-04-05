package com.pacman.entity;

import org.newdawn.slick.Input;
import static org.newdawn.slick.Input.*;

public enum Direction {
	UP, DOWN, RIGHT, LEFT;

	public Direction fromInput(Input input) {
		if (input.isKeyDown(KEY_UP)) {
			return UP;
		} else if (input.isKeyDown(KEY_DOWN)) {
			return DOWN;
		} else if (input.isKeyDown(KEY_RIGHT)) {
			return RIGHT;
		} else if (input.isKeyDown(KEY_LEFT)) {
			return LEFT;
		}
		return this;
	}

}
