package semaphore.light;

public interface Light {
	
	enum OnOff {ON, OFF};
	
	void turnOn();
	void turnOff();
	
	boolean isOn();
	boolean isOff();
}
