package semaphore.app;

import java.io.IOException;
import java.time.LocalTime;

import semaphore.control.OneWaySemaphoreControl;
import semaphore.control.simple.SemaphoreControl;
import semaphore.trafficLight.control.SimpleTrafficLightControl;
import semaphore.trafficLight.control.TrafficLightControl;
import semaphore.trafficLight.simple.SimpleTrafficLight;
import semaphore.util.gui.MyWindow;

public class AppOneWaySimpleSemaphoreControlTest {

	private static void oneWaySimpleSemaphoreTest() throws IOException {

		SimpleTrafficLight defaultOne = new SimpleTrafficLight();
		TrafficLightControl trafficLightControl = new SimpleTrafficLightControl(defaultOne);
		SemaphoreControl semaphoreControl = new OneWaySemaphoreControl(trafficLightControl);

		semaphoreControl.setAlertPeriod(LocalTime.now(), LocalTime.now().plusSeconds(30));
		semaphoreControl.turnOn();

		MyWindow window = new MyWindow();
		window.add(defaultOne);
	}

	public static void main(String[] args) throws IOException {

		oneWaySimpleSemaphoreTest();
	}
}
