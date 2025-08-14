package me.a8kj.doubler.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.lwjgl.input.Mouse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.a8kj.doubler.module.ModuleBase;
import me.a8kj.doubler.util.BoundedDouble;
import me.a8kj.doubler.util.CPSCounter;
import me.a8kj.doubler.util.ItemUtils;
import me.a8kj.doubler.util.Timer;

import java.awt.Robot;
import java.util.Random;

@RequiredArgsConstructor
public class AutoClickerModule extends ModuleBase {

	private final BoundedDouble maxCPS = new BoundedDouble("MaxCPS", 20.0, 11.0, 30.0);
	private final BoundedDouble minCPS = new BoundedDouble("MinCPS", 15.0, 3.0, 20.0);
	private final Timer timer = new Timer();
	private final Random random = new Random();

	@Getter
	private final CPSCounter cpsCounter;

	@Override
	public String getName() {
		return "AutoClicker";
	}

	@Override
	public String getDescription() {
		return "Automatically clicks for you.";
	}

	@SubscribeEvent
	public void onClientTick(ClientTickEvent event) throws Exception {
		if (shouldAutoClick()) {
			performAutoClick();
		}
	}

	private boolean shouldAutoClick() {
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.thePlayer == null || mc.theWorld == null || !mc.inGameHasFocus || !Mouse.isButtonDown(0)) {
			return false;
		}

		Item heldItem = mc.thePlayer.getCurrentEquippedItem() != null ? mc.thePlayer.getCurrentEquippedItem().getItem()
				: null;

		return ItemUtils.isWeapon(heldItem) && timer.hasReached(getDelay());
	}

	private void performAutoClick() throws Exception {
		Robot robot = new Robot();
		robot.mouseRelease(16);
		robot.mousePress(16);
		robot.mouseRelease(16);
		robot.mousePress(16);
		timer.reset();
		cpsCounter.increment();
	}

	private long getDelay() {
		double min = minCPS.getValue();
		double max = maxCPS.getValue();
		return (long) (Math.max(1, max + random.nextDouble() * (min - max)));
	}
}