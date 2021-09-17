package com.chad.engine.ui;

import com.chad.engine.Window;

import java.awt.Point;
import java.awt.Dimension;

@SuppressWarnings("unused")
public class Anchor {

	private final Dimension startSize;
	private final Point point;
	private final boolean stretch;

	public Anchor(Dimension startSize, Point point, boolean stretch) {
		this.startSize = startSize;
		this.point = point;
		this.stretch = stretch;
	}

	public static Anchor topLeft(Dimension startSize) {
		return new Anchor(startSize, new Point(), false);
	}

	public Point getPoint() {
		return stretch ? new Point(
				(int) Window.getWidth() * point.x / startSize.width,
				(int) Window.getHeight() * point.y / startSize.height
		) : point;
	}

}
