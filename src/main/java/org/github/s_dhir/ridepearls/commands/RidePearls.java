//This command is work in progress. The following code does not really do anything yet.
/**
 * The plan is to use this command to enable/disable the plugin
 * Another idea for a command is : when a player throws an ender pearl whilst riding an ender pearl, what should happen to ender pearl that the player is riding?
 * Personally, I had a ton of fun flying around the end using only ender pearls. It's damn fast.
 * Code written by Shiven Dhir
 */

package org.github.s_dhir.ridepearls.commands;

import net.minecraft.server.commands.CommandGamerule;
import org.bukkit.command.*;
import org.github.s_dhir.ridepearls.RidePearlsPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RidePearls extends CommandGamerule implements CommandExecutor {
    private final RidePearlsPlugin thisPlugin;
    public RidePearls(RidePearlsPlugin plugin) {
        thisPlugin = plugin;
    }
    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch (args[0].toLowerCase()) {
            case "toggleenable":
                return toggleEnable(sender, command, label, args);

            case "deletepearls":
                return deletePearls(sender, command, label, args);
            default:
                return false;
        }

    }

    protected boolean deletePearls(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

    protected boolean toggleEnable(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (senderIsValid(sender)) {
            thisPlugin.isDisabled = true;

            thisPlugin.utilityFunctions.sendMessageToAllOps("Ride Ender Pearls plugin has been disabled", null);

            return true;
        }

        return false;
    }

    protected boolean senderIsValid(@NotNull CommandSender sender) {
        if(sender.isOp()) {
            return true;
        }
        if(sender instanceof BlockCommandSender) {
            BlockCommandSender blockSender = (BlockCommandSender) sender;
            try {

                return Objects.equals(thisPlugin.props.getProperty("enable-command-block"), "true");
            } catch (Exception ignored){
            }
        }
        if(sender instanceof ConsoleCommandSender) {
            return true;
        }
        return false;
    }
}
