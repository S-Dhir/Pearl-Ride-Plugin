package org.github.s_dhir.ridepearls;

import org.bukkit.Bukkit;
import org.bukkit.entity.EnderPearl;
import org.bukkit.util.Vector;

public class CustomPearlEntity {
    public final RidePearlsPlugin thisPlugin;
    public final EnderPearl thisEnderPearl;
    public final Vector firstVelocity;
    public final double firstSpeed;
    public CustomPearlEntity(RidePearlsPlugin thisPlugin, EnderPearl enderPearl, Vector firstVelocity) {
        thisEnderPearl = enderPearl;
        this.thisPlugin = thisPlugin;
        speed = thisEnderPearl.getVelocity().length();
        prevVelocity = thisEnderPearl.getVelocity().clone();
        this.firstVelocity = firstVelocity;
        firstSpeed = firstVelocity.length();
        Bukkit.getScheduler().runTaskTimer(thisPlugin, () -> {
            updateVelocity();
        }, 0L, 5L);
    }
    private double speed;
    private Vector prevVelocity;
    public void updateVelocity() {
        double newSpeed = thisEnderPearl.getVelocity().length();
        double dotProduct = thisEnderPearl.getVelocity().dot(prevVelocity);
        if(newSpeed>= speed || !(dotProduct*0.95<newSpeed*speed && newSpeed*speed<dotProduct*1.05) || thisEnderPearl.hasGravity()) {
            speed =newSpeed;
            prevVelocity = thisEnderPearl.getVelocity();
        } else {
            thisEnderPearl.setVelocity(prevVelocity);
            if(speed<firstSpeed){
                thisEnderPearl.setVelocity(firstVelocity);
            }
        }
    }
}
