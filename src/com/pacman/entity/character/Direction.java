package com.pacman.entity.character;

import static org.newdawn.slick.Input.KEY_DOWN;
import static org.newdawn.slick.Input.KEY_LEFT;
import static org.newdawn.slick.Input.KEY_RIGHT;
import static org.newdawn.slick.Input.KEY_UP;

import org.lwjgl.util.Point;
import org.newdawn.slick.Input;

public enum Direction {
	UP {
		@Override
		public void movePoint(Point p, int delta) {
			p.translate(0, -delta);
		}
	}, DOWN {
		@Override
		public void movePoint(Point p, int delta) {
			p.translate(0, delta);
		}
	}, RIGHT {
		@Override
		public void movePoint(Point p, int delta) {
			p.translate(delta, 0);
		}
	}, LEFT {
		@Override
		public void movePoint(Point p, int delta) {
			p.translate(-delta, 0);
		}
	};

	public abstract void movePoint(Point p, int delta);
	
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
