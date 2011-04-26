package com.pacman.entity.direction;

import static org.newdawn.slick.Input.*;

import org.newdawn.slick.Input;

public class DirectionFactory {

	public Direction from(Input input) {
		if (input.isKeyDown(KEY_DOWN)) {
			return new Down();
		} else if(input.isKeyDown(KEY_UP)) {
			return new Up();
		} else if (input.isKeyDown(KEY_LEFT)) {
			return new Left();
		} else if (input.isKeyDown(KEY_RIGHT)) {
			return new Right();
		}
		
		return new NullDirection();
	}

}
