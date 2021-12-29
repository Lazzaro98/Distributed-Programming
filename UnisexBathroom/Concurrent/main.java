package Concurrent;

public class main { // testing only conccurent part
	public static void main(String[] args) {
		UnisexBathroomConcurrentInterface bathroom = new UnisexBathroomConcurrent();
		new Thread(() -> {
			bathroom.man(0);
		}).start();
		new Thread(() -> {
			bathroom.man(1);
		}).start();
		new Thread(() -> {
			bathroom.man(2);
		}).start();
		
		
		new Thread(() -> {
			bathroom.woman(3);
		}).start();
		new Thread(() -> {
			bathroom.woman(4);
		}).start();
		new Thread(() -> {
			bathroom.woman(5);
		}).start();
	}
}
