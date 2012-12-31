package me.reddy360.theholyflint.command;

import me.reddy360.theholyflint.PluginMain;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandSetMsg implements CommandExecutor {
	PluginMain pluginMain;
	public CommandSetMsg(PluginMain pluginMain) {
		this.pluginMain = pluginMain;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!sender.hasPermission(pluginMain.pluginManager.getPermission("thf.setmsg"))){
			sender.sendMessage(ChatColor.DARK_RED + "You don't have permission!");
			return true;
		}
		if(args.length > 2){
			return false;
		}
		pluginMain.getConfig().set("Messages." + args[0], convert(args));
		return true;
	}
	private String convert(String[] args) {
		String returnValue = "";
		for(int x = 1; x < args.length; x++){
			returnValue = returnValue + args[x] + " ";
		}
		return returnValue;
	}

}
