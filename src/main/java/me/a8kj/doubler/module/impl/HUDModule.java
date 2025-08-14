package me.a8kj.doubler.module.impl;

import lombok.RequiredArgsConstructor;
import me.a8kj.doubler.module.ModuleBase;
import me.a8kj.doubler.util.CPSCounter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@RequiredArgsConstructor
public class HUDModule extends ModuleBase {

	private final CPSCounter cpsCounter;

	@Override
	public String getName() {
		return "HUD";
	}

	@Override
	public String getDescription() {
		return "Displays a DoublerMod CPS HUD on the screen.";
	}

	@SubscribeEvent
	public void onRenderHUD(RenderGameOverlayEvent.Post event) {
		if (event.type != RenderGameOverlayEvent.ElementType.ALL) {
			return;
		}

		Minecraft mc = Minecraft.getMinecraft();
		String cpsText = "DM CPS: " + cpsCounter.getCPS();
		String madeByText = "Made by a8kj7sea";

		int cpsTextWidth = mc.fontRendererObj.getStringWidth(cpsText);
		int madeByTextWidth = mc.fontRendererObj.getStringWidth(madeByText);
		int textWidth = Math.max(cpsTextWidth, madeByTextWidth);

		int xPos = 347;
		int yPos = 8;

		drawBackgroundBox(xPos, yPos, textWidth);
		renderCpsText(xPos, yPos, cpsText);
		renderMadeByText(xPos, yPos, madeByText);
	}

	private void renderCpsText(int xPos, int yPos, String text) {
		Minecraft mc = Minecraft.getMinecraft();
		float hue = (System.currentTimeMillis() % 10000L) / 10000.0f;
		int color = java.awt.Color.HSBtoRGB(hue, 1.0f, 1.0f);
		int textXPos = xPos + 3;
		int textYPos = yPos + 2;
		mc.fontRendererObj.drawString(text, textXPos, textYPos, color);
	}

	private void renderMadeByText(int xPos, int yPos, String text) {
		Minecraft mc = Minecraft.getMinecraft();
		float hue = (System.currentTimeMillis() % 10000L) / 10000.0f;
		int color = java.awt.Color.HSBtoRGB(hue, 1.0f, 1.0f);
		int textXPos = xPos + 3;
		int textYPos = yPos + 2 + mc.fontRendererObj.FONT_HEIGHT + 2;
		mc.fontRendererObj.drawString(text, textXPos, textYPos, color);
	}

	private void drawBackgroundBox(int xPos, int yPos, int textWidth) {
		int boxWidth = textWidth + 6;
		int boxHeight = (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * 2) + 6;
		Gui.drawRect(xPos, yPos, xPos + boxWidth, yPos + boxHeight, 0x90000000);
	}
}