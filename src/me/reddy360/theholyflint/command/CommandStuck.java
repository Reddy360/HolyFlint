package me.reddy360.theholyflint.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStuck implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String string,
			String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			player.teleport(player);
			return true;
		}
		sender.sendMessage(ChatColor.AQUA + "Nope.");
		return true;
	}

}
