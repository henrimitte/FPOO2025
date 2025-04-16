package semaphore.light.e27;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import semaphore.util.TurnOnOff;

class E27LightBulbTest {

	@Test
	void shouldTurnOn() {
		TurnOnOff light = new E27LightBulb();

		light.turnOff();
		light.turnOn();

		assertTrue(light.isOn());
	}

	@Test
	void shouldTurnOff() {
		TurnOnOff light = new E27LightBulb();

		light.turnOn();
		light.turnOff();

		assertTrue(light.isOff());
	}
}