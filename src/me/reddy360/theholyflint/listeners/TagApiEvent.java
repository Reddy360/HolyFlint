package me.reddy360.theholyflint.listeners;

import me.reddy360.theholyflint.PluginMain;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

import de.bananaco.bpermissions.api.ApiLayer;
import de.bananaco.bpermissions.api.util.CalculableType;

public class TagApiEvent implements Listener {
	PluginMain pluginMain;
	@EventHandler
	public void onTagApi(PlayerReceiveNameTagEvent event){
		Player player = event.getNamedPlayer();
		
		String[] groups = ApiLayer.getGroups("world", CalculableType.USER, player.getName());
		if(groups.length == 0){
			return;
		}
		if(groups[0].equalsIgnoreCase("New")){
			return;
		}else if(groups[0].equalsIgnoreCase("Member")){
			event.setTag(ChatColor.DARK_PURPLE + player.getName());
		}else if(groups[0].equalsIgnoreCase("VIP")){
			event.setTag(ChatColor.GOLD + player.getName());
		}else if(groups[0].equalsIgnoreCase("Admin")){
			event.setTag(ChatColor.AQUA + player.getName());
		}else if(groups[0].equalsIgnoreCase("Adminoffduty")){
			event.setTag(ChatColor.BLUE + player.getName());
		}
	}
}
