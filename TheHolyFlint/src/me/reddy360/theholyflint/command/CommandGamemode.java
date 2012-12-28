package me.reddy360.theholyflint.command;

import me.reddy360.theholyflint.PluginMain;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGamemode implements CommandExecutor {
	PluginMain pluginMain;
	public CommandGamemode(PluginMain pluginMain) {
		this.pluginMain = pluginMain;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		if(!sender.hasPermission(pluginMain.pluginManager.getPermission("thf.gamemode"))){
			sender.sendMessage(ChatColor.DARK_RED + "You don't have permission!");
			return true;
		}
		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.DARK_RED + "YOUR NOT ALIVE");
		}
		Player player = (Player) sender;
		if(player.getGameMode() == GameMode.CREATIVE){
			player.setGameMode(GameMode.SURVIVAL);
		}else{
			player.setGameMode(GameMode.CREATIVE);
		}
		return true;
	}

}
