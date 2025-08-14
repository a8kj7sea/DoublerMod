package me.a8kj.doubler.util;

public class CPSCounter {

	private int cpsCounter = 0;
	private long lastTime = System.currentTimeMillis();

	public void increment() {
		cpsCounter++;
	}

	public int getCPS() {
		long now = System.currentTimeMillis();
		if (now - lastTime >= 1000) {
			cpsCounter = 0;
			lastTime = now;
		}
		return cpsCounter;
	}
}
