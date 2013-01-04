package me.reddy360.theholyflint.command;

import me.reddy360.theholyflint.PluginMain;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandGetMsg implements CommandExecutor {
	PluginMain pluginMain;
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		if(pluginMain.getConfig().contains("Messages." + arg3[1])){
			arg0.sendMessage(ChatColor.GRAY + "[THF] " + ChatColor.RESET + pluginMain.getConfig().getString("Messages." + arg3[1]));
		return true;
		}else
			arg0.sendMessage(ChatColor.GRAY + "No message found.");
		return true;
	}
}
