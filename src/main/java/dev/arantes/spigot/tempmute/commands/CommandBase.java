package dev.arantes.spigot.tempmute.commands;

import dev.arantes.spigot.tempmute.TempMutePlugin;
import dev.arantes.spigot.tempmute.exceptions.NoPermissionException;
import dev.arantes.spigot.tempmute.exceptions.PluginException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class CommandBase implements CommandExecutor {
    private final TempMutePlugin plugin;
    private String permission;

    public CommandBase(final TempMutePlugin plugin, final String name, final String permission) {
        this.plugin = plugin;
        this.permission = permission;

        plugin.getCommand(name).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            if (permission != null && !commandSender.hasPermission(permission)) {
                throw new NoPermissionException();
            }

            execute(commandSender, strings);
        }catch (PluginException e) {
            commandSender.sendMessage(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public abstract void execute(CommandSender sender, String[] args) throws PluginException;

    public TempMutePlugin getPlugin() {
        return plugin;
    }
}
