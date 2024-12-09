/**
 * This is where all the magic happens. 2 events are being listened for: player throw projectile and player take damage.
 * Code written by Shiven Dhir
 */
package org.github.s_dhir.ridepearls;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.projectile.EntityEnderPearl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_21_R3.CraftServer;
import org.bukkit.craftbukkit.v1_21_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_21_R3.entity.CraftEnderPearl;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRemoveEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.UUID;

public class PlayerRideEvents implements Listener {
    private final RidePearlsPlugin thisPlugin;
    private static final String ridingEnderPearl = "isRidingEnderPearl";

    public PlayerRideEvents(RidePearlsPlugin plugin) {
        this.thisPlugin = plugin;
    }

    @EventHandler
    public void onPlayerShoot(ProjectileLaunchEvent playerLaunch) {
        if (thisPlugin.isDisabled) return;

        if(playerLaunch.getEntity().getShooter() instanceof Player && playerLaunch.getEntity() instanceof org.bukkit.entity.EnderPearl && !(playerLaunch.getEntity() instanceof CustomPearlEntity)) {
            Player player = (Player) playerLaunch.getEntity().getShooter();

            if(player.getVehicle() instanceof EnderPearl) {
                ((EnderPearl) player.getVehicle()).remove();
            }
            Bukkit.broadcastMessage("Current Velocity: " + ((EnderPearl)playerLaunch.getEntity()).getVelocity());

            playerLaunch.getEntity().addPassenger(player);
            new CustomPearlEntity(thisPlugin, (EnderPearl) playerLaunch.getEntity(), playerLaunch.getEntity().getVelocity());
//            CraftEnderPearl enderPearl = (CraftEnderPearl) playerLaunch.getEntity();
//
//            if(enderPearl.getCustomName() == "HOGL") return;
//            Bukkit.broadcastMessage("Unmodded pearl UUID is");
//            Bukkit.broadcastMessage(enderPearl.getUniqueId().toString());
//            player.sendMessage("I will execute");
//
//            CustomPearlEntity customPearl = new CustomPearlEntity((CraftServer) enderPearl.getServer(), enderPearl.getHandle());
//
//
//
//            customPearl.teleport(enderPearl.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
//            customPearl.addPassenger(player);
//            customPearl.setVelocity(enderPearl.getVelocity());
//            customPearl.setShooter(player);
//            customPearl.setGravity(enderPearl.hasGravity());
//            customPearl.addPassenger(player);
//            customPearl.setCustomName("HOGL");
//
//            Bukkit.broadcastMessage(customPearl.getUniqueId().toString());
//            Bukkit.broadcastMessage(String.valueOf(customPearl.getLocation().getBlockX()));

        }
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent playerDamage) {

        if (thisPlugin.isDisabled) return;
        if (!(playerDamage.getEntity() instanceof Player)) return;

        Player damaged = (Player) playerDamage.getEntity();

        if(damaged.getVehicle()==null) {
            return;
        }
        Entity pearl = damaged.getVehicle();

        if(!(pearl instanceof EnderPearl)) {
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
                return;
            case SONIC_BOOM:
            case ENTITY_ATTACK:
            case PROJECTILE:
                pearl.eject();
                damaged.removeMetadata(ridingEnderPearl, thisPlugin);
                damaged.setMetadata(ridingEnderPearl, new FixedMetadataValue(thisPlugin, false));
                return;
        }
    }
}

