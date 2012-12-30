package me.reddy360.theholyflint.command;

import me.reddy360.theholyflint.PluginMain;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandUnban implements CommandExecutor {
	PluginMain pluginMain;
	public CommandUnban(PluginMain pluginMain) {
		this.pluginMain = pluginMain;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!sender.hasPermission(pluginMain.pluginManager.getPermission("thf.unban"))){
			sender.sendMessage("");
			return true;
		}
		if(args.length == 1){
			if(pluginMain.getConfig().contains("banned." + args[0])){
				pluginMain.getConfig().set("banned." + args[0], null);
				sender.sendMessage(ChatColor.GREEN + "Player " + args[0] + " has been unbanned!");
			}else{
				sender.sendMessage(ChatColor.GREEN + "Player " + args[0] + " is not banned!");
			}
		}
		return true;
	}

}
