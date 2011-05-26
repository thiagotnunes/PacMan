package com.pacman.entity.maze.consumable;

import org.newdawn.slick.Graphics;

import com.pacman.entity.collision.Collidable;
import com.pacman.entity.maze.tile.ImageTile;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.Drawable;
import com.pacman.sound.Sonorous;
import com.pacman.sound.SoundPlayer;

public class Food implements Drawable, Sonorous, Collidable {

	protected final ImageTile imageTile;
	protected final SoundPlayer soundPlayer;

	public Food(ImageTile imageTile, SoundPlayer soundPlayer) {
		this.imageTile = imageTile;
		this.soundPlayer = soundPlayer;
	}

	public void play() {
		soundPlayer.play();
	}

	@Override
	public void draw(Graphics g) {
		imageTile.draw(g);
	}

	@Override
	public boolean isCollidingWith(CollisionPolygon collisionPolygon) {
		return imageTile.isCollidingWith(collisionPolygon);
	}

}
