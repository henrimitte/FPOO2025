package semaphore.app;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import semaphore.control.OneWaySemaphoreControl;
import semaphore.control.simple.SemaphoreControl;
import semaphore.trafficLight.control.SimpleTrafficLightControl;
import semaphore.trafficLight.control.TrafficLightControl;
import semaphore.trafficLight.simple.SimpleTrafficLight;
import semaphore.util.gui.MyWindow;

public class AppOneWayTwoSemaphoreControlTest {

	private MyWindow window = new MyWindow();
	private SemaphoreControl semaphoreControl;

	public AppOneWayTwoSemaphoreControlTest() throws IOException {

		this.create();
		this.semaphoreControl.setAlertPeriod(LocalTime.now(), LocalTime.now().plusSeconds(30));
		this.semaphoreControl.turnOn();
	}

	private TrafficLightControl createTrafficLightControl(Point position, Dimension dimension) throws IOException {

		SimpleTrafficLight trafficLight = new SimpleTrafficLight(position, dimension);
		window.add(trafficLight);

		return (new SimpleTrafficLightControl(trafficLight));
	}

	private void create() throws IOException {
		List<TrafficLightControl> list = new ArrayList<>();
		list.add(createTrafficLightControl(new Point(100, 100), new Dimension(70, 180)));
		list.add(createTrafficLightControl(new Point(300, 100), new Dimension(70, 180)));

		this.semaphoreControl = new OneWaySemaphoreControl(list);
	}

	public static void main(String[] args) throws IOException {
		new AppOneWayTwoSemaphoreControlTest();
	}
}
