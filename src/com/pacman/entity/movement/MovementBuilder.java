package com.pacman.entity.movement;

import static org.newdawn.slick.Input.*;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;

import com.pacman.graphics.MovementAnimationFactory;
import com.pacman.graphics.StoppedAnimationFactory;

public class MovementBuilder {

	private Map<Integer, Movement> movements;
	private Map<Movement, Stopped> stoppedMovements;
	private Movement defaultDirection;
	private NullMovement nullMovement;

	protected MovementBuilder(Movement defaultDirection,
			Map<Integer, Movement> movements,
			Map<Movement, Stopped> stoppedMovements) {
		this();
		this.defaultDirection = defaultDirection;
		this.movements = movements;
		this.stoppedMovements = stoppedMovements;
	}
	
	public MovementBuilder() {
		this.nullMovement = new NullMovement();
	}
	
	public void buildMovements() throws SlickException {
		MovementAnimationFactory animationFactory = new MovementAnimationFactory();
		Left left = new Left(animationFactory);
		Down down = new Down(animationFactory);
		Up up = new Up(animationFactory);
		Right right = new Right(animationFactory);

		defaultDirection = left;
		
		buildMovements(left, down, up, right);
		
		buildStoppedMovements(left, down, up, right);
	}

	private void buildMovements(Left left, Down down, Up up, Right right) {
		movements = new HashMap<Integer, Movement>();
		movements.put(KEY_DOWN, down);
		movements.put(KEY_UP, up);
		movements.put(KEY_RIGHT, right);
		movements.put(KEY_LEFT, left);
	}

	private void buildStoppedMovements(Left left, Down down, Up up, Right right)
			throws SlickException {
		StoppedAnimationFactory stoppedFactory = new StoppedAnimationFactory();
		Stopped stoppedUp = new Stopped(stoppedFactory, up);
		Stopped stoppedDown = new Stopped(stoppedFactory, down);
		Stopped stoppedRight = new Stopped(stoppedFactory, right);
		Stopped stoppedLeft = new Stopped(stoppedFactory, left);
		stoppedMovements = new HashMap<Movement, Stopped>();
		stoppedMovements.put(up, stoppedUp);
		stoppedMovements.put(down, stoppedDown);
		stoppedMovements.put(right, stoppedRight);
		stoppedMovements.put(left, stoppedLeft);
		stoppedMovements.put(stoppedUp, stoppedUp);
		stoppedMovements.put(stoppedDown, stoppedDown);
		stoppedMovements.put(stoppedRight, stoppedRight);
		stoppedMovements.put(stoppedLeft, stoppedLeft);
	}

	public Movement from(Integer key) {
		return movements.get(key);
	}

	public Movement defaultMovement() {
		return defaultDirection;
	}

	public Stopped stoppedFrom(Movement movement) {
		return stoppedMovements.get(movement);
	}

	public NullMovement nullMovement() {
		return nullMovement;
	}

}
