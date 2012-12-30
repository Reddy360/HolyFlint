    package me.reddy360.theholyflint.command;
     
    import me.reddy360.theholyflint.PluginMain;
     
    import org.bukkit.Bukkit;
    import org.bukkit.ChatColor;
    import org.bukkit.command.Command;
    import org.bukkit.command.CommandExecutor;
    import org.bukkit.command.CommandSender;
    import org.bukkit.entity.EntityType;
    import org.bukkit.entity.Player;
     
    public class CommandTrainCannon implements CommandExecutor {
            PluginMain pluginMain;
            public CommandTrainCannon(PluginMain pluginMain) {
                    this.pluginMain = pluginMain;
            }
            @Override
            public boolean onCommand(CommandSender sender, Command command,
                            String label, String[] args) {
                    if(!sender.hasPermission(pluginMain.pluginManager.getPermission("thf.trains"))){
                            sender.sendMessage("");
                            return true;
                    }
                    if(!(sender instanceof Player)){
                            sender.sendMessage(ChatColor.DARK_RED + "You cannot console train!");
                            return true;
                    }
                    final Player player = (Player) sender;
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.MINECART);
                   
                    for(org.bukkit.entity.Entity entity : player.getNearbyEntities(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())){
                            if(entity.getType() == EntityType.MINECART){
                                    entity.setVelocity(player.getVelocity().multiply(2));
                            }
                    }
                   
                    Bukkit.getScheduler().scheduleSyncDelayedTask(pluginMain, new Runnable(){
                            @Override
                            public void run() {
                                    for(org.bukkit.entity.Entity entity : player.getNearbyEntities(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())){
                                            if(entity.getType() == EntityType.MINECART){
                                                    entity.getLocation().getWorld().createExplosion(entity.getLocation(), 0F);
                                                    entity.remove();
                                            }
                                    }
                            }
                    }, 20L);
                   
                    return false;
            }
     
    }
