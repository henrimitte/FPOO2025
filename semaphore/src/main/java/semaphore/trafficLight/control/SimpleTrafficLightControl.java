package semaphore.trafficLight.control;

import java.util.Timer;
import java.util.TimerTask;

import semaphore.trafficLight.TrafficLight;
import semaphore.util.TurnOnOff;

public class SimpleTrafficLightControl implements TrafficLightControl {

	final TrafficLight trafficLight;
	State state = State.OFF;
	private Timer timer;

	public SimpleTrafficLightControl(TrafficLight trafficLight) {
		this.trafficLight = trafficLight;
	}

	@Override
	public void turnAlert() {
		TurnOnOff yellow = this.trafficLight.spotYellow();

		this.turnOff();

		this.state = State.ALERT;

		configureAlert(yellow, 1_000);
	}

	@Override
	public void turnGreen() {
		this.turnOff();
		this.trafficLight.spotGreen().turnOn();

		this.state = State.GREEN;
	}

	@Override
	public void turnYellow() {
		this.turnOff();
		this.trafficLight.spotYellow().turnOn();

		this.state = State.YELLOW;
	}

	@Override
	public void turnRed() {
		this.turnOff();
		this.trafficLight.spotRed().turnOn();

		this.state = State.RED;
	}

	@Override
	public void turnOff() {

		if (this.state == State.ALERT)
			this.stopAlert();

		this.trafficLight.spotGreen().turnOff();
		this.trafficLight.spotYellow().turnOff();
		this.trafficLight.spotRed().turnOff();

		this.state = State.OFF;
	}

	private void configureAlert(TurnOnOff spot, long delayMillis) {

		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				if (spot.isOff())
					spot.turnOn();
				else
					spot.turnOff();
			}
		};

		timer = new Timer();
		this.timer.scheduleAtFixedRate(task, 0, delayMillis);
	}

	private void stopAlert() {
		if (this.state == State.ALERT)
			this.timer.cancel();
	}
}