package me.reddy360.theholyflint.command;

import java.util.ArrayList;
import java.util.List;

import me.reddy360.theholyflint.PluginMain;
import net.minecraft.server.v1_4_6.Item;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandFlamingDiscoStick implements CommandExecutor {
	PluginMain pluginMain;
	public CommandFlamingDiscoStick(PluginMain pluginMain) {
		this.pluginMain = pluginMain;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED + "Flamer!");
			return true;
		}else if(!(sender.hasPermission(pluginMain.pluginManager.getPermission("thf.flamingdiscostick")))){
			sender.sendMessage(ChatColor.DARK_RED + "NO.");
			return true;
		}else{
			ItemStack stick = new ItemStack(Item.STICK.id, 1);
			stick.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 817);
			List<String> lore = new ArrayList<String>();
			ItemMeta meta = stick.getItemMeta();
			lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "One insane disco stick. And its on fire.");
			meta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Flaming Disco Stick");
			meta.setLore(lore);
			stick.setItemMeta(meta);
			((Player)sender).getInventory().addItem(stick);
			sender.sendMessage(ChatColor.AQUA + "" + ChatColor.ITALIC + "Burning down the house! -Tom Jones");
			return true;
		}
	}

}

