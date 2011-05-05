package com.pacman.entity.direction;

import static org.newdawn.slick.Input.*;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;

import com.pacman.graphics.AnimationFactory;

public class DirectionBuilder {

	private Map<Integer, Direction> directions;
	private Direction defaultDirection;

	public DirectionBuilder(Direction defaultDirection, Map<Integer, Direction> directions) {
		this.defaultDirection = defaultDirection;
		this.directions = directions;
	}
	
	public DirectionBuilder() {
	}
	
	public void buildDirections() throws SlickException {
		AnimationFactory animationFactory = new AnimationFactory();
		defaultDirection = new Left(animationFactory);
		directions = new HashMap<Integer, Direction>();
		directions.put(KEY_DOWN, new Down(animationFactory));
		directions.put(KEY_UP, new Up(animationFactory));
		directions.put(KEY_RIGHT, new Right(animationFactory));
		directions.put(KEY_LEFT, defaultDirection);
	}

	public Direction from(Integer key) {
		return directions.get(key);
	}

	public Direction defaultDirection() {
		return defaultDirection;
	}

}
