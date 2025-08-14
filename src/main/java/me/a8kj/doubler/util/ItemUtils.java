package me.a8kj.doubler.util;

import lombok.experimental.UtilityClass;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemSword;

@UtilityClass
public class ItemUtils {

	public static boolean isWeapon(Item item) {
		return item instanceof ItemSword || item instanceof ItemAxe || item == Items.bow;
	}
}
