package semaphore.trafficLight.control;

import java.util.Timer;
import java.util.TimerTask;

import semaphore.trafficLight.TrafficLight;
import semaphore.util.TurnOnOff;

public class SimpleTrafficLightControl implements TrafficLightControl {

	private final TrafficLight trafficLight;
	private final TurnOnOff green, yellow, red;

	private State state = State.OFF;

	private Timer timer = null;

	public SimpleTrafficLightControl(TrafficLight trafficLight) {
		this.trafficLight = trafficLight;

		this.green = trafficLight.spotGreen();
		this.yellow = trafficLight.spotYellow();
		this.red = trafficLight.spotRed();
	}

	@Override
	public void turnAlert() {

		if (this.state == State.ALERT)
			return;

		this.reset();
		this.configureAlert(this.yellow, 1_000);
		this.state = State.ALERT;
	}

	@Override
	public void turnGreen() {
		this.reset();
		this.green.turnOn();
		this.state = State.GREEN;
	}

	@Override
	public void turnYellow() {
		this.reset();
		this.yellow.turnOn();
		this.state = State.YELLOW;
	}

	@Override
	public void turnRed() {
		this.reset();
		this.red.turnOn();
		this.state = State.RED;
	}

	@Override
	public void turnOff() {
		this.reset();
	}

	private void configureAlert(TurnOnOff spot, long delayMillis) {

		this.reset();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				if (spot.isOff())
					spot.turnOn();
				else
					spot.turnOff();
			}
		};

		this.timer = new Timer();
		this.timer.scheduleAtFixedRate(task, 0, delayMillis);
	}

	private void stopAlert() {
		if (this.timer != null)
			this.timer.cancel();

		this.yellow.turnOff();
	}

	private void reset() {

		if (this.state == State.ALERT)
			this.stopAlert();

		this.green.turnOff();
		this.yellow.turnOff();
		this.red.turnOff();

		this.state = State.OFF;
	}
}