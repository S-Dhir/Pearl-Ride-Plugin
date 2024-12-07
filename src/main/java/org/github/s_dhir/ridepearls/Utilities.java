package org.github.s_dhir.ridepearls;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static org.bukkit.Bukkit.getServer;

public class Utilities {
    protected RidePearlsPlugin thisPlugin;
    public Utilities(RidePearlsPlugin thisPlugin) {
        this.thisPlugin = thisPlugin;
    }

    /**
     * Sends a message to the console and all OPs
     * @param message the message to send
     * @param permission currently unused. Will use it for permissions in future
     */
    public void sendMessageToAllOps(String message, @Nullable String permission) {
        thisPlugin.logger.info(message);
        for (Player operator : thisPlugin.getServer().getOnlinePlayers()) {
            if(!operator.isOp()) continue;
            operator.sendMessage(message);
        }
    }
}
