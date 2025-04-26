package semaphore.util.gui;

import java.awt.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

public class MyWindow {
	private JFrame frame = new JFrame();
	private MyPanel panel = new MyPanel();

	private void configureRepaint() {
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				SwingUtilities.invokeLater(() -> panel.repaint());
			}
		};

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 100);
	}

	public MyWindow() {
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		configureRepaint();
	}

	public void add(Paintable paintable) {
		this.panel.add(paintable);
	}

	public void remove(Paintable paintable) {
		this.panel.remove(paintable);
	}
}

class MyPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	Set<Paintable> paintables = new HashSet<>();

	public void add(Paintable paintable) {
		paintables.add(paintable);
	}

	public void remove(Paintable paintable) {
		paintables.remove(paintable);
	}

	@Override
	public void paint(Graphics g) {
		synchronized (g) {
			for (Paintable paintable : paintables) {
				paintable.paint(g);
			}
		}
	}
}