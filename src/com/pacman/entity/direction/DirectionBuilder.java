package com.pacman.entity.direction;

import static org.newdawn.slick.Input.*;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.pacman.entity.character.AnimationFactory;

public class DirectionBuilder {

	private Map<Integer, Direction> directions;
	private final AnimationFactory animationFactory;

	public DirectionBuilder(AnimationFactory animationFactory) {
		this.animationFactory = animationFactory;
	}

	public void buildDirectionMap() throws SlickException {
		directions = new HashMap<Integer, Direction>();

		directions.put(KEY_DOWN, new Down(animationFactory));
		directions.put(KEY_UP, new Up(animationFactory));
		directions.put(KEY_LEFT, new Left(animationFactory));
		directions.put(KEY_RIGHT, new Right(animationFactory));
	}

	public Direction from(Input input) {
		for (Integer key : directions.keySet()) {
			if (input.isKeyDown(key)) {
				return directions.get(key);
			}
		}

		return new NullDirection();
	}

	public Direction defaultDirection() throws SlickException {
		return directions.get(KEY_LEFT);
	}

}
