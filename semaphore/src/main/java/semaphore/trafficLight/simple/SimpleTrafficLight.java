package semaphore.trafficLight.simple;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;

import semaphore.spot.SpotLight;

public class SimpleTrafficLight {

	private Point position;
	private Dimension dimension;
	private Image mask;

	private SpotLight yellow;
	private SpotLight green;
	private SpotLight red;

	private void create() {
		// TO DO
	}

	public SimpleTrafficLight() {
		this.position = new Point(0, 0);
		this.dimension = new Dimension(40, 80);
	}

	public SimpleTrafficLight(Point position, Dimension dimension) {
		this.position = new Point(position);
		this.dimension = new Dimension(dimension);
	}

	public void setPosition(Point position) {
		this.position = new Point(position);
	}

	public Point getPosition() {
		return (Point) this.position.clone();
	}

	public void setDimension(Dimension dimension) {
		this.dimension = new Dimension(dimension);
	}

	public Dimension getDimension() {
		return (Dimension) this.dimension.clone();
	}
}