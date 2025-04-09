package semaphore.light;

public class AbstractLight implements Light {

	private OnOff state = OnOff.OFF;

	@Override
	public void turnOn() {
		this.state = OnOff.ON;
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
}