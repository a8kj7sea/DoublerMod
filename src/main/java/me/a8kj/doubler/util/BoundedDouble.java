package me.a8kj.doubler.util;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import java.math.BigDecimal;

@Getter
public class BoundedDouble {
	private final String name;

	@Setter(AccessLevel.NONE)
	private double value;

	private final double min;
	private final double max;

	public BoundedDouble(String name, double value, double min, double max) {
		this.name = name;
		this.value = clamp(value, min, max);
		this.min = min;
		this.max = max;
	}

	public void setValue(double value) {
		this.value = clamp(value, min, max);
	}

	public double getValue() {
		return round(value, 2);
	}

	private double clamp(double val, double min, double max) {
		return Math.max(min, Math.min(max, val));
	}

	private double round(double val, int places) {
		BigDecimal bd = BigDecimal.valueOf(val);
		bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
}
