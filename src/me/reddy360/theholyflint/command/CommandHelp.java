package me.reddy360.theholyflint.command;

import java.util.ArrayList;
import java.util.List;

import me.reddy360.theholyflint.PluginMain;
import net.minecraft.server.v1_4_6.Item;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import de.bananaco.bpermissions.api.ApiLayer;
import de.bananaco.bpermissions.api.util.CalculableType;

public class CommandHelp implements CommandExecutor {

	PluginMain pluginMain;
	public CommandHelp(PluginMain pluginMain) {
		this.pluginMain = pluginMain;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.DARK_RED + "You cannot get the help book!");
			return true;
		}
		ItemStack book = new ItemStack(Item.WRITTEN_BOOK.id, 1);
		BookMeta bookMeta = (BookMeta) book.getItemMeta();
		bookMeta.setAuthor(ChatColor.GOLD + "The Holy Flint");
		bookMeta.setPages(getCommandBook(sender.getName()));
		bookMeta.setTitle(getBookTitle(sender.getName()));
		book.setItemMeta(bookMeta);
		((Player)sender).getInventory().addItem(book);
		return true;
	}
	private String getBookTitle(String name) {
		String[] groups = ApiLayer.getGroups("world", CalculableType.USER, name);
		if(groups.length == 0){
			return ChatColor.DARK_RED + "ERROR GROUP NOT FOUND";
		}
		return ChatColor.GRAY + "Help: " + groups[0];
	}
	private List<String> getCommandBook(String name) {
		String[] groups = ApiLayer.getGroups("world", CalculableType.USER, name);
		if(groups.length == 0){
			return new ArrayList<String>();
		}
		return pluginMain.getConfig().getStringList("help." + groups[0]);
	}

}
