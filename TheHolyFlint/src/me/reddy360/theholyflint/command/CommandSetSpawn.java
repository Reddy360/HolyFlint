package me.reddy360.theholyflint.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!sender.hasPermission(Bukkit.getPluginManager().getPermission("thf.setspawn"))){
			sender.sendMessage(ChatColor.DARK_RED + "WHAT YOU DOING?");
			return true;
		}
		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.DARK_RED + "You need to be a Player");
			return true;
		}
		Player player = (Player) sender;
		Location location = player.getLocation();
		player.getWorld().setSpawnLocation(location.getBlockX(), location.getBlockY() + 1, location.getBlockZ());
		return false;
	}

}
