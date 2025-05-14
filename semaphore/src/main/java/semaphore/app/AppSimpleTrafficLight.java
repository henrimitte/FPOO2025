package semaphore.app;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import semaphore.trafficLight.control.SimpleTrafficLightControl;
import semaphore.trafficLight.simple.SimpleTrafficLight;
import semaphore.util.gui.MyWindow;

public class AppSimpleTrafficLight {

	public static void main(String[] args) throws IOException {

		SimpleTrafficLight defaultOne = new SimpleTrafficLight();
		SimpleTrafficLightControl defaultControl = new SimpleTrafficLightControl(defaultOne);

		Point position = new Point(100, 100);
		Dimension dimension = new Dimension(130, 350);
		SimpleTrafficLight customOne = new SimpleTrafficLight(position, dimension);
		SimpleTrafficLightControl customControl = new SimpleTrafficLightControl(customOne);

		MyWindow window = new MyWindow();
		window.add(defaultOne);
		window.add(customOne);

		customControl.turnAlert();

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			private int i = 0;

			@Override
			public void run() {

				i++;
				if ((i % 3) == 1)
					defaultControl.turnGreen();

				if ((i % 3) == 2)
					defaultControl.turnYellow();

				if ((i % 3) == 0) {
					defaultControl.turnRed();
					i = 0;
				}
			}
		}, 0, 2_000);
	}
}
