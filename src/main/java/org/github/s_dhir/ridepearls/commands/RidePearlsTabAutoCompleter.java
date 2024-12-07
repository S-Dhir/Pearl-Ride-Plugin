/**
 * Autocompleter for a enable/disable command. Work in progress
 * Author(s): Shiven Dhir
 */
package org.github.s_dhir.ridepearls.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.github.s_dhir.ridepearls.RidePearlsPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RidePearlsTabAutoCompleter implements TabCompleter {
    public final RidePearlsPlugin thisPlugin;
    public RidePearlsTabAutoCompleter(RidePearlsPlugin thisPlugin) {
        this.thisPlugin = thisPlugin;
    }
    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside of a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor
     */
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
