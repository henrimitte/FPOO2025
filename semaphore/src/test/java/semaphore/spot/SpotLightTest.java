package semaphore.spot;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.jupiter.api.Test;

import semaphore.light.e27.AbstractLightE27;
import semaphore.light.e27.E27LightBulb;

class SpotLightTest {

	@Test
	void shouldTurnOn() {
		// given
		AbstractLightE27 light = new E27LightBulb();
		SpotLight spot = new SpotLight(null);
		spot.setLight(light);

		// do action
		light.turnOff();
		spot.turnOn();

		// check
		assertTrue(light.isOn());
		assertTrue(spot.isOn());
	}

	@Test
	void shouldTurnOff() {
		// given
		AbstractLightE27 light = new E27LightBulb();
		SpotLight spot = new SpotLight(null);
		spot.setLight(light);

		// do action
		light.turnOn();
		spot.turnOff();

		// check
		assertTrue(light.isOff());
		assertTrue(spot.isOff());
	}

	@Test
	void shouldReturnCloneOfGetPosition() {
		// given
		SpotLight spot = new SpotLight(null);
		spot.setLight(new E27LightBulb());
		final int xLeft = 20;
		final int yTop = 50;
		spot.setPosition(xLeft, yTop);

		// do action
		Point position = spot.getPosition();
		position.setLocation(new Point(30, 30));

		// check
		assertEquals(xLeft, spot.getPosition().getX());
		assertEquals(yTop, spot.getPosition().getY());
	}

	@Test
	void shouldReturnCloneOfDimension() {
		// given
		SpotLight spot = new SpotLight(null);
		spot.setLight(new E27LightBulb());
		final int width = 50;
		final int height = 20;
		spot.setDimension(new Dimension(width, height));

		// do action
		Dimension dimension = spot.getDimension();
		dimension.setSize(30, 30);

		// check
		assertEquals(width, spot.getDimension().getWidth());
		assertEquals(height, spot.getDimension().getHeight());
	}

}
