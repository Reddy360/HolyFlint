package me.reddy360.theholyflint.command;

import me.reddy360.theholyflint.PluginMain;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kitteh.tag.TagAPI;

import de.bananaco.bpermissions.api.ApiLayer;
import de.bananaco.bpermissions.api.util.CalculableType;

public class CommandDuty implements CommandExecutor {
	PluginMain pluginMain;
	public CommandDuty(PluginMain pluginMain) {
		this.pluginMain = pluginMain;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!sender.hasPermission(pluginMain.pluginManager.getPermission("thf.duty"))){
			sender.sendMessage(ChatColor.DARK_RED + "You do not have permssion!");
			return true;
		}
		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.DARK_RED + "You are a console! WTF?");
			return true;
		}
		Player player = (Player) sender;
		if(ApiLayer.getGroups("world", CalculableType.USER, player.getName())[0].equalsIgnoreCase("Admin")){
			ApiLayer.removeGroup("world", CalculableType.USER, player.getName(), "Admin");
			ApiLayer.addGroup("world", CalculableType.USER, player.getName(), "AdminOffDuty");
			player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "You are now off duty!");
		}else if (ApiLayer.getGroups("world", CalculableType.USER, player.getName())[0].equalsIgnoreCase("AdminOffDuty")){
			ApiLayer.removeGroup("world", CalculableType.USER, player.getName(), "AdminOffDuty");
			ApiLayer.addGroup("world", CalculableType.USER, player.getName(), "Admin");
			player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "You are now on duty!");
		}
		TagAPI.refreshPlayer(player);
		return true;
	}

}
