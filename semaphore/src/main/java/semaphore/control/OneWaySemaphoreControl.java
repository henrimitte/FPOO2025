package semaphore.control;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import semaphore.control.simple.SemaphoreControl;
import semaphore.trafficLight.control.TrafficLightControl;

public class OneWaySemaphoreControl implements SemaphoreControl {

	private List<TrafficLightControl> trafficLights = new ArrayList<>();

	private int greenMillis = 10_000;
	private int yellowMillis = 2_000;
	private int redMillis = 5_000;

	private LocalTime alertStart = LocalTime.of(0, 0);
	private LocalTime alertEnd = LocalTime.of(5, 30);

	private OnOff state = OnOff.OFF;

	public OneWaySemaphoreControl(List<TrafficLightControl> trafficLights) {
		this.trafficLights = trafficLights;
	}

	public OneWaySemaphoreControl(TrafficLightControl... trafficLights) {
		this(Arrays.asList(trafficLights));
	}

	@Override
	public void turnOn() {
		this.state = OnOff.ON;
		this.run();
	}

	@Override
	public void turnOff() {
		this.state = OnOff.OFF;
	}

	@Override
	public boolean isOn() {
		return this.state == OnOff.ON;
	}

	@Override
	public boolean isOff() {
		return this.state == OnOff.OFF;
	}

	@Override
	public void setGreenSeconds(int seconds) {
		this.greenMillis = seconds * 1_000;
	}

	@Override
	public void setYellowSeconds(int seconds) {
		this.yellowMillis = seconds * 1_000;
	}

	@Override
	public void setRedSeconds(int seconds) {
		this.redMillis = seconds * 1_000;
	}

	@Override
	public void setAlertPeriod(LocalTime start, LocalTime end) {
		this.alertStart = start;
		this.alertEnd = end;
	}

	private boolean isAlertPeriod() {

		boolean START_MIDNIGHT_END = this.alertStart.isAfter(this.alertEnd);

		LocalTime now = LocalTime.now();

		if (START_MIDNIGHT_END)
			return (now.isAfter(this.alertStart) || now.isBefore(this.alertEnd));

		return (now.isAfter(this.alertStart) && now.isBefore(this.alertEnd));
	}

	private void doAlert() throws InterruptedException {
		while (isAlertPeriod()) {

			this.trafficLights.forEach(e -> e.turnAlert());
			Thread.sleep(1_000);
		}
	}

	private void doYellowRedGreen() throws InterruptedException {

		this.trafficLights.forEach(e -> e.turnYellow());
		Thread.sleep(this.yellowMillis);

		this.trafficLights.forEach(e -> e.turnRed());
		Thread.sleep(this.redMillis);

		this.trafficLights.forEach(e -> e.turnGreen());
		Thread.sleep(this.greenMillis);
	}

	private void run() {

		Runnable runnable = () -> {

			while (this.state == OnOff.ON) {
				try {
					doAlert();
					doYellowRedGreen();
				} catch (InterruptedException exception) {

					this.trafficLights.forEach(e -> e.turnAlert());
				}
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}
}
