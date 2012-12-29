package me.reddy360.theholyflint;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;


public class Signs {
	private static PluginMain pluginMain;
	public Signs(PluginMain pluginMain){
		Signs.pluginMain = pluginMain;
	}
	
	public static void setServerStatus(String[] status){
		if(pluginMain.serverStatusSigns){
			String[] points = pluginMain.getConfig().getString("Signs.ServerStatus").split(":");
			Location location = new Location(Bukkit.getWorld(points[0]), Integer.parseInt(points[1]), Integer.parseInt(points[2]), Integer.parseInt(points[3]));
			Sign sign = (Sign) location.getBlock();
			for(int x = 0; x <= 3; x++){
				sign.setLine(x, status[x]);
			}
		}
	}
}
