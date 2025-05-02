package semaphore.app;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import semaphore.light.e27.E27LightBulb;
import semaphore.spot.SpotLight;
import semaphore.util.gui.MyWindow;

public class AppSpotLightTest {

	public static void main(String[] args) throws IOException {
		AppSpotLightTest app = new AppSpotLightTest();
		app.go();
	}

	private SpotLight createSpotLight(String color,
									  int xLeft, int yTop,
									  int width, int height
									  ) throws IOException {
		String path = "/semaphore/trafficLight/simple/img/";
		URL url;

		url = getClass().getResource(path + color + "On.png");
		Image maskOn = ImageIO.read(url);

		url = getClass().getResource(path + color + "Off.png");
		Image maskOff = ImageIO.read(url);

		SpotLight spot = new SpotLight(maskOn, maskOff);
		spot.setPosition(xLeft, yTop);
		spot.setSize(width, height);

		return spot;
	}

	private void turnOnOff(SpotLight spot, int delayMillis) {
		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				if (spot.isOff())
					spot.turnOn();
				else
					spot.turnOff();

			}
		};

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, delayMillis);
	}

	private void go() throws IOException {
		MyWindow window = new MyWindow();
		SpotLight spot;

		spot = createSpotLight("yellow", 100, 100, 50, 50);
		spot.setLight(new E27LightBulb());
		window.add(spot);
		turnOnOff(spot, 2_000);

		spot = createSpotLight("red", 100, 150, 50, 50);
		spot.setLight(new E27LightBulb());
		window.add(spot);
		turnOnOff(spot, 2_000);

		spot = createSpotLight("green", 100, 200, 50, 50);
		spot.setLight(new E27LightBulb());
		window.add(spot);
		turnOnOff(spot, 2_000);
	}
}
