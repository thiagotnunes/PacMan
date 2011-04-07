package com.pacman.entity.character;

import static com.pacman.entity.Direction.DOWN;
import static com.pacman.entity.Direction.LEFT;
import static com.pacman.entity.Direction.RIGHT;
import static com.pacman.entity.Direction.UP;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.pacman.entity.AnimationFactory;
import com.pacman.entity.Direction;

public class PacManFactory {

	private final AnimationFactory animationFactory;

	public PacManFactory(AnimationFactory animationFactory) {
		this.animationFactory = animationFactory;
	}

	public PacMan create() throws SlickException {
		Map<Direction, Animation> animationMap = new HashMap<Direction, Animation>();
		animationMap.put(DOWN, animationFactory.createFromPath(70, createPathsFrom(DOWN)));
		animationMap.put(UP, animationFactory.createFromPath(70, createPathsFrom(UP)));
		animationMap.put(LEFT, animationFactory.createFromPath(70, createPathsFrom(LEFT)));
		animationMap.put(RIGHT, animationFactory.createFromPath(70, createPathsFrom(RIGHT)));
		return new PacMan(new Point(100, 100), new Dimension(20, 20), animationMap, Direction.LEFT);
	}
	
	private String[] createPathsFrom(Direction direction) {
		String lowerDirection = direction.toString().toLowerCase();
		String prefix = "data" + File.separator + "pacman"
				+ File.separator + lowerDirection + File.separator + "Pacman_" + lowerDirection + "-";
		String extension = ".png";
		
		String[] paths = new String[4];
		for (int i=0; i<paths.length; i++) {
			paths[i] = prefix + i + extension;
		}
		return paths;
	}

}
