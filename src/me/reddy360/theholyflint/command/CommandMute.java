package me.reddy360.theholyflint.command;

import me.reddy360.theholyflint.PluginMain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMute implements CommandExecutor {
	PluginMain pluginMain;
	public CommandMute(PluginMain pluginMain) {
		this.pluginMain = pluginMain;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!sender.hasPermission(pluginMain.pluginManager.getPermission("thf.mute"))){
			sender.sendMessage(ChatColor.DARK_RED + "You do not have permission! I am going to haunt you from " +
					"beyond the grave!");
			return true;
		}
		if(args.length == 1){
			Player player = Bukkit.getPlayer(args[0]);
			if(player == null || !player.isOnline()){
				sender.sendMessage("OH NOES! Hes not online!");
				return true;
			}
			if(player.hasPermission(pluginMain.pluginManager.getPermission("thf.mute.exempt"))){
				sender.sendMessage(ChatColor.RED + "You can't mute this player!");
				return true;
			}
			if(pluginMain.mutedPlayers.contains(player.getName())){
				pluginMain.mutedPlayers.remove(player.getName());
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + player.getName() + ChatColor.GREEN + " has been unmuted!");
			}else{
				pluginMain.mutedPlayers.add(player.getName());
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + player.getName() + ChatColor.GREEN + " has been muted!");
			}
		}else{
			sender.sendMessage(ChatColor.DARK_RED + "Invalid Arguments! /mute [player]");
		}
		
		return true;
	}

}
