/**
 * This is where all the magic happens. 2 events are being listened for: player throw projectile and player take damage.
 * Code written by Shiven Dhir
 */
package org.github.s_dhir.ridepearls;

import org.bukkit.Bukkit;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class PlayerRideEvents implements Listener {
    private final RidePearlsPlugin thisPlugin;
    private static final String ridingEnderPearl = "isRidingEnderPearl";

    public PlayerRideEvents(RidePearlsPlugin plugin) {
        this.thisPlugin = plugin;
    }


    @EventHandler
    public void onPlayerShoot(ProjectileLaunchEvent playerLaunch) {
        if (thisPlugin.isDisabled) return;

        if(playerLaunch.getEntity().getShooter() instanceof Player && playerLaunch.getEntity() instanceof org.bukkit.entity.EnderPearl) {
            Player player = (Player) playerLaunch.getEntity().getShooter();
            if(player.getVehicle() instanceof EnderPearl) {
                EnderPearl playerPearl = (EnderPearl) player.getVehicle();
                playerPearl.remove();
            }
            EnderPearl enderPearl = (EnderPearl) playerLaunch.getEntity();
            player.setMetadata(ridingEnderPearl, new FixedMetadataValue(thisPlugin, true));
            enderPearl.addPassenger(player);

        }
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent playerDamage) {

        if (thisPlugin.isDisabled) return;
        if (!(playerDamage.getEntity() instanceof Player)) return;

        Player damaged = (Player) playerDamage.getEntity();

        if(damaged.getVehicle()==null) {
            thisPlugin.logger.info("False: null vehicle");
            return;
        }
        Entity pearl = damaged.getVehicle();

        if(!(pearl instanceof EnderPearl)) {
            thisPlugin.logger.info("False: Vehicle not an Enderpearl");
            return;
        }
        Bukkit.getLogger().info(playerDamage.getCause().name());
        switch (playerDamage.getCause()) {
            case CONTACT:
            case FALL:
            case SUFFOCATION:
            case CRAMMING:
            case FLY_INTO_WALL:
                playerDamage.setCancelled(true);
                playerDamage.setDamage(0d);
                thisPlugin.getLogger().info("It's cancelled LOL");
                return;
            case SONIC_BOOM:
            case ENTITY_ATTACK:
            case PROJECTILE:
                pearl.eject();
                Bukkit.getLogger().info("Attempted ejection");
                damaged.removeMetadata(ridingEnderPearl, thisPlugin);
                damaged.setMetadata(ridingEnderPearl, new FixedMetadataValue(thisPlugin, false));
                return;
        }
    }
}

