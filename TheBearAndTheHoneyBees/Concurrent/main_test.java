package Concurrent;

public class main_test {
	public static void main(String[] args) {
		BeesConcurrentInterface honey_pot = new BeesConcurrent();

		new Thread(() -> {
			honey_pot.bear();
		}).start();

		new Thread(() -> {
			honey_pot.bee();
		}).start();

		new Thread(() -> {
			honey_pot.bee();
		}).start();

		new Thread(() -> {
			honey_pot.bee();
		}).start();
	}
}
