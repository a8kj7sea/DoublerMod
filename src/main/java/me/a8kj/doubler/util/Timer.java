package me.a8kj.doubler.util;

public class Timer {
	private long previousMS = 0;

	public boolean hasReached(double ms) {
		return (System.currentTimeMillis() - previousMS) >= ms;
	}

	public void reset() {
		previousMS = System.currentTimeMillis();
	}
}
