package me.reddy360.theholyflint.listeners;

import me.reddy360.theholyflint.PluginMain;
import me.reddy360.theholyflint.TradingHandler;
import net.minecraft.server.v1_4_6.Block;
import net.minecraft.server.v1_4_6.Item;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Sign;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

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
		if(e.getLine(1).equalsIgnoreCase("[THF]")){
			if(!(e.getPlayer().hasPermission(pluginMain.pluginManager.getPermission("thf.makesign")))){
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.DARK_RED + "You do not have permission to create [THF] signs.");
			}
		}
		for(int x = 0; x < 4; x++){
			e.setLine(x, ChatColor.translateAlternateColorCodes('&', e.getLine(x)));
		}
	}
	
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent e){
		Player player = e.getPlayer();
		if(e.getRightClicked() instanceof ItemFrame || e.getRightClicked() instanceof Painting){
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
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		Player player = e.getPlayer();
		if(e.getFrom().getBlock().getLocation().equals(e.getTo().getBlock().getLocation())){
			return;
		}
		Location location = e.getTo();
		location.setY(location.getY() - 2);
		if(location.getBlock().getState() instanceof Sign){
			Sign sign = (Sign) location.getBlock().getState();
			if(sign.getLine(1).equalsIgnoreCase("[THF]")){
				if(sign.getLine(3).equalsIgnoreCase("")){
					doSignEvent(sign.getLine(2), player, location);
					return;
				}else if(player.hasPermission(pluginMain.pluginManager.getPermission("thf.group." + sign.getLine(3)))){
					doSignEvent(sign.getLine(2), player, location);
					return;
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this sign. :(");
				}
			}
		}
	}

	private void doSignEvent(String line, Player player, Location signLocation) {
		if(line.startsWith("Launch:")){
			if(line.split(":").length == 1){
				return;
			}
			player.getWorld().createExplosion(player.getLocation(), 0F);
			Location location = player.getLocation().clone();
			location.setPitch(-90);
			Vector direction = location.getDirection();
			try{
				player.setVelocity(direction.multiply(Float.parseFloat(line.split(":")[1])));
/*				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.parseInt(line.split(":")[1]) * 2 * 20, 5)
					, true); */
				player.setFallDistance(Float.parseFloat(line.split(":")[1]) - (Float.parseFloat(line.split(":")[1]) * 2));
			}catch(NumberFormatException e){
				
			}
			
			
		}else if(line.equalsIgnoreCase("Chest")){
			Location location = signLocation;
			location.setY(location.getY() - 1);
			if(location.getBlock().getTypeId() == Block.CHEST.id){
				Chest chest = (Chest) location.getBlock().getState();
				player.getInventory().setContents(chest.getBlockInventory().getContents());
			}
			
		}else if(line.startsWith("Potion:")){
			if(line.split(":").length < 4){
				return;
			}
			String[] lines = line.split(":");
			player.addPotionEffect(new PotionEffect(PotionEffectType.getById(Integer.parseInt(lines[1])), Integer.parseInt(lines[2]), Integer.parseInt(lines[3]), true));
		}else if(line.startsWith("NoDamage:")){
			if(line.split(":").length == 1){
				return;
			}
			player.setNoDamageTicks(Integer.parseInt(line.split(":")[1]) * 20);
		}else if(line.startsWith("TP:")){
			if(line.split(":").length != 2){
				return;
			}
			String[] split = pluginMain.getConfig().getString("coords." + line.split(":")[1]).split(":");
			Location location = new Location(Bukkit.getWorld(split[0]), Integer.parseInt(split[1]) + 0.5, Integer.parseInt(split[2]) + 0.5, Integer.parseInt(split[3]) + 0.5);
			location.setPitch(player.getLocation().getPitch());
			location.setYaw(player.getLocation().getYaw());
			player.playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
			player.teleport(location);
		}else if(line.equalsIgnoreCase("Troll")){
			player.playEffect(EntityEffect.DEATH);
		}else if(line.startsWith("MSG:")){
			String[] args = line.split(":");
			if(args.length == 1){
				return;
			}
			if(pluginMain.getConfig().contains("Messages." + args[1])){
				player.sendMessage(ChatColor.GRAY + "[THF] " + ChatColor.RESET + pluginMain.getConfig().getString("Messages." + args[1]));
			}
		}else if(line.equalsIgnoreCase("TradeTest")){
			player.openInventory(TradingHandler.getInventory(player, player));
		}else if(line.startsWith("Voom:")){
			if(line.split(":").length == 1){
				return;
			}
			player.getWorld().createExplosion(player.getLocation(), 0F);
			Vector direction = player.getLocation().getDirection();
			try{
				player.setVelocity(direction.multiply(Float.parseFloat(line.split(":")[1])));
				player.setFallDistance(Float.parseFloat(line.split(":")[1]) - (Float.parseFloat(line.split(":")[1]) * 2));
			}catch(NumberFormatException e){
				
			}
			
			
		}else if(line.startsWith("Fire:")){
			if(line.split(":").length == 1){
				return;
			}try{
				player.setFireTicks(Integer.parseInt(line.split(":")[1]) * 20);
			}catch(NumberFormatException e){
			}
		}
	}
}
