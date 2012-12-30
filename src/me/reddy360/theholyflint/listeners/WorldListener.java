package me.reddy360.theholyflint.listeners;

import me.reddy360.theholyflint.PluginMain;
import net.minecraft.server.v1_4_6.Item;

import org.bukkit.ChatColor;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

public class WorldListener implements Listener {
	PluginMain pluginMain;
	public WorldListener(PluginMain pluginMain) {
		this.pluginMain = pluginMain;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		Player player = e.getPlayer();
		if(e.isCancelled()){
			return;
		}
		if(!player.hasPermission(pluginMain.pluginManager.getPermission("thf.world.modify"))){
			player.sendMessage(ChatColor.DARK_RED + "You don't have permission to break blocks!");
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		Player player = e.getPlayer();
		if(e.isCancelled()){
			return;
		}
		if(!player.hasPermission(pluginMain.pluginManager.getPermission("thf.world.modify"))){
			player.sendMessage(ChatColor.DARK_RED + "You don't have permission to place blocks!");
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockDispense(BlockDispenseEvent e){
		if(e.isCancelled()){
			return;
		}
		Dispenser dispenser = (Dispenser) e.getBlock().getState();
		dispenser.getInventory().addItem(e.getItem());
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e){
		if(e.toWeatherState()){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e){
		for(int x = 0; x < 4; x++){
			e.setLine(x, ChatColor.translateAlternateColorCodes('&', e.getLine(x)));
		}
	}
	
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent e){
		Player player = e.getPlayer();
		if(e.getRightClicked() instanceof ItemFrame){
			if(player.hasPermission(pluginMain.pluginManager.getPermission("thf.world.modify"))){
				
			}
		}
	}
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e){
		Player player = e.getPlayer();
		if(player.hasPermission(pluginMain.pluginManager.getPermission("thf.drop"))){
			if(e.getItemDrop().getItemStack().getTypeId() != Item.FLINT.id){
				player.sendMessage(ChatColor.DARK_RED + "You can only drop Flint!");
				e.setCancelled(true);
			}
		}else if(player.hasPermission(pluginMain.pluginManager.getPermission("thf.drop.destroy"))){
			if(e.getItemDrop().getItemStack().getTypeId() != Item.FLINT.id){
				player.sendMessage(ChatColor.DARK_RED + "You can only drop Flint!");
				e.setCancelled(true);
				e.getItemDrop().setItemStack(new ItemStack(0, 0));
			}
		}else{
			e.setCancelled(true);
		}
	}
}
