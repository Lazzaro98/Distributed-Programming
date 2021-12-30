package Concurrent;

public class main_test_concurrent {
	public static void main(String[] args) {
		Pot pot = new Pot(5, 10);
		
		new Thread(() -> {
			pot.cook();
		}).start();
		new Thread(() -> {
			pot.cook();
		}).start();
		new Thread(() -> {
			pot.cook();
		}).start();
		new Thread(() -> {
			pot.eat(2);
		}).start();
		new Thread(() -> {
			pot.eat(3);
		}).start();
		new Thread(() -> {
			pot.eat(4);
		}).start();
		new Thread(() -> {
			pot.eat(1);
		}).start();
		new Thread(() -> {
			pot.eat(2);
		}).start();
		new Thread(() -> {
			pot.eat(2);
		}).start();
		
		
	}
}
