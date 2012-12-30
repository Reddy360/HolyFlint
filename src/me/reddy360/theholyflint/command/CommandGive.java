package me.reddy360.theholyflint.command;

import java.util.HashMap;

import me.reddy360.theholyflint.PluginMain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandGive implements CommandExecutor {
	HashMap<String, Integer> items = new HashMap<String, Integer>();
	PluginMain pluginMain;
	public CommandGive(PluginMain pluginMain) {
		this.pluginMain = pluginMain;
		
		for(Material material : Material.values()){
			String name = material.toString().replaceAll("_", "").toLowerCase();
			int id = material.getId();
			items.put(name, id);
			items.put(String.valueOf(id), id);
		}
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!sender.hasPermission(pluginMain.pluginManager.getPermission("thf.give"))){
			sender.sendMessage(ChatColor.DARK_RED + "You can't give items!");
			return true;
		}
		if(args.length == 2){
			give(args[1], 64, args[0], sender);
		}else if(args.length == 3){
			try{
				int amount = Integer.parseInt(args[2]);
				give(args[1], amount, args[0], sender);
			}catch(NumberFormatException e){
				sender.sendMessage(ChatColor.DARK_RED + "Number is not a number or is too high!");
				return true;
			}
			
		}
		return true;
	}
	
	private void give(String name, int amount, String playerName, CommandSender sender){
		Player player = Bukkit.getPlayer(playerName);
		if(!player.getName().equals(sender.getName())){
			if(!sender.hasPermission(pluginMain.pluginManager.getPermission("thf.give.others"))){
				sender.sendMessage(ChatColor.DARK_RED + "You can't give items to other people!");
				return;
			}
		}
		String[] itemName = name.split(":");
		name = itemName[0];
		if(!items.containsKey(name.toLowerCase())){
			sender.sendMessage(ChatColor.DARK_RED + "Cannot find item " + name);
			return;
		}
		int id = items.get(name.toLowerCase());
		ItemStack give = new ItemStack(id, amount);
		if(itemName.length == 2){
			try{
				short durability = Short.parseShort(itemName[1]);
				give.setDurability(durability);
			}catch(NumberFormatException e){
				
			}
			
			
		}
		player.getInventory().addItem(give);
		player.sendMessage("[THF] You were given " + amount + " " + name + "(s)");
		sender.sendMessage("[THF] Spawned item(s) for " + player.getName());
	}

}
