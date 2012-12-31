package me.reddy360.theholyflint;

import net.minecraft.server.v1_4_6.Block;
import net.minecraft.server.v1_4_6.Item;
import net.minecraft.server.v1_4_6.ItemSign;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TradingHandler {
	public static Inventory getInventory(Player trader, Player otherPlayer){
		ItemStack air = new ItemStack(0, 0);
		ItemStack stick = new ItemStack(Item.STICK.id, 1);
		ItemStack butter = new ItemStack(Block.GOLD_BLOCK.id);
		ItemMeta metaButter = butter.getItemMeta();
		metaButter.setDisplayName(ChatColor.LIGHT_PURPLE + "You're trading on this side.");
		butter.setItemMeta(metaButter);
		ItemStack sign = new ItemStack(ItemSign.SIGN.id , 1);
		ItemMeta signMeta = sign.getItemMeta();
		signMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "You're trading with " + otherPlayer.getName() + ".");
		sign.setItemMeta(signMeta);
		ItemStack iron = new ItemStack(Block.IRON_BLOCK.id, 1);
		ItemMeta ironMeta = iron.getItemMeta();
		ironMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "The other persons trade items.");
		iron.setItemMeta(ironMeta);
		ItemStack trade = new ItemStack(Item.FLINT.id, 16);
		ItemStack otherTrade = new ItemStack(Block.SANDSTONE.id, 64);
		ItemStack canTrade = new ItemStack(Block.BEDROCK.id, 1);
		ItemMeta canTradeMeta = canTrade.getItemMeta();
		canTradeMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Cannot make trade.");
		canTrade.setItemMeta(canTradeMeta);
		ItemStack confirm = new ItemStack(Block.WOOL.id, 1);
		confirm.setDurability((short) 5);
		ItemMeta confirmMeta = confirm.getItemMeta();
		confirmMeta.setDisplayName(ChatColor.GREEN + "Accept trade?");
		confirm.setItemMeta(confirmMeta);
		ItemStack rejectTrade = new ItemStack(Block.WOOL.id, 1);
		rejectTrade.setDurability((short) 14);
		ItemMeta rejectTradeMeta = rejectTrade.getItemMeta();
		rejectTradeMeta.setDisplayName(ChatColor.RED + "Reject trade?");
		rejectTrade.setItemMeta(rejectTradeMeta);
		ItemStack partner = new ItemStack(Block.WOOL.id, 1);
		ItemMeta partnerMeta = partner.getItemMeta();
		partnerMeta.setDisplayName(ChatColor.DARK_AQUA + "Your partner has not decided.");
		partner.setItemMeta(partnerMeta);
		ItemStack[] contents = {stick, air, butter, air, sign, air, iron, air, stick,
				stick, stick, stick, stick, stick, stick, stick, stick, stick,
				stick, air, trade, air, stick, air, otherTrade, air, stick,
				stick, air, air, air, stick, air, air, air, stick,
				stick, stick, stick, stick, canTrade, stick, stick, stick, stick,
				stick, confirm, air, rejectTrade, stick, air, partner, air, stick};
		Inventory tradeScreen = Bukkit.createInventory(trader, contents.length, "Trading");
		tradeScreen.setContents(contents);
		return tradeScreen;
	}
}
