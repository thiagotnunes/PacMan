package com.pacman.entity.character;

import static org.newdawn.slick.Input.*;

import org.newdawn.slick.Input;

import com.pacman.geometry.SquarePolygon;

public enum Direction {
	UP {
		@Override
		public SquarePolygon move(SquarePolygon p, int delta) {
			return p.translate(0, -delta);
		}
	}, DOWN {
		@Override
		public SquarePolygon move(SquarePolygon p, int delta) {
			return p.translate(0, delta);
		}
	}, RIGHT {
		@Override
		public SquarePolygon move(SquarePolygon p, int delta) {
			return p.translate(delta, 0);
		}
	}, LEFT {
		@Override
		public SquarePolygon move(SquarePolygon p, int delta) {
			return p.translate(-delta, 0);
		}
	};

	public abstract SquarePolygon move(SquarePolygon p, int delta);
	
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
