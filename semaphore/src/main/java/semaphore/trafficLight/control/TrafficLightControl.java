package semaphore.trafficLight.control;

public interface TrafficLightControl {

	public static enum State {
		GREEN, YELLOW, RED, ALERT, OFF
	};

	void turnAlert();

	void turnGreen();

	void turnYellow();

	void turnRed();

	void turnOff();
}
