package me.reddy360.theholyflint.command;

import me.reddy360.theholyflint.PluginMain;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHelp implements CommandExecutor {

	PluginMain pluginMain;
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		sender.sendMessage(ChatColor.DARK_BLUE + "Nope.");
		return true;
	}

}
