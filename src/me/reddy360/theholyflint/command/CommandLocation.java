package me.reddy360.theholyflint.command;

import me.reddy360.theholyflint.PluginMain;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLocation implements CommandExecutor {
	PluginMain pluginMain;
	public CommandLocation(PluginMain pluginMain) {
		this.pluginMain = pluginMain;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.DARK_RED + "You need to be a Player!");
			return true;
		}
		Player player = (Player) sender;
		if(!player.hasPermission(pluginMain.pluginManager.getPermission("thf.location"))){
			sender.sendMessage("<INSERT FFAEN HERE>");
			return true;
		}
		if(args.length == 1){
			if(args[0].equalsIgnoreCase("serverstatus")){
				String location = pluginMain.getLocation(player.getName());
				if(location == null){
					sender.sendMessage("No location set!");
					return true;
				}
				pluginMain.getConfig().set("Signs.ServerStatus", location);
				pluginMain.saveConfig();
				sender.sendMessage("Set serverstatus to " + location);
				pluginMain.serverStatusSigns = true;
				return true;
			}
		}
		sender.sendMessage("Usable Locations: ServerStatus");
		return true;
	}

}
