package Concurrent;

public interface ProducerAndConsumer_concurrent_interface<T> {
	public T get();
	public void put(T item);
}
