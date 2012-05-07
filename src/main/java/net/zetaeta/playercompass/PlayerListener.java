package net.zetaeta.playercompass;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == Material.COMPASS) {
            Player target = null;
            double shortest = Double.MAX_VALUE;
            for (Player p : player.getWorld().getPlayers()) {
                if (p.equals(player)) {
                    continue;
                }
                double temp = player.getLocation().distance(p.getLocation());
                if (temp < shortest) {
                    shortest = temp;
                    target = p;
                }
            }
            if (target == null) {
                return;
            }
            PlayerCompass.targets.put(target, player);
            player.setCompassTarget(player.getLocation());
            player.sendMessage("§a  Compass pointing to " + target.getDisplayName());
        }
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getFrom().equals(event.getTo()) || (event.getFrom().getBlockX() == event.getTo().getBlockX() && event.getFrom().getBlockZ() == event.getTo().getBlockZ())) {
            return;
        }
        Player p = PlayerCompass.targets.get(event.getPlayer());
        if (p != null) {
            p.setCompassTarget(event.getTo());
        }
    }
}
