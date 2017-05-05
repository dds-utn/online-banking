package banking;
import java.time.Clock;

public class GlobalClock {
	
	public static Clock clock;

	static {
		reset();
	}

	public static void reset() { use(Clock.systemDefaultZone()); }

	public static void use(Clock clock) {
		GlobalClock.clock = clock;
	}

}
