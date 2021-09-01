package com.chad.engine.UI;

import java.awt.*;

public class Anchor {

	private Dimension startSize;
	private Point point;
	private boolean stretch;

	public Anchor(Dimension startSize, Point point, boolean stretch) {
		this.startSize = startSize;
		this.point = point;
		this.stretch = stretch;
	}

	public static Anchor topLeft(Dimension startSize) {
		return new Anchor(startSize, new Point(), false);
	}

	public Point getPoint(Dimension currentSize) {
		return stretch ? new Point(
			currentSize.width * point.x / startSize.width,
			currentSize.height * point.y / startSize.height
		) : point;
	}

}
